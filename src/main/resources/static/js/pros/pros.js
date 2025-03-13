$(function() {
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

	// scroll 이벤트시 정보가 하단에 추가되는 함수
	$(".pros_list, .pros_list2").on('scroll', function() {
		const scrollPosition = $(this).scrollTop() + $(this).innerHeight();
		const scrollHeight = $(this)[0].scrollHeight;
		if (scrollPosition >= scrollHeight) {
			console.log('끝 지점에 도착했습니다!');
			const list = $(this).hasClass("pros_list");
			const category = $(".pros_list_category").text();
			const page = Number($(".pros_list_page").text()) + 1;
			$(".pros_list_page").text(page);
			$.ajax({
				url: "/admin/" + category + "/" + page,
				type: "POST",
				dataType: "json",
				success: function(data) {
					console.log(data);
					let hdata = ``;
					if(data != null){
					switch(category){
					case "player":
						for (let i = 0; i < data.length; i++) {
							let statusText = data[i].isActive == 0 ? '활성화' : '비활성화';
							hdata += `<div class="pros_search pros_items" data-id="${data[i].id}">
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
						for (let i = 0; i < data.length; i++) {
						hdata += `<div class="pros_items" data-id="${data[i].id}">
				                    <img src="${data[i].image}" class="pros_list_img">
				                  </div>`;						
						$(".pros_list2").append(hdata);
					}
					
				}
				}
				},
				error: function() {
					alert('실패');
				}
			})

		}
	});

	//새로고침 버튼 클릭
	$(document).on("click", ".pros_reload_btn", function() {
		const category = $(".pros_list_category").text();
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
						hdata += `<div class="pros_search pros_items" data-id=${data[i].id}>
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
					hdata += `<div class="pros_items" data-id="${data[i].id}">
			                  <img src="${data[i].image}" class="pros_list_img">
			                  </div>`;						
					}
					$(".pros_list2").append(hdata);
			}
			},
			error: function() {
				alert('실패');
			}
		})
	});
	
	// 검색시 검색결과가 나오는 함수
	$(document).on("click", ".pros_search_btn", function() {
		const category = $(".pros_list_category").text();
		const keyword = $(".pros_keyword").val().trim();
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
						hdata += `<div class="pros_search pros_items" data-id=${data[i].id}>
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
				case "pokemon":
					$(".pros_list2").children().hide();
					for (let i = 0; i < data.length; i++) {
						hdata += `<div class="pros_search" data-id="${data[i].id}">
				                    <img src="${data[i].image}" class="pros_list_img">
				                  </div>`;
					}
					$(".pros_list2").append(hdata);
				}
				$(".pros_search_flag").text("1");
			},
			error: function() {
				alert("실패");
			}
		})
	});

	//엔터 입력시 검색되게하는 함수
	$(document).on("keypress", ".pros_keyword", function(event) {
		if (event.keyCode == 13) { $(".pros_search_btn").click(); }
	});

	//검색결과 지우면 전체 검색결과 다시 노출
	$(document).on("input", ".pros_keyword", function() {
		const flag = $(".pros_search_flag").text();
		const input = $(".pros_keyword").val();
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
			url: "/admin/pokemon/view/" + id,
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
				alert("실패");
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
				$(".pros_get_coin").text(data.gameMoney);
				$(".pros_get_ruby").text(data.realMoney);
				const active = data.isActive == 0 ? "on" : "off";
				$(".pros_active_first").text(active);
				$(".pros_get_id").val(data.id);
				$(".pros_profile_view_container").show();
			},
			error: function() {
				alert("실패");
			}
		});
	});
	
	//수정하기 버튼 클릭
	$(document).on("click", ".pros_update_flag", function() {
			const category = $(".pros_list_category").text();
			switch (category) {
				case "player":
					const hashIndex = $(".pros_profile_name1").text().indexOf('#');
					$(".pros_get_name_input").val($(".pros_profile_name1").text().slice(0,hashIndex-1));
					$(".pros_get_lv_input").val($(".pros_profile_name2").text().slice(3));
					$(".pros_get_coin_input").val($(".pros_get_coin").text());
					$(".pros_get_ruby_input").val($(".pros_get_ruby").text());
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
		const name = $(".pros_get_name_input").val();
		if(confirm(name+"님 정보를 수정하시겠습니까?")){
			$.ajax({
				url:"/pros/update/"+category+"/id/"+id,
			})
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
				$(".pros_active_off").shoe();
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
