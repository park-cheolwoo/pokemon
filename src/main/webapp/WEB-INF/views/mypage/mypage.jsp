<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mypage</title>
    <link type="text/css" rel="stylesheet" href="/css/styles.css"> <!-- 공통 스타일 -->
    <link type="text/css" rel="stylesheet" href="/css/pokedex.css"> <!-- 도감 관련 스타일 -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div class="mypage-background-container">
        <div class="mypage-banner-container">
            <div class="mypage-banner">
                <div class="banner-item" data-tab="pokedex">
                    <span>도감</span>
                </div>
                <div class="banner-item" data-tab="item">
                    <span>아이템</span>
                </div>
                <div class="banner-item" data-tab="expedition">
                    <span>원정대</span>
                </div>
            </div>
            <a href="/"><div class="closeBtn"></div></a>
        </div>

        <div id="content-container" class="mypage-content-container">
            <!-- 콘텐츠는 JavaScript로 동적으로 로드됩니다 -->
        </div>
    </div>

    <!-- JavaScript 코드 -->
    <script src="/js/play/ingame.js"></script>
    <script src="/js/mypage/mypage.js"></script>
    <script src="/js/mypage/expedition.js"></script>
    <script src="/js/mypage/mypage_item.js"></script>
</body>
</html>
