import {firstComments, ingameComments, attackComments, exitComments } from './battleComments.js';
import { firstSelection, attackSelection } from  './battleSelection.js';
import './battleEvent.js';

$(function() {
	const noImagePokemon = "/images/no-sprite.png";

	const container = $(".container");
	const btnContainer = $(".selection-box");
	const textBox = $(".text-box");

	$.when(
		$.ajax({ url: '/ingame/me/info', dataType: 'json'}),
		$.ajax({ url: '/player/items/my-items/info', dataType: 'json'})
	).then(function (battleResponse, itemResponse) {

		if (battleResponse[0] === undefined || itemResponse[0] === undefined) {
			alert("잘못된 접근입니다.");
			location.href = "/";
			return;
		}

		const { playerId, myPokemons, enemies, ingame, stageId, maxStageId, stage } = battleResponse[0];
		let selectionIdx = battleResponse[0].selectionIdx;

		if (
			(myPokemons === undefined || myPokemons.length === 0)
			|| (enemies === undefined || enemies.length === 0)
			|| (stage === undefined)
		) {
			alert("잘못된 접근입니다.");
			location.href = "/";
		}

		$(container).css({backgroundImage: `url(/images/play/battle/${stage.habitat.name}.png)`});

		if (enemies[0].hp === 0) {
			$(container).trigger("stageClear", [{ playerId, stageId, maxStageId, stage, comments: [[`이미 ${enemies[0].name} 은(는) 쓰러져 있다 ..`]] }]);
		} else {
			container.append(pokemonBlockForm("you", enemies[0]));
		}

		if (myPokemons[selectionIdx].hp === 0) {
			$(container).trigger("pokemonDown", [{ playerId, myPokemons, selectionIdx }]);
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
				appendBtns(firstSelection(myPokemons, selectionIdx, itemResponse[0]), false);
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

			const executedPower = executeDamage(enemies[0].types[0], data.typesId, data.power * (myPokemons[selectionIdx].level / 50));
			const hp = changeHp("you", executedPower.power);
			ingameEnemyHp(enemies[0].id, hp);
			enemies[0].hp = hp;

			let callback;
			if (hp > 0) {
				callback = () => $(textBox).trigger("damaged");
			} else {
				callback = () => $(container).trigger("stageClear", [{ playerId, stageId, maxStageId, stage, comments: [[`야호! ${enemies[0].name} 을(를) 쓰러뜨렸다!`]] }]);
			}

			$(textBox).trigger("nextComment", [{ comments: attackComments(myPokemons[selectionIdx].name, enemies[0].name, data.name, executedPower), isWait: true, callback }]);
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

			const executedPower = executeDamage(myPokemons[selectionIdx].types[0], attack.typesId, attack.power * (enemies[0].level / 50));
			const hp = changeHp("me", executedPower.power);
			ingamePokemonHp(myPokemons[selectionIdx].id, hp);
			myPokemons[selectionIdx].hp = hp;

			let callback;
			if (hp > 0) {
				callback = () => $(btnContainer).trigger("nextSelection");
			} else {
				callback = () => $(container).trigger("pokemonDown", [{ playerId, myPokemons, selectionIdx }]);
			}

			$(textBox).trigger("nextComment", [{ comments: attackComments(enemies[0].name, myPokemons[selectionIdx].name, attack.name, executedPower), isWait: true, callback }]);
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

		$(container).on("catchPokemon", function (e, data) {
			const { name, cost } = data.info;
			const totalHp = enemies[0].stats.find(stat => stat.id === 1).total;
			const hpPercent = enemies[0].hp / totalHp;
			const percent = Math.max(cost / 1000, 0.4);
			const totalPercent = Math.max(percent - hpPercent, 0.3);
			
			if (Math.random() < totalPercent) {
				catchPokemon(enemies[0]);

				$(container).trigger("stageClear", [{ playerId, stageId, maxStageId, stage, comments: [[`야호! ${enemies[0].name} 을(를) 잡았다 !`]] }]);
			} else {
				$(textBox).trigger("nextComment", [{
					comments: [[`${name} (으)로 시도했지만, 잡지 못했다 ..`]],
					isWait: true,
					callback: () => $(textBox).trigger("damaged")
				}]);
			}

		});

		$(container).on("healPokemon", function (e, data) {
			let { cost } = data.info;

			const healValue = Math.min(Math.floor(cost / 20), 60);
			const totalHp = myPokemons[selectionIdx].stats.find(stat => stat.id === 1).value;
			const prevHp = myPokemons[selectionIdx].hp;
			const hp = Math.min(prevHp + healValue, totalHp);

			ingamePokemonHp(myPokemons[selectionIdx].id, hp);
			myPokemons[selectionIdx].hp = hp;
			changeHp("me", prevHp - hp);

			$(textBox).trigger("nextComment", [{
				comments: [[`${myPokemons[selectionIdx].name} 은(는) ${totalHp === hp ? "모두" : hp - prevHp + "만큼"} 회복했다.`]],
				callback: () => $(btnContainer).trigger("nextSelection")
			}]);

		});
		

		$(container).on("useItem", function (e, data) {
			$(btnContainer).children().remove();
			const { item, idx } = data;
			const { id } = item;
			const { name, categoryId } = item.info;

			let callback;
			if (categoryId === 34) {
				callback = () => $(container).trigger("catchPokemon", [item]);
			} else if (categoryId === 27) {
				callback = () => $(container).trigger("healPokemon", [item]);
			}
			
			$.ajax({
				url: '/player/items/my-items/use',
				type: 'POST',
				data: JSON.stringify(id),
				contentType: 'application/json',
				success: function (data) {
					itemResponse[0].filter(item => item.id === id)[0].count = data;
		
					$(textBox).trigger("nextComment", [{
						comments: [[`${name} 을(를) 사용했다.`]],
						isWait: true,
						callback
					}]);
				},
				error: function (e) {
					console.log(e);
				}
			});
		});
		
		$(container).on("exit", function() {
			$(btnContainer).children().remove();

			if(Math.random() < 0.5) {
				$(textBox).trigger("nextComment", { comments: exitComments(), isWait: true,
					callback: () => {
						$(btnContainer).remove();
						$(textBox).remove();

						$.when(
							$.ajax({url: '/ingame/status', type: 'POST', data: JSON.stringify(false), contentType: 'application/json'}),
							$.ajax({url: '/ingame/enemy/delete', type: 'POST'})
						).then(function () {
							location.href = "/";
						});
					}
				})
			} else {
				$(textBox).trigger("nextComment", { comments: [["도망 칠 수 없다 .."]], isWait: true,
					callback: () => {
						$(textBox).trigger("damaged");
					}
				});
			}
		});

		function executeDamage(targetTypeInfo, type, power) {
			const { damageFrom } = targetTypeInfo;
			if (damageFrom !== undefined) {
				const info = damageFrom.find(t => t.type == type);
				if (info !== undefined && info.damage !== undefined) {
					const commentTail = ["효과가 별로인듯 하다 ..", "평범했다.", "효과는 굉장했다!"];
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

			const maxHp = data.stats.find(stat => stat.id === 1);
			const totalHp = part === "me" ? maxHp.value : maxHp.total;
			const pokemon = $("<div>", {class: `pokemon ${part}`});
			pokemon.html(`
					<div class="pokemon__info">
						<div class="pokemon__info--level">LV ${data.level}</div>
						<div class="pokemon__info--name">${data.name}</div>
						<div class="pokemon__info--hp hp-bar">
							<div class="hp-bar__value" data-value="${data.hp}" data-total="${totalHp}"></div>
						</div>
					</div>
					<div class="pokemon-image">
						<img src="${femaleSprites !== noImagePokemon && !data.gender ? femaleSprites : maleSprites }" />
					</div>
				`);
			setTimeout(() => changeHp(part, 0), 100);

			return pokemon;
		}
	}).fail(function(e) {
		console.log(e);
		alert("서버와의 연결이 불안정합니다.");
		location.href = "/";
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

	function catchPokemon(enemy) {
		const { pokemon } = enemy;
		$.ajax({
			url: '/player/pokemon/save',
			type: 'POST',
			data: JSON.stringify({ pokemonId: pokemon.id, name: pokemon.name, gender: enemy.gender, level: enemy.level }),
			contentType: 'application/json',
			error: function (e) {
				console.log(e);
			}
		})
	}
});