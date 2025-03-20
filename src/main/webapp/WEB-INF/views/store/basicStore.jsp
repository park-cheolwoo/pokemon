<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/store/store.css">
  <link type="text/css" rel="stylesheet" href="/css/styles.css"> <!-- 공통 스타일 -->
  <script src="/js/store/store.js"></script>
</head>

<body>
  <script>
    const session_id = "${session_id}";
  </script>
  <div class="item_main_container">
    <img class="item_main_title" src="/images/yellow-bar.png">
    <h4 class="item_main_title_txt text">상점</h4>


    <img class="item_top_coin" src="/images/store/coin.png">
    <img class="item_top_coin_box" src="/images/store/wood-vacant-bar.png">
    <h4 class="game_money text"> <fmt:formatNumber value = "${session_gameMoney}" type="number" pattern="#,###" /></h4>
      <img class="item_top_ruby" src="/images/store/ruby.png">
      <img class="item_top_ruby_box" src="/images/store/wood-vacant-bar.png">
      <h4 class="real_money text"> <fmt:formatNumber value="${session_realMoney}" type="number" pattern="#,###" /></h4>
        <img src="/images/wallet.png" class="pokecoins">
        <img src="/images/close.png" class="exit shop_exit">


        <div class="item_dscrb">
          <img src="/images/store/item-container2.png" class="item_dscrb_frame">
          <img src="" class="item_dscrb_img" />
          <div class="item_dscrb_blur_box"></div>
          <h4 class="item_dscrb_name text"></h4>
          <img src="/images/store/coin.png" class="item_dscrb_coin">
          <h4 class="item_cost text text"></h4>
          <img src="/images/yellow-bar.png" class="item_dscrb_btn item_dscrb_btn_box">
          <h4 class="item_dscrb_btn item_dscrb_btn_title text">설명</h4>
          <img src="/images/yellow-bar.png" class="item_buy_btn item_buy_btn_box">
          <h4 class="item_buy_btn item_buy_btn_text text">구매</h4>
        </div>


        <div class="item_list">
          <div class="item_category_monsterball_wrap">
          <img src="/images/yellow-bar.png" class="item_category_monsterball item_category_monsterball_box">
          <h4 class="item_category_monsterball item_category_monsterball_text text">몬스터볼</h4>
          </div>
          <div class="item_category_battle_wrap">
          <img src="/images/yellow-bar.png" class="item_category_battle item_category_battle_box">
          <h4 class="item_category_battle item_category_battle_text text">배틀아이템</h4>
          </div>
          <div class="item_category_grow_wrap">
          <img src="/images/yellow-bar.png" class="item_category_grow item_category_grow_box">
          <h4 class="item_category_grow item_category_grow_text text">육성아이템</h4>
        </div>
        

          <img class="item_table_frame" src="/images/store/item-container3.png">
            <div class="item_table">
            <c:forEach items='${list}' var="list">
            <div class="shop_items" data-id = '${list.id}'><img src='${list.image}' class="item_list_img "><h4 class="item_list_name">${list.name}</h4><h4 class="item_list_cost">${list.cost}</h4><h4 class="item_list_cat">${list.categoryId}</h4></div>
            </c:forEach>
            </div>
  	<img src="/images/store/yellow-right.png" class="item_next_page">
  	</div>
 </div>

  <!-- 아이템 개수 모달  -->
  <div class="item_buy_container">
    <h4 class="item_buy_cost" style="display:none"></h4>
    <div class="item_blur"></div>
    <img src="/images/item-container.png" class="item_frame">
    <h4 class="item_buy_title text">구매개수</h4>
    <img src="/images/store/coin.png" class="item_buy_coin">
    <h4 class="item_total_cost text"></h4>
    <h4 class="item_cost_warning text">코인이 부족합니다!</h4>
    <img src="/images/store/yellow-down.png" class="item_lower lower_yes">
    <img src="/images/store/item-frame.png" class="item_total_count_frame">
    <input type="text" class="item_total_count text" value="1" maxlength="2">
    <img src="/images/store/yellow-up.png" class="item_upper upper_yes">
    <img src="/images/store/green-check.png" class="item_buy_yes buy_ok">
    <img src="/images/close.png" class="item_buy_no">
  </div>

  <!-- 아이템 구입 확인 모달  -->
  <div class="item_confirm_container">
    <div class="item_blur"></div>
    <img src="/images/item-container.png" class="item_frame" />
    <div class="item_comfirm_wrap">
      <h4 class="item_confirm_name text">아이템 이름</h4>
      <br />
      <h4 class="item_confirm_count text">
      </h4>
      <br />
      <div class="item_confirm_cost_wrap">
        <img src="/images/store/coin.png" class="item_confirm_coin" />
        <h4 class="item_confirm_cost text">
        </h4>
      </div>
      <br />
      <h4 class="item_confirm_text text">구매하시겠습니까?</h4>
    </div>
    <img src="/images/store/green-check.png" class="item_confirm_yes">
    <img src="/images/close.png" class="item_confirm_no">
  </div>

  <!-- 아이템 설명 모달  -->
  <div class="item_describe_container">
    <div class="item_blur"></div>
    <img src="/images/item-container.png" class="item_frame">
    <img src="/images/store/item-frame.png" class="item_describe_image_frame">
    <img src="" class="item_describe_image">
    <img src="/images/yellow-bar.png" class="item_describe_category_box">
    <h4 class="item_describe_category text"></h4>
    <h4 class="item_describe_name text"></h4>
    <img src="/images/store/coin.png" class="item_describe_coin" />
    <h4 class="item_describe_cost text"></h4>
    <h4 class="item_describe_txt text"></h4>
    <img src="/images/close.png" class="item_describe_exit">
  </div>

  <!-- 뽑기 페이지 전환 -->

	  <div class="item_lotto_container">
	  	<img src="/images/pros/more.png" class="lotto_info_btn">
	  
	  	<img src="/images/item-container.png" class="item_lotto1_frame" >
	  	<img src="/images/store/green-egg.png" class="item_lotto1_img">
	  	<h4 class="item_lotto1_txt text">일반 포켓몬 뽑기</h4>
	  	<img src="/images/yellow-bar.png" class="item_lotto1_btn item_lotto1_btn_box">
	  	<h4 class="item_lotto1_btn item_lotto1_btn_txt text">하루 1회 무료 뽑기</h4>
	  	
	  	<img src="/images/item-container.png" class="item_lotto2_frame" >
	  	<img src="/images/store/backpack.png" class="item_lotto2_img">
	  	<h4 class="item_lotto2_txt text">일반 장비 뽑기</h4>
	  	<img src="/images/yellow-bar.png" class="item_lotto2_btn item_lotto2_btn_box">
	  	<img src="/images/store/coin.png" class="item_lotto2_btn item_lotto2_btn_coin">
	  	<h4 class="item_lotto2_btn item_lotto2_btn_txt text">2,000</h4>
	  	
	  	<img src="/images/item-container.png" class="item_lotto3_frame" >
	  	<img src="/images/store/purple-egg.png" class="item_lotto3_img">
	  	<h4 class="item_lotto3_txt text">프리미엄 포켓몬 뽑기</h4>
	  	<img src="/images/yellow-bar.png" class="item_lotto3_btn item_lotto3_btn_box">
	  	<img src="/images/store/ruby.png" class="item_lotto3_btn item_lotto3_btn_ruby">
	  	<h4 class="item_lotto3_btn item_lotto3_btn_txt text">500</h4>
	  
	  	<img src="/images/store/yellow-left.png" class="item_prev_page">
	  </div>
	  
  	  <div class="item_lotto_info_container">
  		<div class="item_blur"></div> <!-- 30 -->
  		<img src="/images/store/item-container3.png" class="item_lotto_frame">
  		<img src="/images/green-bar.png" class="normal_btn normal_btn_box">
  		<h4 class="normal_btn_txt text">일반 포켓몬</h4>
  		<img src="/images/yellow-bar.png" class="equip_btn equip_btn_box">
  		<h4 class="equip_btn_txt text">일반 장비</h4>
  		<img src="/images/yellow-bar.png" class="pre_btn pre_btn_box">
  		<h4 class="pre_btn_txt text">프리미엄 포켓몬</h4>
  		
  		<h4 class="lotto_info_title text">일반 포켓몬 뽑기 확률 안내</h4>
  		<div class="lotto_info_wrap">
  			<h4 class="lotto_info_txt text">
  				노말 타입 80%<br/><br/>
  				불 타입 5%<br/><br/>
  				물 타입 5%<br/><br/>
  				전기 타입 5% <br/><br/>

  			</h4>
  		</div>
  		<img src="/images/close.png" class="lotto_info_exit_btn">
      </div>
	  
	  
	  
	  

	<script src="/js/play/ingame.js"></script>
</body>

</html>