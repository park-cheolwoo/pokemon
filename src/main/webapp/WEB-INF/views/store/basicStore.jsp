<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/store/store.css">
  <script src="/js/store/store.js"></script>
</head>

<body>

  <div class="item_main_container">
    <img class="item_main_title" src="/images/yellow-bar.png">
    <h4 class="item_main_title_txt text">상점</h4>


    <img class="item_top_coin" src="/images/store/coin.png">
    <img class="item_top_coin_box" src="/images/store/wood-vacant-bar.png">
    <h4 class="game_money text"> 0,000,000</h3>
      <img class="item_top_ruby" src="/images/store/ruby.png">
      <img class="item_top_ruby_box" src="/images/store/wood-vacant-bar.png">
      <h4 class="real_money text"> 0,000,000</h3>
        <img src="/images/wallet.png" class="pokecoins">
        <img src="/images/close.png" class="exit">


        <div class="item_dscrb">
          <img src="/images/store/item-container2.png" class="item_dscrb_frame">
          <img src="/images/masterball.png" class="item_dscrb_img" />
          <div class="item_dscrb_blur_box"></div>
          <h4 class="item_dscrb_name text">마스터볼</h4>
          <img src="/images/store/coin.png" class="item_dscrb_coin">
          <h4 class="item_cost text text">0,000</h4>
          <img src="/images/yellow-bar.png" class="item_dscrb_btn item_dscrb_btn_box">
          <h4 class="item_dscrb_btn item_dscrb_btn_title text">설명</h4>
          <img src="/images/yellow-bar.png" class="item_buy_btn item_buy_btn_box">
          <h4 class="item_buy_btn item_buy_btn_text text">구매</h4>


        </div>


        <div class="item_list">
          <img src="/images/yellow-bar.png" class="item_category_healing item_category_healing_box">
          <h4 class="item_category_healing item_category_healing_text text">회복아이템</h4>
          <img src="/images/yellow-bar.png" class="item_category_battle item_category_battle_box">
          <h4 class="item_category_battle item_category_battle_text text">배틀아이템</h4>
          <img src="/images/green-bar.png" class="item_category_monsterball item_category_monsterball_box">
          <h4 class="item_category_monsterball item_category_monsterball_text text cat_on">몬스터볼</h4>
        

          <img class="item_table_frame" src="/images/store/item-container3.png">
          <div class="item_table_wrap">
            <table class="item_table">

              <!-- 반복  -->
              <tr class="item_row">
                <td><img src="/images/monsterball.png" id="1" class="item_list_img selected"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td><img src="/images/monsterball.png" class="item_list_img"></td>
                <td>
              </tr>
              <!-- 반복  -->
              <tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr>
              <tr class="item_row">
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td><img src="/images/superball.png" class="item_list_img"></td>
                <td>
              </tr>
              <tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr><tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr><tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr><tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr><tr class="item_row">
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td><img src="/images/masterball.png" class="item_list_img"></td>
                <td>
              </tr>

            </table>
          </div>
          <img src="/images/store/yellow-right.png" class="item_next_page">
        </div>
  </div>

  <!-- 아이템 개수 모달  -->
  <div class="item_buy_container">
    <div class="item_blur"></div>
    <img src="/images/item-container.png" class="item_frame">
    <h4 class="item_buy_title text">구매개수</h4>
    <img src="/images/store/coin.png" class="item_buy_coin">
    <h4 class="item_total_cost text">0</h4>
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
        <span class="item_confirm_count_span text">1</span>
        개를
      </h4>
      <br />
      <div class="item_confirm_cost_wrap">
        <img src="/images/store/coin.png" class="item_confirm_coin" />
        <h4 class="item_confirm_cost text">
          <span class="item_confirm_cost_span text">2000</span>
          에
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
    <img src="/images/monsterball.png" class="item_describe_image">
    <img src="/images/yellow-bar.png" class="item_describe_category_box">
    <h4 class="item_describe_category text">회복아이템</h4>
    <h4 class="item_describe_name text">몬스터볼</h4>
    <img src="/images/store/coin.png" class="item_describe_coin" />
    <h4 class="item_describe_cost text">0,000원</h4>
    <h4 class="item_describe_txt text">야생 포켓몬을 반드시 잡을 수 있는 최고 성능의 볼.</h4>
    <img src="/images/close.png" class="item_describe_exit">
  </div>

  <!-- 뽑기 페이지 전환 -->
  <!-- <div class="draw_main_container">
	  <h3 class="game_money text"> 0,000,000</h3>
	  <h3 class="real_money text"> 0,000,000</h3>
	  <img src="/images/wallet.png" class="pokecoins">
	  <img src="/images/닫기.png" class="exit">
	
	  <div class="lotto_button_wrap">


	  </div>
	</div> -->



</body>

</html>