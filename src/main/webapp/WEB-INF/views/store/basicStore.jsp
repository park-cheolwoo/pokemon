<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/store/css/store.css" ></style>
</head>

<body>
  <h3 class="game_money text"> 0,000,000</h3>
  <h3 class="real_money text"> 0,000,000</h3>
  <img src="/store/images/지갑.png" class="pokecoins">
  <img src="/store/images/닫기.png" class="exit">

  <div class="item_describe">
    <img src="/store/images/마스터볼.png" class="item_describe_img"/>
    <h4 class="item_name text">마스터볼</h4>
    <img src="/store/images/동전.png" class="item_describe_coin">
    <h4 class="item_cost text">0,000</h4>
    <img src="/store/images/설명.png" class="item_describe_btn">
    <img src="/store/images/구매.png" class="item_buy">
  </div>
  <div class="item_list">
    <img src="/store/images/회복아이템_no.png" class="item_category healing">
    <img src="/store/images/배틀아이템_no.png" class="item_category battle">
    <img src="/store/images/몬스터볼_yes.png" class="item_category monsterball cat_on">

    
  <div class="item_table_wrap">
    <table class="item_table">
    
    <!-- 반복  -->
     <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" id="1" class="item_list_img selected"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <!-- 반복  -->
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
    <tr class="item_row">
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td><img src="/store/images/몬스터볼.png" class="item_list_img"></td>
      <td>
     </tr>
   
   	
   
   
   
    </table>
	  </div>
	  <img src="/store/images/우_yes.png" class="item_next_page">
	</div>

	<div class="item_buy_container">
	    <img src="/store/images/blur.png" class="blur" />
	    <img src="/store/images/구매개수.png" class="item_container_box">
	    <h4 class="item_total_cost text">0</h4>
	    <h4 class="item_cost_warning text">코인이 부족합니다!</h4>
	    <img src="/store/images/하_yes.png" class="item_lower lower_yes">
	    <h4 class="item_total_count text">0</h4>
	    <img src="/store/images/상_yes.png" class="item_upper upper_yes">
	    <img src="/store/images/예_yes.png" class="item_buy_yes buy_ok">
	    <img src="/store/images/닫기.png" class="item_buy_no">
	 </div>

	<div class="item_confirm_container">
	    <img src="/store/images/blur.png" class="blur" />
	    <img src="/store/images/구매확인.png" class="item_container_box">
	    <div class="item_buy_wrap">
		    <h4 class="item_buy_name text">아이템 이름</h4>
			<br/>
		    <h4 class="item_buy_count text">
		    	<span class="item_buy_count_span text">1</span>
		    	개를
		    </h4>
			<br/>
			<div class="item_buy_cost_wrap">
		    <img src="/store/images/동전.png" class="item_buy_coin" />
		    <h4 class="item_buy_cost text">
		    	<span class="item_buy_cost_span text">2000</span>
		    	에
		    </h4>
			</div>
		    <br/>
		    <h4 class="item_buy_txt3 text">구매하시겠습니까?</h4>
		</div>
	    <img src="/store/images/예_yes.png" class="item_confirm_yes">
	    <img src="/store/images/닫기.png" class="item_confirm_no">
  	</div>

	


<script>

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
			
		})
		// 구매 모달창 //

	});
</script>

</body>

</html>