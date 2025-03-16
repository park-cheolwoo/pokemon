import { firstSelection } from './battleSelection.js';

$(function () {
    const btnContainer = $(".selection-box");
    const textBox = $(".text-box");

    function appendComments(comments = [], next) {
        const commentNodes = comments.map(function (comment) {
            return $("<div>", {class: "text-box__comment", text: comment});
        });

        textBox.html(commentNodes);
        activateComments(commentNodes, next);
    }

    function activateComments(commentNodes = [], next) {
        if (commentNodes.length > 0) {
            const originWidth = commentNodes[0].outerWidth();

            commentNodes[0].css({ width: 0, opacity: 1 });
            commentNodes[0].animate({ width: originWidth}, originWidth * 5, "linear", function () {
                if (commentNodes.length > 1) {
                    activateComments(commentNodes.slice(1), next);
                    return;
                }

                if (next.length > 0) {
                    textBox.addClass("wait");

                    function handleKeyUpNextComment(e) {
                        if (e.keyCode === 13) {
                            textBox.removeClass("wait");
                            $(textBox).trigger("nextComment", [next]);

                            $(window).off("keyup", handleKeyUpNextComment);
                        }
                    }

                    $(window).on("keyup", handleKeyUpNextComment);
                    return;
                }

                $(btnContainer).trigger("nextSelection");
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

    $(textBox).on("nextComment", function (e, data) {
        appendComments(data[0], data.slice(1));
    });

    $(btnContainer).on("pokemonList", function (e, data) {
        console.log("바꿔라 ..");
        console.log(data);
    });

    $(btnContainer).on("itemList", function (e, data) {
        console.log("아이템 ..");
        console.log(data);
    });

});