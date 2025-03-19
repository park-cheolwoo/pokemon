<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <img src="/images/pros/yellow-ellipse-bar.png" class="pros_player_btn_box_on"/>
  <h4 class="pros_player_btn_text_on text">유저 정보</h4>
  <img src="/images/pros/wood-ellipse-bar.png" class="pros_data_btn pros_data_btn_box_off" />
  <h4 class="pros_data_btn pros_data_btn_text_off text">데이터 정보</h4>

  <div class="pros_user_list_container">
  <img src="/images/container.png" class="pros_container_frame">
    <div class="pros_search_wrap">
      <img src="/images/pros/wood-bar2.png" class="pros_search_bar" />
      <input type="text" name="keyword" class="pros_keyword" placeholder="검색" maxlength="15" />
      <img src="/images/pros/search.png" class="pros_search_btn" />
      <img src="/images/pros/retry.png" class="pros_reload_btn" />
    </div>

    <div class="pros_list_wrap">
   	  <h4 class="pros_list_category" style="display: none">${category }</h4>
	  <h4 class="pros_list_page" style="display: none">1</h4>
	  <h4 class="pros_search_flag" style="display: none">0</h4>
      <div class="pros_list">
        <!-- 반복 -->
        <c:forEach items="${list }" var="list">
        <div class="pros_items" data-id="${list.id }">
        	<div class="pros_profile_frame">
        		<img src="/images/store/item-frame.png" class="pros_profile_frame_img">
         	    <img src="/images/pros/avarter.png" class="pros_profile_img">
        	</div>
       		<div class="pros_profile_wrap">
       		<img src="/images/pros/wood-bar2.png" class="pros_profile_bar">
            <h4 class="pros_profile">${list.nickname }</h4>
            <h4 class="pros_profile_info">
            	<c:if test="${list.isActive == 0 }">활성화</c:if>
            	<c:if test="${list.isActive == 1 }">비활성화</c:if>
            </h4>
       		</div>
        	<img src="/images/pros/more.png" class="pros_more_btn">
        </div>
        </c:forEach>    
      </div>
	</div>
  </div>
  <!-- 상세보기 모달 -->
  <div class="pros_profile_view_container">
  <img src="/images/container.png" class="pros_container_frame2">
  <div class="pros_blur"></div>
  
  <img src="/images/pros/setting.png" class="pros_update_flag pros_update_btn" /> 
  <img src="/images/pros/retry.png" class="pros_reload_btn2" />


  <img src="/images/store/item-frame.png" class="pros_profile_frame_img2" />
  <img src="/images/pros/avarter.png" class="pros_profile_img2">
  <h4 class="pros_profile_name1 pros_modal1 text"></h4>
  <h4 class="pros_profile_name2 pros_modal2 text"></h4>
  
  <!-- 내용 부분 -->

  <img src="/images/pros/wood-bar2.png" class="pros_exe_frame">
  <img src="/images/pros/exe-bar.png" class="pros_exe_bar">
  <h4 class="pros_exe_txt text"></h4>
  

    <h4 class="pros_update text"></h4>
    <h4 class="pros_create text"></h4>

	
    <img src="/images/store/coin.png" class="pros_coin">
    <h4 class="pros_get_coin text"></h4>

    <img src="/images/store/ruby.png" class="pros_ruby">
    <h4 class="pros_get_ruby text"></h4>
    
    <h4 class="pros_active_txt text">활성화</h4>
    <img src="/images/store/item-frame.png" class="pros_active_frame">
    <h4 class="pros_active_first" style="display:none">on</h4>
    <img src="/images/store/green-check.png" class="pros_active_on" />
    <img src="/images/close.png" class="pros_active_off" /> 
    

    <h4 class="pros_intro text"></h4>
	
	
	
	<form name="updateFrm" method="post" class="updateFrm">
		<input type="hidden" name="get_active" class="pros_active" value="on" />
    <input type="hidden" name="get_id" class="pros_get_id" />
		<input type='text' name="get_name" class="pros_get_name_input" maxlength=6>
		<input type='text' name="get_lv"  class="pros_get_lv_input" maxlength=3>
		<input type='text' name="get_coin"  class="pros_get_coin_input">
	  <input type='text' name="get_ruby"  class="pros_get_ruby_input">
		<textarea class="pros_intro_input"></textarea>
		<img src="/images/store/green-check.png" class="pros_update_confirm_btn">
	  	<img src="/images/close.png" class="pros_update_exit">
	</form>
	<img src="/images/close.png" class="pros_profile_view_exit">
  </div>
  <!-- 모달 끝 -->
  <img src="/images/pros/home.png" class="pros_home_btn">
  <script src="/js/play/ingame.js"></script>
</body>
</html>