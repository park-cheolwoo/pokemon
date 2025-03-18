import { firstSelection } from './battleSelection.js';

$(function () {
    const container = $(".container");
    const btnContainer = $(".selection-box");
    const textBox = $(".text-box");

    function appendComments(comments = [], next, callback) {
        const commentNodes = comments.map(function (comment) {
            return $("<div>", {class: "text-box__comment", text: comment});
        });

        textBox.html(commentNodes);
        activateComments(commentNodes, next, callback);
    }

    function activateComments(commentNodes = [], next, callback) {
        if (commentNodes.length > 0) {
            const originWidth = commentNodes[0].outerWidth();

            commentNodes[0].css({ width: 0, opacity: 1 });
            commentNodes[0].animate({ width: originWidth}, originWidth * 5, "linear", function () {
                if (commentNodes.length > 1) {
                    activateComments(commentNodes.slice(1), next, callback);
                    return;
                }

                if (next.length > 0) {
                    textBox.addClass("wait");

                    function handleKeyUpNextComment(e) {
                        if (e.keyCode === 13) {
                            textBox.removeClass("wait");
                            $(textBox).trigger("nextComment", [{comments: next, callback }]);

                            $(window).off("keyup", handleKeyUpNextComment);
                        }
                    }

                    $(window).on("keyup", handleKeyUpNextComment);
                    return;
                }

                if (callback !== undefined) {
                    callback();
                }
            });

            function handleKeyUpComment(e) {
                if (e.keyCode === 13) {
                    commentNodes[0].stop(true, true);

                    $(window).off("keyup", handleKeyUpComment);
                }
            }

            $(window).on("keyup", handleKeyUpComment);
        }
    }

    function popupModal(items, isExit = true) {
        const modalContainer = $("<div>", {class: "modal-container"});
        const modalBackground = $("<div>", {class: "modal-container__background"});
        const modalWrapper = $("<div>", {class: "modal-wrapper"})

		if (isExit) {
			function exitModal() {
	            modalContainer.remove();
				$(btnContainer).trigger("nextSelection");
	        }
			
	        modalBackground.click(exitModal);
			
			function handleKeyUp(e) {
				if (e.keyCode === 27) {

					exitModal();
					$(window).off("keyup", handleKeyUp);		
				}
			}
			
			$(window).on("keyup", handleKeyUp);
		}

        modalWrapper.html(items);
        modalContainer.html([modalWrapper, modalBackground]);
        container.append(modalContainer);
    }

    $(textBox).on("nextComment", function (e, data) {
        const { comments, callback } = data;

        appendComments(comments[0], comments.slice(1), callback);
    });

    $(btnContainer).on("pokemonList", function (e, data) {
		$(btnContainer).children().remove();
		const { pokemons, selectionIdx } = data;

		const itemWrapper = $("<div>", {class: "modal-wrapper__grid"});
		itemWrapper.html(pokemons.map((pokemon, idx) => {
            const itemContainer = $("<div>", {class: "modal-wrapper__item"});
            itemContainer.html(`
                <div class="modal-wrapper__item--title">${pokemon.name}</div>
                <div class="modal-wrapper__item--hp">
					<div class="modal-wrapper__item--hp-bar"></div>
				</div>
                <div class="modal-wrapper__item--image">
                    <img src="${pokemon.pokemon.sprites.front_default}" />
                </div>
            `);
			
			if (idx !== selectionIdx && pokemon.hp > 0) {
				itemContainer.addClass("active-item");

	            itemContainer.click(function () {
	                $(btnContainer).trigger("changePokemon", [idx]);
					$(btnContainer).trigger("nextSelection");
	            });
			} else if (idx === selectionIdx) {
				itemContainer.addClass("select-item");
			}

			lazyHpBar(itemContainer.find(".modal-wrapper__item--hp-bar"), pokemon.hp, pokemon.stats.find(stat => stat.id === 1).value);

            return itemContainer;
        }));

        popupModal(itemWrapper, true);
    });

    $(btnContainer).on("itemList", function (e, data) {
        console.log("아이템 ..");
        console.log(data);
    });
	
	$(container).on("stageClear", function (e, data) {
		setWaitComments(() => {
			$(textBox).trigger("nextComment", [{
				comments: [[`야호! ${data.enemyName} 을(를) 쓰러뜨렸다!`]],
				callback: () => setWaitComments(() => {
					$(btnContainer).remove();
					$(textBox).remove();
					const { stageId, maxStageId, stage } = data;
					
					if (maxStageId < stageId) {
						setMaxStage(stageId);
					}
					
					$.when(
						$.ajax({url: '/ingame/status', type: 'POST', data: JSON.stringify(false), contentType: 'application/json'}),
						$.ajax({url: '/ingame/enemy/delete', type: 'POST'})
					).then(function (data) {
						if (data) {
							$(".pokemon.you").animate({opacity: 0}, 1000, function () {
								const modalContents = $("<div>", {class: "modal-wrapper__content"});
								const modalForm = `
									<div class="modal-wrapper__content--title">스테이지 클리어</div>
									<div class="modal-wrapper__content--content">
										<div class="modal-wrapper__content--item-title">보상</div>
										<div class="modal-wrapper__content--item">
											<span>
												<img src="/images/store/coin.png" />
											</span>
											<span>${stage.money}</span>
										</div>
										<div class="modal-wrapper__content--item">
											<span>EXP : </span>
											<span>${stage.experience}</span>
										</div>
									</div>
								`;
								const modalBtn = $("<div>", {class: "modal-wrapper__content--btn", text: "나가기"});
								modalBtn.click(function() {
									location.href = "/play/plist";
								});
								
								modalContents.html([modalForm, modalBtn]);
								popupModal(modalContents, false);
							});
						} else {
							alert("서버와의 연결이 좋지 않습니다 ..");
							location.href = "/";
						}
					}).fail(function (e) {
						console.log(e);
						alert("서버와의 연결이 좋지 않습니다 ..");
						location.href = "/";
					});
				})
			}]);
		});
	});
	
	$(container).on("pokemonDown", function (e, data) {
		const { myPokemons, selectionIdx } = data;
		
		console.log(myPokemons, selectionIdx);
		
		if (myPokemons.every(pokemon => pokemon.hp == 0)) {
			alert("모든 포켓몬이 쓰러졌습니다 ..");
			location.href = "/";
			return;
		}

		$(textBox).trigger("nextComment", [{
			comments: [[`${myPokemons[selectionIdx].name} 이(가) 쓰러졌다.`, "다른 포켓몬을 꺼내야 한다."]],
			callback: () => $(btnContainer).trigger("pokemonList", [{
				pokemons: myPokemons,
				selectionIdx: selectionIdx
			}])
		}]);
	});
	
	function setMaxStage(maxStage) {
		$.ajax({
			url: '/ingame/maxStage',
			type: 'POST',
			data: JSON.stringify(stageId),
			contentType: 'application/json',
			error: function (e) {
				console.log(e);
			}
		});
	}
	
	function setWaitComments(callback) {
		$(textBox).addClass("wait");

		function handleKeyUpComment(e) {
		    if (e.keyCode === 13) {
		        $(window).off("keyup", handleKeyUpComment);
				callback();
		    }
		}

		$(window).on("keyup", handleKeyUpComment);
	}
	
	function lazyHpBar(hpBar, hp, totalHp) {
		setTimeout(() => {
			const percent = hp / totalHp * 100;
			hpBar.css({width: percent + "%", backgroundColor: `rgb(${(100 - percent) / 50 * 255}, ${percent / 50 * 255}, 0)`});
		}, 100);
	}

});