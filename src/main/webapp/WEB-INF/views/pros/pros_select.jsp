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
  <img src="/images/pros/yellow-ellipse-bar.png" class="pros_user_btn_box_on"/>
  <h4 class="pros_user_btn_text_on text">유저 정보</h4>
  <img src="/images/pros/wood-ellipse-bar.png" class="pros_data_btn pros_data_btn_box_off" />
  <h4 class="pros_data_btn pros_data_btn_text_off text">데이터 정보</h4>


  <div class="pros_user_list_container">
  <img src="/images/container.png" class="pros_container_frame">

    <div class="pros_search_wrap">
      <img src="/images/pros/wood-bar2.png" class="pros_search_bar" />
      <input type="text" name="keyword" class="pros_keyword" placeholder="검색" maxlength="15"
      onkeypress="if( event.keyCode == 13 ){.pros_search_btn.click();}" />
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
   	  <div class="pros_list2">
   	  	<!-- 반복 -->
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<!-- 반복 -->
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div><div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">해바라기씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>
   	  	<div class="pros_items"><img src="/images/pros/pokemon.png" class="pros_list_img"><h4 class="pros_list_name">이상해씨</h4></div>

   	  </div>
   	  
      
		
		
		

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