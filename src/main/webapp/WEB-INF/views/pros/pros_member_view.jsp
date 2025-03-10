<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href="/css/pros/pros.css">
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
  <link rel="stylesheet" type="text/css" href="/css/reset.css">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="/js/pros/pros.js"></script>
  
</head>

<body>
  <img src="/images/pros/gear.png" class="pros_gear" />
  <img src="/images/pros/yellow-ellipse-bar.png" class="pros_user_btn pros_user_btn_box_on" />
  <h4 class="pros_user_btn pros_user_btn_text_on text">유저 정보</h4>
  <img src="/images/pros/wood-ellipse-bar.png" class="pros_data_btn pros_data_btn_box_off" />
  <h4 class="pros_data_btn pros_data_btn_text_off text">데이터 정보</h4>
  
  
  <div class="pros_profile_view_container">
  <img src="/images/container.png" class="pros_container_frame">
  
  
  <img src="/images/pros/setting.png" class="pros_update_flag pros_update_btn" /> 
  <img src="/images/pros/retry.png" class="pros_reload_btn" />
  <img src="/images/pros/list.png" class="pros_dropbar_btn" />
  <img src="/images/yellow-bar.png" class="pros_pokemon_select_box" />
  <h4 class="pros_pokemon_select_text text">포켓몬</h4>
  <img src="/images/yellow-bar.png" class="pros_item_select_box" />
  <h4 class="pros_item_select_text text">아이템</h4>
  <img src="/images/yellow-bar.png" class="pros_dungeon_select_box" />
  <h4 class="pros_dungeon_select_text text">던전</h4>


  <img src="/images/store/item-frame.png" class="pros_profile_frame_img2" />
  <img src="/images/pros/avarter.png" class="pros_profile_img2">
  <h4 class="pros_profile_name1 text">한지우</h4>
  <h4 class="pros_profile_name2 text">LV 32</h4>
  
  <!-- 내용 부분 -->

  <img src="/images/pros/wood-bar2.png" class="pros_exe_frame">
  <img src="/images/pros/exe-bar.png" class="pros_exe_bar">
  <h4 class="pros_exe_txt text">exp ( 73 / 100 )</h4>
  

    <h4 class="pros_update text">수정일 : 2025/02/25</h4>
    <h4 class="pros_create text">가입일 : 2025/02/25</h4>

	
    <img src="/images/store/coin.png" class="pros_coin">
    <h4 class="pros_get_coin text">10,000</h4>

    <img src="/images/store/ruby.png" class="pros_ruby">
    <h4 class="pros_get_ruby text">10,000</h4>
    
    <h4 class="pros_active_txt text">활성화</h4>
    <img src="/images/store/item-frame.png" class="pros_active_frame">
    <h4 class="pros_active_first" style="display:none">on</h4>
    <img src="/images/store/green-check.png" class="pros_active_on" />
    <img src="/images/close.png" class="pros_active_off" /> 
    

    <h4 class="pros_intro text">자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.</h4>
	
	
	
	<form name="updateFrm" method="post" class="updateFrm">
	<input type="hidden" name="get_category" class="get_category" value="member" />
	<input type="hidden" name="get_active" class="pros_active" value="on" />
	<input type='text' name="get_name" class="pros_get_name_input" maxlength=6>
	<input type='text' name="get_lv"  class="pros_get_lv_input" maxlength=3>
	<input type='text' name="get_coin"  class="pros_get_coin_input">
    <input type='text' name="get_ruby"  class="pros_get_ruby_input">
	<textarea class="pros_intro_input"></textarea>
	<img src="/images/store/green-check.png" class="pros_update_confirm_btn">
  	<img src="/images/close.png" class="pros_update_exit">
	</form>

  </div>
  <!-- 모달 끝 -->

  <img src="/images/pros/home.png" class="pros_home_btn">
</body>

</html>