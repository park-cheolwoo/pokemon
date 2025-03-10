$(function () {
    // 친구 요청 버튼 클릭 이벤트
    $("#requestbtn").click(function () {
        const toId = $(this).data("player-id"); // 랜덤 플레이어 ID
        const fromId = $("#currentPlayerId").val(); // 현재 접속 플레이어 ID

        $.ajax({
            url: "/friend/request",
            method: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: {
                fromId: fromId,
                toId: toId
            },
            success: function (response) {
                alert("친구 요청 성공: " + response);
            },
            error: function (xhr) {
                alert("친구 요청 실패: " + xhr.responseText);
            }
        });
    });
});
