import {firstComments, ingameComments, attackComments } from './battleComments.js';
import { firstSelection, attackSelection } from  './battleSelection.js';
import './battleEvent.js';

$(function() {
	const noImagePokemon = "/images/no-sprite.png";

	const container = $(".container");
	const btnContainer = $(".selection-box");
	const textBox = $(".text-box");

	$.ajax({
		url: '/ingame/me/info',
		dataType: 'json',
		success: function(data) {
			if (data === undefined) {
				alert("잘못된 접근입니다.");
				location.href = "/";
				return;
			}

			const { myPokemons, enemies, ingame, stageId, maxStageId, stage } = data;
			let selectionIdx = data.selectionIdx;
			
			if (
				(myPokemons === undefined || myPokemons.length == 0)
				|| (enemies === undefined || enemies.length === 0)
				|| (stage === undefined)
			) {
				alert("잘못된 접근입니다.");
				location.href = "/";
			}
			
			$(container).css({backgroundImage: `url(/images/play/battle/${stage.habitat.name}.png)`});
			
			if (enemies[0].hp === 0) {
				$(container).trigger("stageClear", [{ stageId, maxStageId, stage, enemyName: enemies[0].name }]);
			} else {
				container.append(pokemonBlockForm("you", enemies[0]));				
			}

			if (myPokemons[selectionIdx].hp === 0) {
				$(container).trigger("pokemonDown", [{ myPokemons, selectionIdx }]);
			} else {
				container.append(pokemonBlockForm("me", myPokemons[selectionIdx]));			
				
				$(container).one("transitionend", () => {
					let firstCommentData;
					if (ingame) {
						firstCommentData = {
							comments: ingameComments(myPokemons[selectionIdx].name),
							callback: () => $(btnContainer).trigger("nextSelection")
						}
					} else {
						ingameStatus(true);
						firstCommentData = {
							comments: firstComments(myPokemons[selectionIdx].name, enemies[0].name),
							callback: () => $(btnContainer).trigger("nextSelection")
						}
					}

					$(textBox).trigger("nextComment", [firstCommentData]);
				});	
			}


			$(btnContainer).on("nextSelection", function (e, data) {
				if (data === undefined) {
					appendBtns(firstSelection(myPokemons, selectionIdx), false);
					return;
				}

				appendBtns(data, true);
			});

			$(btnContainer).on("attackList", function (e, data) {
				btnContainer.children(".selection-box__group").css({display: "none"});
				btnContainer.find(".selection-box__btn").removeClass("active-btn");

				appendBtns(attackSelection(data), true);
			});

			$(btnContainer).on("attack", function (e, data) {
				$(this).children(".selection-box__group:not(.main-group)").remove();

				$(".pokemon.you").addClass("damaged-pokemon");
				$(".pokemon.me").addClass("right-attack-pokemon");
				setTimeout(() => {
					$(".pokemon.you").removeClass("damaged-pokemon");
					$(".pokemon.me").removeClass("right-attack-pokemon");
				}, 1000);

				const executedPower = executeDamage(enemies[0].types[0], data.typesId, data.power * (myPokemons[selectionIdx].level / 100));
				const hp = changeHp("you", executedPower.power);
				ingameEnemyHp(enemies[0].id, hp);
				enemies[0].hp = hp;

				let callback;
				if (hp > 0) {
					callback = () => $(textBox).trigger("damaged");
				} else {
					callback = () => $(container).trigger("stageClear", [{ stageId, maxStageId, stage, enemyName: enemies[0].name }]);
				}

				$(textBox).trigger("nextComment", [{ comments: attackComments(myPokemons[selectionIdx].name, enemies[0].name, data.name, executedPower), callback }]);
			});

			$(textBox).on("damaged", function (e, data) {
				const { attacks } = enemies[0];
				const attack = attacks[Math.floor(Math.random() * (attacks.length))];

				$(".pokemon.me").addClass("damaged-pokemon");
				$(".pokemon.you").addClass("left-attack-pokemon");
				$(container).addClass("shake-device");
				setTimeout(() => {
					$(".pokemon.me").removeClass("damaged-pokemon");
					$(".pokemon.you").removeClass("left-attack-pokemon");
					$(container).removeClass("shake-device");
				}, 1000);

				const executedPower = executeDamage(myPokemons[selectionIdx].types[0], attack.typesId, attack.power * (enemies[0].level / 100));
				const hp = changeHp("me", executedPower.power);
				ingamePokemonHp(myPokemons[selectionIdx].id, hp);
				myPokemons[selectionIdx].hp = hp;
				
				let callback;
				if (hp > 0) {
					callback = () => $(btnContainer).trigger("nextSelection");
				} else {
					callback = () => $(container).trigger("pokemonDown", [{ myPokemons, selectionIdx }]);
				}

				$(textBox).trigger("nextComment", [{ comments: attackComments(enemies[0].name, myPokemons[selectionIdx].name, attack.name, executedPower), callback }]);
			});

			$(btnContainer).on("changePokemon", function (e, data) {

				$.ajax({
					url: '/ingame/pokemon/idx',
					type: 'POST',
					data: JSON.stringify(data),
					contentType: 'application/json',
					success: function (d) {
						if (d) {
							$(btnContainer).children().remove();
							$(container).children(".modal-container").remove();
							selectionIdx = data;
							$(textBox).trigger("nextComment", [{comments: [[`${myPokemons[selectionIdx].name} (으)로 포켓몬 교체 !!`]], callback: () => {
									const changePokemon = pokemonBlockForm("me", myPokemons[selectionIdx]);
									changePokemon.css({opacity: 0});
									togglePokemon("me", false);
									setTimeout(() => {
										container.children(".pokemon.me").remove();
										container.append(changePokemon);
										changeHp("me", 0);

										setTimeout(() => togglePokemon("me", true, () => $(textBox).trigger("nextComment", [{
											comments: [[`가랏! ${myPokemons[selectionIdx].name} !`, `${myPokemons[selectionIdx].name} 은(는) 무엇을 할까?`]],
											callback: () => $(btnContainer).trigger("nextSelection")
										}])), 500);
									}, 500);
								}}]
							);
						}
					},
					error: function (e) {
						console.log(e);
						alert("서버와의 연결이 불안정합니다.");
						location.href = "/";
					}
				});
			});

			function executeDamage(targetTypeInfo, type, power) {
				const { damageFrom } = targetTypeInfo;
				if (damageFrom !== undefined) {
					const info = damageFrom.find(t => t.type == type);
					if (info !== undefined && info.damage !== undefined) {
						const commentTail = ["효과는 굉장했다!", "평범했다.", "효과가 별로인듯 하다 .."];
						return {
							power: Math.round(power * info.damage),
							comments: commentTail[info.damage] 
						}
					}
				}

				return {
					power: Math.round(power),
					comments: "평범했다." 
				};
			}

			container.addClass("active-device");
			
			function changeHp(target, value) {
				const bar = $(".pokemon." + target).find(".hp-bar__value");
				const total = bar.attr("data-total");
				let hp = bar.attr("data-value");
				
				hp = Math.max(hp - value, 0);

				bar.attr("data-value", hp);

				const percent = hp / total * 100;
				bar.css({width: percent + "%", backgroundColor: `rgb(${(100 - percent) / 50 * 255}, ${percent / 50 * 255}, 0)`});
				
				return hp;
			}
			
			function pokemonBlockForm(part, data) {
				const maleSprites = part === "you" ? data.pokemon.sprites.front_default : data.pokemon.sprites.back_default;
				const femaleSprites = part === "you" ? data.pokemon.sprites.front_female : data.pokemon.sprites.back_female;

				const totalHp = data.stats.find(stat => stat.id === 1).value;
				const pokemon = $("<div>", {class: `pokemon ${part}`});
				pokemon.html(`
					<div class="pokemon__info">
						<div class="pokemon__info--level">LV ${data.level}</div>
						<div class="pokemon__info--name">${data.name}</div>
						<div class="pokemon__info--hp hp-bar">
							<div class="hp-bar__value" data-value="${data.hp}" data-total="${totalHp < data.hp ? data.hp : totalHp}"></div>
						</div>
					</div>
					<div class="pokemon-image">
						<img src="${femaleSprites !== noImagePokemon && !data.gender ? femaleSprites : maleSprites }" />
					</div>
				`);
				setTimeout(() => changeHp(part, 0), 100);

				return pokemon;
			}
		},
		error: function(e) {
			console.log(e);
		}
	});

	function appendBtns(btns, hasPrev) {
		const btnGroup = $("<div>", {class: "selection-box__group"});

		if (!hasPrev) {
			$(btnContainer).children().remove();
			btnGroup.addClass("main-group");
		}

		btnGroup.html(btns.map(btn => {
			const button = $("<div>", {class: "selection-box__btn"});
			const content = $("<span>", {text: btn.text});

			if (btn.image !== undefined) {
				button.append($("<img>", {src: btn.image}));
			}
			button.append(content);
			return button;
		}));

		if (btnGroup.children().length === 0) {
			btnGroup.append($("<div>", {class: "selection-box__btn none-btn", text: "< 선택 없음 >"}));
		}

		if (hasPrev) {
			btnGroup.prepend($("<div>", {class: "selection-box__back"}));
		}

		btnContainer.append(btnGroup);

		activateBtns(btnGroup, btns.map(btn => btn.event));
	}

	function activateBtns(btnGroup, events) {
		const backBtn = btnGroup.children(".selection-box__back");

		btnGroup.children(".selection-box__btn").each(function (idx) {
			setTimeout(() => {
				$(this).addClass("appearing-btn");

				$(this).one("animationend", function () {
					$(this).removeClass("appearing-btn");
					$(this).addClass("active-btn");

					if (events !== undefined && events.length > 0) {
						$(this).off("click");
						$(this).click(function () {
							const event = events[idx];
							$(btnContainer).trigger(event.name, [event.data]);
						});
					}

					backBtn.addClass("active");

					backBtn.click(function () {
						const prevBtnGroup = btnGroup.prev();
						btnGroup.remove();
						prevBtnGroup.css({display: ""});
						activateBtns(prevBtnGroup);
					});
				});
			}, 300 * idx);
		});
	}

	function togglePokemon(target, isActive, callback) {
		const pokemon = $(".pokemon." + target);

		pokemon.one("transitionend", function () {
			if (callback !== undefined) {
				callback();
			}
		});

		pokemon.css({opacity: isActive? 1 : 0});
	}
	
	function ingameStatus(status) {
		$.ajax({
			url: '/ingame/status',
			type: 'POST',
			data: JSON.stringify(status),
			contentType: 'application/json',
			error: function (e) {
				console.log(e);
			}
		});
	}
	
	function ingamePokemonHp(id, hp) {
		$.ajax({
			url: '/ingame/pokemon/hp',
			type: 'POST',
			data: JSON.stringify({ id, hp }),
			contentType: 'application/json',
			error: function (e) {
				console.log(e);
			}
		});
	}
	
	function ingameEnemyHp(id, hp) {
		$.ajax({
			url: '/ingame/enemy/hp',
			type: 'POST',
			data: JSON.stringify({ id, hp }),
			contentType: 'application/json',
			error: function (e) {
				console.log(e);
			}
		});
	}
});