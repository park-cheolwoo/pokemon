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

    function popupModal(items) {
        const modalContainer = $("<div>", {class: "modal-container"});
        const modalBackground = $("<div>", {class: "modal-container__background"});
        const modalWrapper = $("<div>", {class: "modal-wrapper"})

        modalBackground.click(function () {
            modalContainer.remove();
        });

        modalWrapper.html(items);
        modalContainer.html([modalWrapper, modalBackground]);
        container.append(modalContainer);
    }

    $(textBox).on("nextComment", function (e, data) {
        const { comments, callback } = data;

        appendComments(comments[0], comments.slice(1), callback);
    });

    $(btnContainer).on("pokemonList", function (e, data) {
        popupModal(data.map((pokemon, idx) => {
            const itemContainer = $("<div>", {class: "modal-wrapper__item"});
            itemContainer.html(`
                <div class="modal-wrapper__item--title">${pokemon.name}</div>
                <div class="modal-wrapper__item--image">
                    <img src="${pokemon.pokemon.sprites.front_default}" />
                </div>
            `);

            itemContainer.click(function () {
                $(btnContainer).trigger("changePokemon", [idx]);
            });

            return itemContainer;
        }));
    });

    $(btnContainer).on("itemList", function (e, data) {
        console.log("아이템 ..");
        console.log(data);
    });

});