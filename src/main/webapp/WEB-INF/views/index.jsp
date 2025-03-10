<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓몬</title>
<link type="text/css" rel="stylesheet" href="/css/memberstyles.css" >
<link type="text/css" rel="stylesheet" href="/css/styles.css" >
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div class="main-container">
        <h2 class="player">Lv.${session_lv} ${session_nickname}</h2>
        <img src="/images/index/wood_button1.png" class="wood1">
        <img src="/images/index/gold.png" class="gold">
        <img src="/images/index/wood_button3.png" class="wood2">
        <h3 class="money1">${session_gameMoney} 원</h3>
        <img src="/images/index/diamond.png" class="diamond">
        <img src="/images/index/wood_button3.png" class="wood3">
        <h3 class="money2">${session_realMoney} 원</h3>
        <img src="/images/index/option.png" class="option">
        
        <div id="pokemon-container" class="poketmonimg"></div>
        
        <img src="/images/index/wood_button2.png" class="wood4">
        <button id="mypageBtn" class="indexbtn1">마이페이지</button>
        <img src="/images/index/wood_button2.png" class="wood5">
        <button id="storeBtn" class="indexbtn2">상점</button>
        <img src="/images/index/wood_button2.png" class="wood6">
        <button id="playBtn" class="indexbtn3">PLAY</button>
        <img src="/images/index/wood_button2.png" class="wood7">
        <button id="questBtn" class="indexbtn4">퀘스트</button>
        <img src="/images/index/wood_button2.png" class="wood8">
        <button id="socialBtn" class="indexbtn5">소셜</button>
        
        <a href="member/login">로그인</a>
    </div>
        
    <script src="/js/index.js"></script>
   
</body>
</html>
