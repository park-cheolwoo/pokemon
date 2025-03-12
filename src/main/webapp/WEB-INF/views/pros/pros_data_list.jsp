<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <img src="/images/pros/wood-ellipse-bar.png" class="pros_user_btn_box_off"/>
  <h4 class="pros_user_btn_text_off text">유저 정보</h4>
  <img src="/images/pros/yellow-ellipse-bar.png" class="pros_data_btn pros_data_btn_box_on" />
  <h4 class="pros_data_btn pros_data_btn_text_on text">데이터 정보</h4>


  <div class="pros_user_list_container">
  <img src="/images/container.png" class="pros_container_frame">

    <div class="pros_search_wrap">
      <img src="/images/pros/wood-bar2.png" class="pros_search_bar" />
      <input type="text" name="keyword" class="pros_keyword" placeholder="검색" maxlength="15" />
      <img src="/images/pros/search.png" class="pros_search_btn" />
      <img src="/images/pros/retry.png" class="pros_reload_btn" />
      <img src="/images/pros/list.png" class="pros_dropbar_btn" />
      <img src="/images/yellow-bar.png" class="pros_pokemon_select_box" />
      <h4 class="pros_pokemon_select_text text">포켓몬</h4>
      <img src="/images/yellow-bar.png" class="pros_item_select_box" />
      <h4 class="pros_item_select_text text">아이템</h4>
      <img src="/images/yellow-bar.png" class="pros_dungeon_select_box" />
      <h4 class="pros_dungeon_select_text text">던전</h4>
    </div>

    <div class="pros_list_wrap">
   	  <h4 class="pros_list_category" style="display:none">pokemon</h4>
   	  <h4 class="pros_list_page" style="display:none">1</h4>
   	  <h4 class="pros_search_flag" style="display:none">0</h4>
   	  <div class="pros_list2">
   	  	<!-- 반복 -->
   	  	<c:forEach items="${list}" var="list"> 
   	  		 <div class="pros_items" data-name="${list.getName()}"><img src="${list.getImage()}" class="pros_list_img"></div>
   	  	</c:forEach>
   	  	<!-- 반복 -->
   	  </div>
   	 </div>
   	 
   	 
   <!--  상세보기 모달 -->	 
  <div class="pros_profile_view_container">
  <div class="pros_blur"></div> 	  
  <img src="/images/container.png" class="pros_container_frame2">
  <img src="/images/store/item-frame.png" class="pros_profile_frame_img2" />
  <img src="/images/pros/avarter.png" class="pros_profile_img2">
  <h4 class="pros_profile_name1 text">일레도리자드</h4>
  <img src="/images/pros/type-grass.png" class="pros_pokemon_type1">
  <img src="/images/pros/type-poison.png" class="pros_pokemon_type2">
  <h4 class="pros_profile_name2 text">씨앗포켓몬</h4>
  
  <h4 class="pros_update text">수정일 : 2025/02/25</h4>
  <h4 class="pros_create text">등록일 : 2025/02/25</h4>

  <!-- 내용 부분 -->
  <img src="/images/pros/pokemon.png" class="pros_pokemon_img1">
  <img src="/images/pros/wood-right.png" class="pros_wood_right1">
  <img src="/images/pros/pokemon.png" class="pros_pokemon_img2">
  <img src="/images/pros/wood-right.png" class="pros_wood_right2">
  <img src="/images/pros/pokemon.png" class="pros_pokemon_img3">

	<h4 class="pros_active_txt text">활성화</h4>
    <img src="/images/store/item-frame.png" class="pros_active_frame">
    <h4 class="pros_active_first" style="display:none">on</h4>
    <img src="/images/store/green-check.png" class="pros_active_on" />
    <img src="/images/close.png" class="pros_active_off" /> 



  	<h4 class="pros_intro text">자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.자기소개가 너무 길면 스크롤이 됩니다.</h4>

	<form name="updateFrm" method="post" class="updateFrm">
	<input type="hidden" name="get_category" class="get_category" value="pokemon" />
	<input type="hidden" name="get_active" class="pros_active" value="on" />
	<input type="text" name="get_name" class="pros_get_name_input" maxlength="9" />
	<textarea class="pros_intro_input"></textarea>
	<img src="/images/store/green-check.png" class="pros_update_confirm_btn">
  	<img src="/images/close.png" class="pros_update_exit">
	</form>
	
	<img src="/images/close.png" class="pros_profile_view_exit">
  </div>
  <!-- 모달 끝 -->

  <img src="/images/pros/home.png" class="pros_home_btn">
		
		
		

        <!-- 임시저장 -->
        <!-- 
        <table class="pros_list">
        <tr class="pros_rows">
          <td>
            <div class="pros_profile_frame">
              <img src="/images/store/item-frame.png" class="pros_profile_frame_img">
              <img src="/images/pros/pokemon.png" class="pros_profile_img">
            </div>
          </td>
          <td class="pros_profile_wrap">
            <div>
              <img src="/images/pros/wood-bar2.png" class="pros_profile_bar">
              <h4 class="pros_profile">이상해씨</h4>
              <h4 class="pros_profile_info">활성화</h4>
            </div>
          </td>
          <td><img src="/images/pros/more.png" class="pros_more_btn"></td>
        </tr>
        
      </table>
        <!-- 임시저장 -->

    </div>
  </div>

</body>

</html>