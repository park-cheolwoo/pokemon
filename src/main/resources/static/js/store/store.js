
$(function(){
		
		// 우측 상단 버튼 클릭 // 
		
		$(document).on("click",".healing",function(){
			if($(this).hasClass("cat_on")){
				$(this).attr("src","/store/images/회복아이템_no.png");
			} else{ $(this).attr("src","/store/images/회복아이템_yes.png");}
			$(this).toggleClass("cat_on");
		});
		
		$(document).on("click",".battle",function(){
			if($(this).hasClass("cat_on")){
				$(this).attr("src","/store/images/배틀아이템_no.png");
			} else{ $(this).attr("src","/store/images/배틀아이템_yes.png");}
			$(this).toggleClass("cat_on");
		});
		
		$(document).on("click",".monsterball",function(){
			if($(this).hasClass("cat_on")){
				$(this).attr("src","/store/images/몬스터볼_no.png");
			} else{ $(this).attr("src","/store/images/몬스터볼_yes.png");}
			$(this).toggleClass("cat_on");
		});
		
		// 우측 상단 버튼 클릭 // 
		
		
		// 왼쪽 아이템 설명창 //		
		
		$(document).on("click",".item_list_img",function(){
			//let id = ${temp.id};
			$(".item_table").find(".item_list_img").removeClass("selected");
			let img = $(this).attr("src");
			$(this).toggleClass("selected");
		$(".item_describe_img").attr("src",img);
		$(".item_name").text("몬스터볼");
		});
		
		// 왼쪽 아이템 설명창 //
		
		// 구매버튼 모달창 // 
		$(document).on("click",".item_buy_no",function(){
			$(".item_buy_container").css("display","none");
		});
		
		
		$(document).on("click",".item_buy",function(){
			$(".item_total_count").text("1");
			$(".item_total_cost").text("20");
			$(".item_buy_container").css("display","block");
		});
		
		
		$(document).on("click",".upper_yes",function(){
			let count = Number($(".item_total_count").text());
			let cost = 20*(count+1);
			$(".item_total_count").text(Number(count)+1);
			$(".item_total_cost").text(cost);
			$(".item_lower").attr("src","/store/images/하_yes.png");
			$(".item_lower").addClass("lower_yes");
			if(count == 98 ){
				$(".item_upper").attr("src","/store/images/상_no.png");
				$(".item_upper").toggleClass("upper_yes");
			}
		
			if ($(".item_total_count").text() == "0") {
			    $(".item_cost_warning").css("display", "inline");
			    $(".item_buy_yes").attr("src","/store/images/예_no.png");
			    $(".item_buy_yes").removeClass("buy_ok");
			} else {
			    $(".item_cost_warning").css("display", "none");
			    $(".item_buy_yes").attr("src","/store/images/예_yes.png");
			    $(".item_buy_yes").addClass("buy_ok");
			}
		});
			
		$(document).on("click",".lower_yes",function(){
			let count = Number($(".item_total_count").text());
			let cost = 20*(count-1);
			$(".item_total_count").text(Number(count)-1);
			$(".item_total_cost").text(cost);
			$(".item_upper").attr("src","/store/images/상_yes.png");
			$(".item_upper").addClass("upper_yes");
			if(count == 1 ){
				$(".item_lower").attr("src","/store/images/하_no.png");
				$(".item_lower").toggleClass("lower_yes");
			}
			if ($(".item_total_count").text() == "0") {
			    $(".item_cost_warning").css("display", "inline");
			    $(".item_buy_yes").attr("src","/store/images/예_no.png");
			    $(".item_buy_yes").removeClass("buy_ok");
			} else {
			    $(".item_cost_warning").css("display", "none");
			    $(".item_buy_yes").attr("src","/store/images/예_yes.png");
			    $(".item_buy_yes").addClass("buy_ok");
			}
		});
		
		$(document).on("click",".buy_ok",function(){
			let name = $(".item_name").text();
			let count = Number($(".item_total_count").text());
			let cost = Number($(".item_total_cost").text());
			$(".item_buy_container").css("display","none");
			$(".item_buy_name").text(name);
			$(".item_buy_count").text(count + " 개를");
			$(".item_buy_cost").text(cost + " 에");
			$(".item_confirm_container").css("display","block");
		})
		
		$(document).on("click",".item_confirm_no",function(){
			$(".item_confirm_container").css("display","none");
		})
		
			
		$(document).on("click",".item_describe_btn",function(){
			$(".item_describe_container").css("display","block");
		})
		
		$(document).on("click",".item_describe_exit",function(){
			$(".item_describe_container").css("display","none");
		})
		// 구매 모달창 //

		$(document).on("click",".item_next_page",function(){
			$(".item_main_container").css("display","none");
			$("body").css("background",'url("/store/images/아이템 뽑기.png") no-repeat center center / cover');
			$(".draw_main_container").css("display","block");
		})
	
	
	
	
	});
	
	
	