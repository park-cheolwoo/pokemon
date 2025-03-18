import {firstComments, attackComments, stageClearComments} from './battleComments.js';
import { firstSelection, attackSelection } from  './battleSelection.js';
import './battleEvent.js';

$(function() {
	const noImagePokemon = "/images/no-sprite.png";

	const container = $(".container");
	const btnContainer = $(".selection-box");
	const textBox = $(".text-box");

<<<<<<< HEAD
	$.when(
		$.ajax('/play/create/pokemon/habitat/2?maxLevel=10'),
		$.ajax('/play/me/pokemon')
	).then(function (enemy, my) {
		const enemyData = enemy[0];
		const myData = my[0];
		let pokemonIdx = 0;
		console.log(enemyData);
		console.log(myData);

		container.append(pokemonBlockForm("you", enemyData));
		container.append(pokemonBlockForm("me", myData[pokemonIdx]));

		$(container).one("transitionend", () => $(textBox).trigger("nextComment", [{comments: firstComments(myData[pokemonIdx].name, enemyData.name), callback: () => $(btnContainer).trigger("nextSelection")}]));

		$(btnContainer).on("nextSelection", function (e, data) {
			if (data === undefined) {
				appendBtns(firstSelection(myData, pokemonIdx), false);
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
			setTimeout(() => $(".pokemon.you").removeClass("damaged-pokemon"), 1000);
			const power = executeDamage(enemyData, data.typesId, data.power * (myData[pokemonIdx].level / 100));
			let nextCommentData;
			if (changeHp("you", power) > 0) {
				nextCommentData = {
					comments: attackComments(myData[pokemonIdx].name, enemyData.name, data.name, power),
					callback: () => $(btnContainer).trigger("nextSelection")
				};
			} else {
				nextCommentData = {
					comments: stageClearComments(myData[pokemonIdx].name, enemyData.name, data.name, power),
					callback: () => console.log("스테이지 클리어!!")
				}
			}
			$(textBox).trigger("nextComment", [nextCommentData]);
		});

		$(btnContainer).on("changePokemon", function (e, data) {
			$(btnContainer).children().remove();
			$(container).children(".modal-container").remove();
			pokemonIdx = data;
			$(textBox).trigger("nextComment", [{comments: [[`${myData[pokemonIdx].name} (으)로 포켓몬 교체 !!`]], callback: () => {
					const changePokemon = $(pokemonBlockForm("me", myData[pokemonIdx]));
					changePokemon.css({opacity: 0});
					togglePokemon("me", false);
					setTimeout(() => {
						container.children(".pokemon.me").remove();
						container.append(changePokemon);

						setTimeout(() => togglePokemon("me", true, () => $(textBox).trigger("nextComment", [{
							comments: [[`가랏! ${myData[pokemonIdx].name} !`, `${myData[pokemonIdx].name} 은(는) 무엇을 할까?`]],
							callback: () => $(btnContainer).trigger("nextSelection")
						}])), 500);
					}, 500);
				}}]
			);
		});

		function executeDamage(targetType, type, power) {
			console.log(targetType, type, power);
			return power;
		}

		container.addClass("active-device");
	}).fail(function (e) {
		console.log(e);
	});

	function pokemonBlockForm(part, data) {
		const maleSprites = part === "you" ? data.pokemon.sprites.front_default : data.pokemon.sprites.back_default;
		const femaleSprites = part === "you" ? data.pokemon.sprites.front_female : data.pokemon.sprites.back_female;
		const hp = data.stats.find(stat => stat.id === 1).value;
		return `
			<div class="pokemon ${part}">
				<div class="pokemon__info">
					<div class="pokemon__info--level">LV ${data.level}</div>
					<div class="pokemon__info--name">${data.name}</div>
					<div class="pokemon__info--hp hp-bar">
						<div class="hp-bar__value" data-value="${hp}" data-total="${hp}"></div>
					</div>
				</div>
				<div class="pokemon-image">
					<img src="${femaleSprites !== noImagePokemon && !data.gender ? femaleSprites : maleSprites }" />
				</div>
			</div>
		`;
	}

	function appendBtns(btns, hasPrev) {
		const btnGroup = $("<div>", {class: "selection-box__group"});

		if (!hasPrev) {
			$(btnContainer).children().remove();
			btnGroup.addClass("main-group");
=======
	$.ajax({
		url: '/ingame/me/info',
		dataType: 'json',
		success: function(data) {
			const { myPokemons, enemies } = data;
			let selectionIdx = data.selectionIdx;
			
			container.append(pokemonBlockForm("you", enemies[0]));
			container.append(pokemonBlockForm("me", myPokemons[selectionIdx]));

			$(container).one("transitionend", () => $(textBox).trigger("nextComment", [{
				comments: firstComments(myPokemons[selectionIdx].name, enemies[0].name),
				callback: () => $(btnContainer).trigger("nextSelection")
			}]));

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
				setTimeout(() => $(".pokemon.you").removeClass("damaged-pokemon"), 1000);
				const power = executeDamage(enemies[0], data.typesId, data.power * (myPokemons[selectionIdx].level / 100));
				let nextCommentData;
				if (changeHp("you", power) > 0) {
					nextCommentData = {
						comments: attackComments(myPokemons[selectionIdx].name, enemies[0].name, data.name, power),
						callback: () => $(btnContainer).trigger("nextSelection")
					};
				} else {
					nextCommentData = {
						comments: stageClearComments(myPokemons[selectionIdx].name, enemies[0].name, data.name, power),
						callback: () => console.log("스테이지 클리어!!")
					}
				}
				$(textBox).trigger("nextComment", [nextCommentData]);
			});

			$(btnContainer).on("changePokemon", function (e, data) {
				console.log(data)
				$(btnContainer).children().remove();
				$(container).children(".modal-container").remove();
				selectionIdx = data;
				$(textBox).trigger("nextComment", [{comments: [[`${myPokemons[selectionIdx].name} (으)로 포켓몬 교체 !!`]], callback: () => {
						const changePokemon = $(pokemonBlockForm("me", myPokemons[selectionIdx]));
						changePokemon.css({opacity: 0});
						togglePokemon("me", false);
						setTimeout(() => {
							container.children(".pokemon.me").remove();
							container.append(changePokemon);

							setTimeout(() => togglePokemon("me", true, () => $(textBox).trigger("nextComment", [{
								comments: [[`가랏! ${myPokemons[selectionIdx].name} !`, `${myPokemons[selectionIdx].name} 은(는) 무엇을 할까?`]],
								callback: () => $(btnContainer).trigger("nextSelection")
							}])), 500);
						}, 500);
					}}]
				);
			});

			function executeDamage(targetType, type, power) {
				console.log(targetType, type, power);
				return power;
			}

			container.addClass("active-device");
		},
		error: function(e) {
			console.log(e);
>>>>>>> branch 'main' of https://github.com/park-cheolwoo/pokemon.git
		}
<<<<<<< HEAD
=======
	});

	function pokemonBlockForm(part, data) {
		const maleSprites = part === "you" ? data.pokemon.sprites.front_default : data.pokemon.sprites.back_default;
		const femaleSprites = part === "you" ? data.pokemon.sprites.front_female : data.pokemon.sprites.back_female;
		
		console.log(data)
		const hp = data.stats.find(stat => stat.id === 1).value;
		return `
			<div class="pokemon ${part}">
				<div class="pokemon__info">
					<div class="pokemon__info--level">LV ${data.level}</div>
					<div class="pokemon__info--name">${data.name}</div>
					<div class="pokemon__info--hp hp-bar">
						<div class="hp-bar__value" data-value="${hp}" data-total="${hp}"></div>
					</div>
				</div>
				<div class="pokemon-image">
					<img src="${femaleSprites !== noImagePokemon && !data.gender ? femaleSprites : maleSprites }" />
				</div>
			</div>
		`;
	}

	function appendBtns(btns, hasPrev) {
		const btnGroup = $("<div>", {class: "selection-box__group"});

		if (!hasPrev) {
			$(btnContainer).children().remove();
			btnGroup.addClass("main-group");
		}
>>>>>>> branch 'main' of https://github.com/park-cheolwoo/pokemon.git

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

	function changeHp(target, value) {
		const bar = $(".pokemon." + target).find(".hp-bar__value");
		const total = bar.attr("data-total");
		let hp = bar.attr("data-value");

		hp = Math.max(hp - value, 0);

		bar.attr("data-value", hp);

		const percent = hp / total * 100;
		bar.css({backgroundColor: `rgb(${(100 - percent) / 50 * 255}, ${percent / 50 * 255}, 0)`})

		bar.css({width: percent + "%"});
		return hp;
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
});