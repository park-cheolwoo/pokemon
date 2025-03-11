$(function() {
	$(document).on("click", ".pros_user_btn", function() {
		location.href="/admin";
	});


	$(document).on("click", ".pros_data_btn", function() {
		location.href="/admin/data";
		});



	$(document).on("click", ".pros_dropbar_btn", function() {
		$(".pros_pokemon_select_box, .pros_pokemon_select_text, .pros_item_select_box, .pros_item_select_text, .pros_dungeon_select_box, .pros_dungeon_select_text").toggle();
	});


	$(document).on("click", ".pros_search_btn", function() {
				const data = $(".pros_list2");
				const keyword = $(".pros_keyword").val().trim();
				data.find(".pros_list_name").each(function() {
				           const profileText = $(this).text().toLowerCase();
						   console.log(profileText);
						   console.log(keyword);
						   console.log(profileText + " = "+keyword);
				           profileText.includes(keyword) ? $(this).closest(".pros_items").css("display", "block") 
						   : $(this).closest(".pros_items").css("display", "none");
				           });
				alert('검색 완료');
    });

	
	

	$(document).on("keypress",".pros_keyword",function(event){
		if(event.keyCode == 13){$(".pros_search_btn").click();}
	})
	
	
	$(document).on("click",".pros_home_btn",function(){
		location.href="/member/admin";
	})
	
	
	$(document).on("click", ".pros_update_flag", function() {
		const category = $(".get_category").val();
		switch(category){
			case "member":
				$(".pros_get_name_input").val($(".pros_profile_name1").text())
				$(".pros_get_lv_input").val($(".pros_profile_name2").text().slice(3))
				$(".pros_get_coin_input").val($(".pros_get_coin").text())
				$(".pros_get_ruby_input").val($(".pros_get_ruby").text())
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

	$(document).on("click", ".pros_update_exit", function() {
		let category = $(".get_category").val();
		let active = $(".pros_active_first").text();
		switch(category){
			case "member":
				$(".updateFrm, .pros_profile_name1, .pros_profile_name2, .pros_get_coin, .pros_get_ruby, .pros_intro").toggle();
				break;
			case "pokemon":
				$(".updateFrm, .pros_profile_name1, .pros_intro").toggle();
			}
			
		if (active == "on") {
			$(".pros_active_on").css("display", "block");
			$(".pros_active_off").css("display", "none");
		} else {
			$(".pros_active_on").css("display", "none");
			$(".pros_active_off").css("display", "block");
		}
		$(".pros_update_btn").addClass("pros_update_flag");
		$(".pros_active_on, .pros_active_off").removeClass("active_update");
	});

	$(document).on("click", ".active_update", function() {
		let active = $(".pros_active").val();
		if (active == "on") {
			$(".pros_active").val("off");
		} else {
			$(".pros_active").val("on");
		}
		$(".pros_active_on, .pros_active_off").toggle();
	})


	$(document).on("click", ".pros_update_confirm_btn", function() {
		console.log($("pros_active").text())
		alert("수정을 진행합니다.");
	})
	
	$(document).on("click", ".pros_reload_btn", function() {
	    alert("새로고침이 완료되었습니다.");
	});

	
	
	$(document).on("click",".pros_list_img",function(){
		location.href="/admin";
	});
	
	$(document).on("click",".pros_pokemon_btn",function(){
		location.href="/admin/pokemon";
	});
	
	$(document).on("click",".pros_home_btn",function(){
		location.href="/admin";
	});
	
	$(".pros_list2").on('scroll', function () {
		console.log("스크롤했음");
	    const scrollPosition = $(this).scrollTop()+$(this).innerHeight();
		const scrollHeight = $(this)[0].scrollHeight;
		console.log("scrollPosition : "+scrollPosition);
		console.log("scrollHeight : "+scrollHeight);

	    if (scrollPosition >= scrollHeight) {
	        console.log('끝 지점에 도착했습니다!');
			const page = Number($(".pros_list_page").text())+96;
			
	    }
	});
	
	
	/* 임시저장 */
	/*
	
	$(document).on("click", ".pros_search_btn", function() {
			const rows = $(".pros_rows");
			const keyword = $(".pros_keyword").text();
			rows.each(function() {
			    const profileText = $(this).find(".pros_profile").text().toLowerCase(); 
			    const searchKeyword = keyword.toLowerCase().trim();
			    $(this).css("display", profileText.includes(searchKeyword) ? "block" : "none");
			});

			alert('검색 완료');
		});	
	
	$(document).on("click",".pros_more_btn",function(){
			location.href="/member/admin2";
		})
	*/
});
