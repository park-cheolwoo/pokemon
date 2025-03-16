import { firstComments, attackComments } from './battleComments.js';
import { firstSelection, attackSelection } from  './battleSelection.js';
import './battleEvent.js';

$(function() {
	const noImagePokemon = "/images/no-sprite.png";

	const container = $(".container");
	const btnContainer = $(".selection-box");
	const textBox = $(".text-box");

	$.when(
		$.ajax('/play/create/pokemon/habitat/2?maxLevel=10'),
		$.ajax('/play/me/pokemon')
	).then(function (enemy, my) {
		const enemyData = enemy[0];
		const myData = my[0];
		console.log(enemyData);
		console.log(myData);

		container.append(pokemonBlockForm("you", enemyData));
		container.append(pokemonBlockForm("me", myData[0]));

		$(container).one("transitionend", () => $(textBox).trigger("nextComment", [firstComments(myData[0].name, enemyData.name)]));

		$(btnContainer).on("nextSelection", function (e, data) {
			if (data === undefined) {
				appendBtns(firstSelection(myData, 0), false);
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
			console.log(data);
			console.log("공격!!!!")

			$(this).children(".selection-box__group:not(.main-group)").remove();

			$(".pokemon.you").addClass("damaged-pokemon");
			setTimeout(() => $(".pokemon.you").removeClass("damaged-pokemon"), 1000);
			const power = executeDamage(enemyData, data.typesId, data.power * (myData[0].level / 100));
			changeHp("you", power);

			$(textBox).trigger("nextComment", [attackComments(myData[0].name, enemyData.name, data.name, power)]);
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
					<div class="pokemon__info--name">${data.pokemon.name}</div>
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

	function changeHp(target, value) {
		const bar = $(".pokemon." + target).find(".hp-bar__value");
		const barWidth = Number.parseInt(bar.css("width").slice(0, -1));
		const total = bar.attr("data-total");
		let hp = bar.attr("data-value");

		hp = Math.max(hp - value, 0);

		const percent = barWidth - (hp / total * 100);
		bar.css({backgroundColor: `rgb(${(100 - percent) / 50 * 255}, ${percent / 50 * 255}, 0)`})

		bar.css({width: percent + "%"});
	}
});