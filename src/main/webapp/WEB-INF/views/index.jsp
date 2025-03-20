<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포켓몬 메인 화면</title>
    <link type="text/css" rel="stylesheet" href="/css/indexstyles.css">
    <link type="text/css" rel="stylesheet" href="/css/styles.css" >
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div class="main-container">
        <div class="player-container">
            <img src="/images/index/wood_button1.png" class="player-bg">
            <h2 class="player-info">Lv.${session_lv} ${session_nickname}</h2>
        </div>

        <div class="resources-container">
            <div class="resource-group">
                <img src="/images/index/gold.png" class="resource-icon" alt="골드">
                <img src=	"/images/index/wood_button3.png" class="wood-bg">
                <h3 class="resource-info">${session_gameMoney}</h3>
            </div>
            <div class="resource-group">
                <img src="/images/index/diamond.png" class="resource-icon" alt="다이아몬드">
                <img src="/images/index/wood_button3.png" class="wood-bg">
                <h3 class="resource-info">${session_realMoney}</h3>
            </div>
                <div style="position: relative; width: 111px; height: 111px;">
                <img src="/images/index/option.png" class="option">
                <button type="button" id="logoutBtn" class="closebtn"></button>
                </div>
        </div>
			<div id ="pokemon-images-container">
			</div>
        
        <div class="buttons-container">
            <div class="button-group">
                <img src="/images/index/wood_button2.png" class="button-bg">
                <button id="mypageBtn" class="menu-btn">마이페이지</button>
            </div>
            <div class="button-group">
                <img src="/images/index/wood_button2.png" class="button-bg">
                <button id="storeBtn" class="menu-btn">상점</button>
            </div>
            <div class="button-group">
                <img src="/images/index/wood_button2.png" class="button-bg">
                <button id="playBtn" class="menu-btn">PLAY</button>
            </div>
            <div class="button-group">
                <img src="/images/index/wood_button2.png" class="button-bg">
                <button id="questBtn" class="menu-btn">퀘스트</button>
            </div>
            <div class="button-group">
                <img src="/images/index/wood_button2.png" class="button-bg">
                <button id="socialBtn" class="menu-btn">소셜</button>
            </div>
        </div>
    </div>

    <script src="/js/play/ingame.js"></script>
    <script src="/js/index.js"></script>
</body>
</html>