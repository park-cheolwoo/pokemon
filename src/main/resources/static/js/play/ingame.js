$(function () {
	$.ajax({
		url: "/ingame/me",
		type: "GET",
		success: function (data) {
			if (data !== undefined && data.ingame) {
				location.href = "/play/battle1";
			}
		},
		error: function (e) {
			console.log(e);
		}
	})
});