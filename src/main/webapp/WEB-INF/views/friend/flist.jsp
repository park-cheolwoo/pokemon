<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구창</title>
<link type="text/css" rel="stylesheet" href="/css/friendstyles.css" >
<link type="text/css" rel="stylesheet" href="/css/styles.css" >
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="flist-container">
		<div class="flist-box">
			<img src='/images/flist.png'>
			<img src='/images/green-bar.png' class="imgflist1" id="btn1">
			<img src='/images/yellow-bar.png' class="imgflist2">
			<button id="toggleBtn1" class="flistbtn">친구 목록</button>
			<img src='/images/green-bar.png' class="imgfplus1 hidden" id="btn2">
			<img src='/images/yellow-bar.png' class="imgfplus2">
			<button id="toggleBtn2" class="fplusbtn">친구 추가</button>
			<img src='/images/green-bar.png' class="imgfpls1 hidden" id="btn3">
			<img src='/images/yellow-bar.png' class="imgfpls2">
			<button id="toggleBtn3" class="fplsbtn">친구 요청</button>
			<!-- 친구 목록창 -->
			<div id="content1">
			<img src='/images/msball.png' class="msball1">
			<img src='/images/wood_panner.png' class="wdpanner1">
			<h3 class="fname1">Lv.1 아조씨</h3>
			<img src='/images/wood_trade.png' class="wdtrade1">
			<button class="tradebtn1">포켓몬 교환</button>
			<img src='/images/stball.png' class="msball2">
			<img src='/images/wood_panner.png' class="wdpanner2">
			<h3 class="fname2">Lv.2 아조씨</h3>
			<img src='/images/wood_trade.png' class="wdtrade2">
			<button class="tradebtn2">포켓몬 교환</button>
			<img src='/images/msball.png' class="msball3">
			<img src='/images/wood_panner.png' class="wdpanner3">
			<h3 class="fname3">Lv.3 아조씨</h3>
			<img src='/images/wood_trade.png' class="wdtrade3">
			<button class="tradebtn3">포켓몬 교환</button>
			</div>
			<!-- 친구 추가 창 -->
			<div id="content2" class="hiddenbox">
			<h2 class ="myprofile">나의 프로필</h2>
			<img src='/images/wood_panner2.png' class="wdmyprofile">
			<h2 class ="myprofilecode">Lv.1 아조씨 #123456</h2>
			<h2 class ="fiendprofile">친구 코드</h2>
			<img src='/images/wood_panner2.png' class="wdfriendprofile">
			<input type="text" class="friendcode">
			<img src='/images/wood_trade.png' class="friendplus">
			<button class="friendplusbtn">친구추가</button>
			</div>
			<!-- 친구 요청창 -->
			<div id="content3" class="hiddenbox" >
			<img src='/images/msball.png' class="msball4">
			<img src='/images/wood_panner.png' class="wdpanner4">
			<h3 class="fname4">Lv.1 아조씨</h3>
			<img src='/images/wood_trade.png' class="wdtrade4">
			<button class="tradebtn4">요청수락</button>
			<img src='/images/stball.png' class="msball5">
			<img src='/images/wood_panner.png' class="wdpanner5">
			<h3 class="fname5">Lv.2 아조씨</h3>
			<img src='/images/wood_trade.png' class="wdtrade5">
			<button class="tradebtn5">요청수락</button>
			</div>
		</div>
				<img src='/images/close.png' class="close">
				<button id="closeBtn" class="closebtn"></button>
	</div>
	<script src="/js/index.js"></script>
</body>
</html>