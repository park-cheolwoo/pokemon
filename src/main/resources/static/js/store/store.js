$(function() {
	
	// 우측 상단 버튼 클릭 // 
	$(document).on("click", ".item_category_grow", function () {
		$(".item_list .text").removeClass("cat_on");
		$(".item_category_battle_box, .item_category_monsterball_box").attr("src", "/images/yellow-bar.png");
		$(".item_category_grow_text").toggleClass("cat_on");
		if ($(".item_category_grow_text").hasClass("cat_on")) {
			$(".item_category_grow_box").attr("src", "/images/green-bar.png");
		} else {
			$(".item_category_grow_box").attr("src", "/images/yellow-bar.png");
		}

		const items = $(".shop_items").find(".item_list_cat");
		items.each((index, element) => {
			if (["10", "26", "37"].includes($(element).text())) {
				$(element).closest(".shop_items").show();
			} else { $(element).closest(".shop_items").hide(); }
		});
	});

	$(document).on("click", ".item_category_battle", function () {
		$(".item_list .text").removeClass("cat_on");
		$(".item_category_grow_box, .item_category_monsterball_box").attr("src", "/images/yellow-bar.png");
		$(".item_category_battle_text").toggleClass("cat_on");
		if ($(".item_category_battle_text").hasClass("cat_on")) {
			$(".item_category_battle_box").attr("src", "/images/green-bar.png");
		} else {
			$(".item_category_battle_box").attr("src", "/images/yellow-bar.png");
		}

		const items = $(".shop_items").find(".item_list_cat");
		items.each((index, element) => {
			if (["1", "11","27","28","29","30"].includes($(element).text())) {
				$(element).closest(".shop_items").show();
			} else { $(element).closest(".shop_items").hide(); }
		});
	});

	$(document).on("click", ".item_category_monsterball", function () {
		$(".item_list .text").removeClass("cat_on");
		$(".item_category_grow_box, .item_category_battle_box").attr("src", "/images/yellow-bar.png");
		$(".item_category_monsterball_text").toggleClass("cat_on");
		if ($(".item_category_monsterball_text").hasClass("cat_on")) {
			$(".item_category_monsterball_box").attr("src", "/images/green-bar.png");
		} else {
			$(".item_category_monsterball_box").attr("src", "/images/yellow-bar.png");
		}

		const items = $(".shop_items").find(".item_list_cat");
		items.each((index, element) => {
			if (["33", "34"].includes($(element).text())) {
				$(element).closest(".shop_items").show();
			} else { $(element).closest(".shop_items").hide(); }
		});
	});

	// 우측 상단 버튼 클릭 // 

	// 우측 테이블 아이템 선택 //
	$(document).on("click", ".item_list_img", function () {
		$(".item_table").find(".item_list_img").removeClass("selected");
		let img = $(this).attr("src");
		let name = $(this).siblings(".item_list_name").text();
		let cost = $(this).siblings(".item_list_cost").text();
		console.log(img, name, cost);
		$(this).toggleClass("selected");
		$(".item_dscrb_img").attr("src", img);
		$(".item_dscrb_name").text(name);
		$(".item_cost").text(Number(cost).toLocaleString());
		$(".item_dscrb_img, .item_dscrb_coin, .item_dscrb_btn, .item_buy_btn").show();
	});
	// 우측 테이블 아이템 선택 //

	// 왼쪽 아이템 설명창 //		
	$(document).on("click", ".item_dscrb_btn, .item_describe_exit", function () {
		let id = $(".item_list_img.selected").closest(".shop_items").data("id");
		console.log(id);
		$.ajax({
			url: "/store/view/" + id,
			type: "POST",
			data: { "id": id },
			dataType: "json",
			success: function (data) { 
				console.log(data);
				$(".item_describe_image").attr("src", data.image);
				switch (data.categoryId) { 
					case 1:
					case 11:
					case 27:
					case 28:
					case 29:
					case 30:
						$(".item_describe_category").text("배틀아이템");
						break;
					case 10:
					case 26:
					case 37:
						$(".item_describe_category").text("육성아이템");
						break;
					case 33:
					case 34:
						$(".item_describe_category").text("몬스터볼");
						break;
				}
				$(".item_describe_name").text(data.name);
				$(".item_describe_cost").text(Number(data.cost).toLocaleString() + "원");
				$(".item_describe_txt").text(data.flavorText);
				$(".item_describe_container").toggle();
			},
			error: function () { 
				alert("아이템 상세보기 조회 실패");
			}
		})
	});
	// 왼쪽 아이템 설명창 //

	// 왼쪽 구매버튼 모달창 // 
	$(document).on("click", ".item_buy_btn", function() {
		$(".item_total_count").val("1");
		$(".item_total_cost").text($(".item_cost").text());
		$(".item_buy_cost").text($(".item_total_cost").text().replace(",", ""));
		$(".item_buy_container").toggle();
	});

	$(document).on("click", ".item_buy_no", function() {
		$(".item_buy_container").toggle();
	});

	$(document).on("click", ".upper_yes", function () {
		let count = Number($(".item_total_count").val());
		let cost = Number($(".item_buy_cost").text());
		$(".item_total_count").val(count + 1);
		$(".item_total_cost").text(
			(cost * (count+1)).toLocaleString()
		);
		countChk();
		costChk();
	});

	$(document).on("click", ".lower_yes", function() {
		let count = Number($(".item_total_count").val());
		let cost = Number($(".item_buy_cost").text());
		$(".item_total_count").val(count - 1);
		$(".item_total_cost").text(
			(cost * (count - 1)).toLocaleString()
		);
		countChk();
		costChk();
	});

	$(document).on("input", ".item_total_count", function() {
		let count = Number($(".item_total_count").val());
		let cost = Number($(".item_buy_cost").text());
		$(".item_total_count").val(count);
		$(".item_total_cost").text(
			(cost * count ).toLocaleString()
		);
		countChk();
		costChk();
	});

	const countChk = () => {
		let count = Number($(".item_total_count").val());
		if (count >= 99) {
			$(".item_upper").attr("src", "/images/store/gray-up.png");
			$(".item_upper").removeClass("upper_yes");
			return false;
		}
		else if (count <= 0) {
			$(".item_lower").attr("src", "/images/store/gray-down.png");
			$(".item_lower").removeClass("lower_yes");
			$(".item_buy_yes").attr("src", "/images/store/gray-check.png");
			$(".item_buy_yes").removeClass("buy_ok");
			return false;
		} else {
			$(".item_upper").attr("src", "/images/store/yellow-up.png");
			$(".item_upper").addClass("upper_yes");
			$(".item_lower").attr("src", "/images/store/yellow-down.png");
			$(".item_lower").addClass("lower_yes");
			$(".item_buy_yes").attr("src", "/images/store/green-check.png");
			$(".item_buy_yes").addClass("buy_ok");
		}
	}

	const costChk = () => { 
		let deposit = Number($(".game_money").text().replace(",", ""));
		let cost = Number($(".item_total_cost").text().replace(",", ""));
		if (cost > deposit) {
			$(".item_cost_warning").show();
			$(".item_buy_yes").attr("src", "/images/store/gray-check.png");
			$(".item_buy_yes").removeClass("buy_ok");
		} else {
			$(".item_cost_warning").hide();
			$(".item_buy_yes").attr("src", "/images/store/green-check.png");
			$(".item_buy_yes").addClass("buy_ok");
		}

	}

	$(document).on("click", ".buy_ok", function() {
		let name = $(".item_dscrb_name").text();
		let count = Number($(".item_total_count").val());
		let cost = $(".item_total_cost").text();
		$(".item_buy_container").toggle();
		$(".item_confirm_name").text(name);
		$(".item_confirm_count").text(count + " 개를");
		$(".item_confirm_cost").text(cost + " 에");
		$(".item_confirm_container").toggle();
	});

	$(document).on("click", ".item_confirm_yes", function () {
		let id = $(".item_list_img.selected").closest(".shop_items").data("id");
		let count = Number($(".item_total_count").val());
		let cost = Number($(".item_total_cost").text().replace(",",""));
		let playerId = `${session_id}`;
		alert("playerId : " + playerId);
		$.ajax({
			url: "/store/buy/" + id,
			type: "POST",
			data: { "playerId": playerId, "itemId": id, "count": count , "cost":cost},
			dataType: "json",
			success: function (data) {
				alert("구매 완료하였습니다.");
				$(".game_money").text(Number(data.cost).toLocaleString());
				// session_gameMoney 값 수정
				
				$(".item_confirm_container").hide();
			},
			error: function () { alert("실패"); }
		});
	});

	$(document).on("click", ".item_confirm_no", function() {
		$(".item_confirm_container").toggle();
	});

	$(document).on("click", ".item_next_page, .item_prev_page", function() {
		$(".item_dscrb, .item_list, .item_lotto_container").toggle();
	});

	$(document).on("click", ".lotto_info_btn, .lotto_info_exit_btn", function() {
		$(".item_lotto_info_container").toggle();
	});

	$(document).on("click", ".shop_exit", function () {
		location.href = "/";
	});

});   