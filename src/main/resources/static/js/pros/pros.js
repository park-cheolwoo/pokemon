$(function() {
	//전역변수 선언
	const category = $(".pros_list_category").text();
	const input = $(".pros_keyword").val();
	const flag = $(".pros_search_flag").text();
	
	$(document).on("click", ".pros_player_btn", function() {
		location.href = "/admin/player";
	});

	$(document).on("click", ".pros_data_btn", function() {
		location.href = "/admin/data";
	});

	$(document).on("click", ".pros_home_btn", function() {
		location.href = "/admin/player";
	});
	
	$(document).on("click", ".pros_dropbar_btn", function() {
		$(".pros_pokemon_select_box, .pros_pokemon_select_text, .pros_item_select_box, .pros_item_select_text, .pros_dungeon_select_box, .pros_dungeon_select_text").toggle();
	});

	$(document).on("click", ".pros_pokemon_btn", function() {
		location.href = "/admin/pokemon";
	});
	
	$(document).on("click", ".pros_item_btn", function() {
		location.href = "/admin/item";
	});

	// 데이터 종류에 따라 버튼 색상 변경 함수
	switch(category){
		case "pokemon":
			$(".pros_pokemon_select_box").attr("src","/images/green-bar.png");
			break;
		case "item":
			$(".pros_item_select_box").attr("src","/images/green-bar.png");
			break;
		case "dungeon":
			$(".pros_dungeon_select_box").attr("src","/images/green-bar.png");
			break;
	}
	
	
	// scroll 이벤트시 정보가 하단에 추가되는 함수
	$(".pros_list_wrap, .pros_list2").on('scroll', function() {
		const scrollPosition = $(this).scrollTop() + $(this).innerHeight();
		const scrollHeight = $(this)[0].scrollHeight;
		const page = Number($(".pros_list_page").text()) + 1;
		console.log(scrollHeight);
		console.log(scrollPosition);
		if (scrollPosition >= scrollHeight) {
			console.log('끝 지점에 도착했습니다!');	
			console.log("url : "+"/admin/" + category + "/" + page);
			$.ajax({
				url: "/admin/" + category + "/" + page,
				type: "POST",
				data : {"page":page},
				dataType: "json",
				success: function(data) {
					console.log(data);
					let hdata = ``;
					if(data != null){
					$(".pros_list_page").text(page);
					console.log(category);
					switch(category){
					case "player":
						for (let i = 0; i < data.length; i++) {
							let statusText = data[i].isActive == 0 ? '활성화' : '비활성화';
							hdata += `<div class="pros_search pros_items" data-id="${data[i].id}" data-nickname="${data[i].nickname}">
									   <div class="pros_profile_frame">
								       <img src="/images/store/item-frame.png" class="pros_profile_frame_img">
									   <img src="/images/pros/avarter.png" class="pros_profile_img">
									   </div>
									   <div class="pros_profile_wrap">
									   <img src="/images/pros/wood-bar2.png" class="pros_profile_bar">
									   <h4 class="pros_profile">${data[i].nickname }</h4>
									   <h4 class="pros_profile_info">${statusText}</h4>
							           </div>
									   <img src="/images/pros/more.png" class="pros_more_btn">
				              		   </div>`;
					}
					$(".pros_list_wrap").append(hdata);	
					break;
					default:
						for (let i = 0; i < data.length; i++) {
						hdata += `<div class="pros_items" data-id="${data[i].id}" data-name="${data[i].name}">
				                    <img src="${data[i].image}" class="pros_list_img">
				                  </div>`;						
					}
					$(".pros_list2").append(hdata);
				}
				}
				},
				error: function() {
					alert('스크롤 실패');
				}
			})

		}
	});

	//새로고침 버튼 클릭
	$(document).on("click", ".pros_reload_btn", function() {
		console.log("url : "+"/admin/" + category + "/1");
		$.ajax({
			url: "/admin/" + category + "/1",
			type: "POST",
			dataType: "json",
			success: function(data) {
				alert("새로고침 하였습니다.");
				console.log(data);
				let hdata = ``;
				$(".pros_items").remove();
				switch(category){
					case "player":
					$(".pros_list").scrollTop(0);
					for (let i = 0; i < data.length; i++) {
						let statusText = data[i].isActive == 0 ? '활성화' : '비활성화';
						hdata += `<div class="pros_search pros_items" data-id=${data[i].id} data-nickame="${data[i].nickname}">
								   <div class="pros_profile_frame">
							       <img src="/images/store/item-frame.png" class="pros_profile_frame_img">
								   <img src="/images/pros/avarter.png" class="pros_profile_img">
								   </div>
								   <div class="pros_profile_wrap">
								   <img src="/images/pros/wood-bar2.png" class="pros_profile_bar">
								   <h4 class="pros_profile">${data[i].nickname }</h4>
								   <h4 class="pros_profile_info">${statusText}</h4>
						           </div>
								   <img src="/images/pros/more.png" class="pros_more_btn">
			              		   </div>`;
						}
						$(".pros_list").append(hdata);
				break;
				default:
					$(".pros_list2").scrollTop(0);
					for (let i = 0; i < data.length; i++) {
					hdata += `<div class="pros_items" data-id="${data[i].id}" data-name="${data[i].name}">
			                  <img src="${data[i].image}" class="pros_list_img">
			                  </div>`;						
					}
					$(".pros_list2").append(hdata);
			}
			},
			error: function() {
				alert('새로고침 실패');
			}
		})
	});
	
	// 검색시 검색결과가 나오는 함수
	$(document).on("click", ".pros_search_btn", function() {
		const keyword = $(".pros_keyword").val();
		console.log("/admin/" + category + "/search/" + keyword);
		$.ajax({
			url: "/admin/" + category + "/search/" + keyword,
			type: "POST",
			data: { "keyword": keyword },
			dataType: "json",
			success: function(data) {
				alert("성공");
				console.log(data);
				let hdata = ``;
				switch(category){
				case "player":
					$(".pros_list").children().hide();
					for (let i = 0; i < data.length; i++) {
						let statusText = data[i].isActive == 0 ? '활성화' : '비활성화';
						hdata += `<div class="pros_search pros_items" data-id="${data[i].id}" data-nickname="${data[i].nickname}">
						        	<div class="pros_profile_frame">
						        		<img src="/images/store/item-frame.png" class="pros_profile_frame_img">
						         	    <img src="/images/pros/avarter.png" class="pros_profile_img">
						        	</div>
						       		<div class="pros_profile_wrap">
						       		<img src="/images/pros/wood-bar2.png" class="pros_profile_bar">
						            <h4 class="pros_profile">${data[i].nickname }</h4>
						            <h4 class="pros_profile_info">
						            	${statusText}
						            </h4>
						       		</div>
						        	<img src="/images/pros/more.png" class="pros_more_btn">
						        </div>`;
					}
					$(".pros_list").append(hdata);
					break;
				default:
					$(".pros_list2").children().hide();
					for (let i = 0; i < data.length; i++) {
						hdata += `<div class="pros_search" data-id="${data[i].id}" data-name="${data[i].name}">
				                    <img src="${data[i].image}" class="pros_list_img">
				                  </div>`;
					}
					$(".pros_list2").append(hdata);
				}
				$(".pros_search_flag").text("1");
			},
			error: function() {
				alert("검색 실패");
			}
		})
	});

	//엔터 입력시 검색되게하는 함수
	$(document).on("keypress", ".pros_keyword", function(event) {
		if (event.keyCode == 13) { $(".pros_search_btn").click(); }
	});

	//검색결과 지우면 전체 검색결과 다시 노출
	$(document).on("input", ".pros_keyword", function() {
		if (flag == "1" && input == "") {
			$(".pros_search").remove();
			$(".pros_items").show();
			$(".pros_search_flag").text("0");
		}
	})

	// 포켓몬 이미지 클릭시 상세보기 전환
	$(document).on("click", ".pros_list_img", function() {
		const id = $(this).parent().data("id");
		$.ajax({
			url: "/admin/"+category+"/view/" + id,
			type: "POST",
			data: { "id": id },
			dataType: "json",
			success: function(data) {
				//alert("성공");
				console.log(data);
				$(".pros_profile_name1").text(data.name);
				$(".pros_profile_name2").text(data.genus);
				$(".pros_profile_img2").attr("src", data.image);
				$(".pros_update").text("수정일 : " + data.updatedAt.slice(0, 10));
				$(".pros_create").text("등록일 : " + data.createdAt.slice(0, 10));
				$(".pros_intro").text(data.flavorText);
				$(".pros_get_id").val(data.id);
				$(".pros_profile_view_container").show();
			},
			error: function() {
				alert("상세보기 전환 실패");
			}
		});
	});
	
	// 플레이어 상세보기 페이지 전환
	$(document).on("click", ".pros_more_btn", function() {
		const id = $(this).parent().data("id");
		console.log("url : "+"/admin/player/view/" + id )
		$.ajax({
			url: "/admin/player/view/"+id,
			type: "POST",
			data: { "id":id },
			dataType: "json",
			success: function(data) {
				console.log(data);
				$(".pros_profile_name1").text(data.nickname + " "+data.tag);
				$(".pros_profile_name2").text("LV "+data.lv);
				$(".pros_update").text("수정일 : "+data.updatedAt.slice(0, 10));
				$(".pros_create").text("등록일 : " + data.createdAt.slice(0, 10));
				$(".pros_intro").text(data.profile);
				$(".pros_get_coin").text(data.gameMoney.toLocaleString());
				$(".pros_get_ruby").text(data.realMoney.toLocaleString());
				$(".pros_get_id").val(data.id);
				const active = data.isActive == 0 ? "on" : "off";
				$(".pros_active_first").text(active);
				if (active == "on") { 
					$(".pros_active_on").show();
					$(".pros_active_off").hide();
				} else {
					$(".pros_active_on").hide();
					$(".pros_active_off").show();
				}
				$(".pros_get_id").val(data.id);
				$(".pros_exe_txt").text("exp ( "+data.experience+" / 100 )")
				$(".pros_exe_bar").attr("width", (518 / 100 * Number(data.experience))+"px")
				$(".pros_profile_view_container").show();
			},
			error: function() {
				alert("상세보기 전환 실패");
			}
		});
	});
	
	//수정하기 버튼 클릭
	$(document).on("click", ".pros_update_flag", function() {
			switch (category) {
				case "player":
					const hashIndex = $(".pros_profile_name1").text().indexOf('#');
					$(".pros_get_name_input").val($(".pros_profile_name1").text().slice(0,hashIndex-1));
					$(".pros_get_lv_input").val($(".pros_profile_name2").text().slice(3));
					$(".pros_get_coin_input").val($(".pros_get_coin").text().replace(",",""));
					$(".pros_get_ruby_input").val($(".pros_get_ruby").text().replace(",", ""));
					$(".pros_intro_input").val($(".pros_intro").text());
					$(".pros_profile_name1, .pros_profile_name2, .pros_get_coin, .pros_get_ruby, .pros_intro").toggle();
					break;
				case "pokemon":
					$(".pros_get_name_input").val($(".pros_profile_name1").text());
					$(".pros_intro_input").val($(".pros_intro").text());
					$(".pros_profile_name1, .pros_intro").toggle();
			}
			$(".updateFrm").toggle();
			$(".pros_update_btn").removeClass("pros_update_flag");
			$(".pros_active_on, .pros_active_off").addClass("active_update");
		});
	
	//수정할 때 활성화 on/off 버튼 클릭
		$(document).on("click", ".active_update", function() {
				let active = $(".pros_active").val();
				if (active == "on") {
					$(".pros_active").val("off");
				} else {
					$(".pros_active").val("on");
				}
				$(".pros_active_on, .pros_active_off").toggle();
			});	
		
	//수정하기 버튼 클릭
	$(document).on("click", ".pros_update_confirm_btn", function() {
		const id = $(".pros_get_id").val();
		const nickname = $(".pros_get_name_input").val();
		const lv = $(".pros_get_lv_input").val();
		const coin = $(".pros_get_coin_input").val();
		const ruby = $(".pros_get_ruby_input").val();
		const intro = $(".pros_intro_input").val();
		let active = $(".pros_active").val();
		if(id == "" || nickname == "" || lv == "" || coin == "" || ruby == "" || intro == ""){	
			alert("모든 항목을 입력해주세요");
			return false;
		}
		if (Number(coin) < 0 || Number(ruby) < 0 || Number(lv) < 1) {
			alert("숫자는 0 이상, 레벨은 1 이상이어야 합니다.");
			return false;
		}
		switch (category) {
			case "player":
				if (active == "on") { active = 0; } else { active = 1; }
				if (confirm(nickname + "님 정보를 수정하시겠습니까?")) {
					$.ajax({
						url: "/admin/update/" + category + "/id/" + id,
						type: "POST",
						data: { "id": id, "nickname": nickname, "lv": lv, "gamemoney": coin, "realmoney": ruby, "profile": intro, "isactive": active },
						success: function (data) {
							alert("수정되었습니다.");
						},
						error: function () {
								alert("수정 실패");
							}
						})
				}
		}
	})		
				
	// 수정 취소 버튼 클릭
	$(document).on("click", ".pros_update_exit", function() {
			const category = $(".pros_list_category").text();
			let active = $(".pros_active_first").text();
			$(".updateFrm").hide();
			switch (category) {
				case "player":
					$(".pros_profile_name1, .pros_profile_name2, .pros_get_coin, .pros_get_ruby, .pros_intro").show();
					break;
				case "pokemon":
					$(".pros_profile_name1, .pros_intro").show();
			}

			if (active == "on") {
				$(".pros_active_on").show();
				$(".pros_active_off").hide();
			} else {
				$(".pros_active_on").hide();
				$(".pros_active_off").show();
			}
			$(".pros_update_btn").addClass("pros_update_flag");
			$(".pros_active_on, .pros_active_off").removeClass("active_update");
		});
	
	// 상세보기 취소 버튼 클릭
	$(document).on("click", ".pros_profile_view_exit", function() {
		$(".pros_update_exit").click();
		$(".pros_profile_view_container").hide();
	});

});
