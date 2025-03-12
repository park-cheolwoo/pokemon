<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구창</title>
<link type="text/css" rel="stylesheet" href="/css/friendstyles.css">
<link type="text/css" rel="stylesheet" href="/css/styles.css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="flist-container">
		<div class="flist-box">
			<img src='/images/index/flist.png'> <img
				src='/images/green-bar.png' class="imgflist1" id="btn1"> <img
				src='/images/yellow-bar.png' class="imgflist2">
			<button id="toggleBtn1" class="flistbtn">친구 목록</button>
			<img src='/images/green-bar.png' class="imgfplus1 hidden" id="btn2">
			<img src='/images/yellow-bar.png' class="imgfplus2">
			<button id="toggleBtn2" class="fplusbtn">친구 추가</button>
			<img src='/images/green-bar.png' class="imgfpls1 hidden" id="btn3">
			<img src='/images/yellow-bar.png' class="imgfpls2">
			<button id="toggleBtn3" class="fplsbtn">친구 요청</button>
			<!-- 친구 목록창 -->
			<div id="content1">
				<c:forEach var="friend" items="${friends}">
					<img src='/images/index/msball.png' class="msball1">
					<img src='/images/index/wood_panner.png' class="wdpanner1">
					<h3 class="fname1">Lv.${friend.playerFrom.lv}
						${friend.playerFrom.nickname}</h3>
					<img src='/images/index/wood_trade.png' class="wdtrade1">
					<button class="tradebtn1">포켓몬 교환</button>
				</c:forEach>
			</div>
			<!-- 친구 추가 창 -->
			<div id="content2" class="hiddenbox">
				<h2 class="myprofile">나의 프로필</h2>
				<img src='/images/index/wood_panner2.png' class="wdmyprofile">
				<h2 class="myprofilecode">Lv.${session_lv} ${session_nickname}
					${session_tag}</h2>
				<h2 class="fiendprofile">친구 코드</h2>
				<img src='/images/index/wood_panner2.png' class="wdfriendprofile">
				<input type="text" class="friendcode" placeholder="친구 코드를 입력해주세요">
				<img src='/images/index/wood_trade.png' class="friendplus">
				<button type="button" class="friendplusbtn">친구추가</button>
			</div>
			<!-- 친구 요청창 -->
			<div id="content3" class="hiddenbox">
				<ul class="frequest" id="friend-requests">
					
				</ul>
			</div>
		</div>
		<img src='/images/close.png' class="close">
		<button id="closeBtn" class="closebtn"></button>
	</div>
	<script src="/js/index.js"></script>
	<script src="/js/member/friend.js"></script>
</body>
</html>