<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/store/css/store.css" >
    <script src="/store/js/store.js"></script>
</head>

<body>
	
<div class="item_main_container">
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
</div>

	<!-- 아이템 개수 모달  -->
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

	<!-- 아이템 구입 확인 모달  -->
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

	<!-- 아이템 설명 모달  -->
	<div class="item_describe_container">
	    <img src="/store/images/blur.png" class="blur" />
	    <img src="/store/images/아이템 설명.png" class="item_container_box">
	    <img src="/store/images/몬스터볼.png" class="item_describe_image">
	    <h4 class="item_describe_category text">회복아이템</h4>
	    <h4 class="item_describe_name text">몬스터볼</h4>
	    <img src="/store/images/동전.png" class="item_describe_coin2" />
	    <h4 class="item_describe_cost text">0,000원</h4>
	    <h4 class="item_describe_txt text">야생 포켓몬을 반드시 잡을 수 있는 최고 성능의 볼.</h4>
	    <img src="/store/images/닫기.png" class="item_describe_exit">
  	</div>

	<!-- 뽑기 페이지 전환 -->
	<div class="draw_main_container">
	  <h3 class="game_money text"> 0,000,000</h3>
	  <h3 class="real_money text"> 0,000,000</h3>
	  <img src="/store/images/지갑.png" class="pokecoins">
	  <img src="/store/images/닫기.png" class="exit">
	
	  <div class="lotto_button_wrap">


	  </div>
	</div>


</body>

</html>