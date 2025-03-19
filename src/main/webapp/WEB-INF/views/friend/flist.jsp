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
			<div class="flist-menu">
			<div class="imft1">
				<img src='/images/green-bar.png' class="imgflist1" id="btn1">
				<img src='/images/yellow-bar.png' class="imgflist2">
				<button id="toggleBtn1" class="flistbtn">친구 목록</button>
			</div>
			<div class="imft2">
				<img src='/images/green-bar.png' class="imgfplus1 hidden" id="btn2">
				<img src='/images/yellow-bar.png' class="imgfplus2">
				<button id="toggleBtn2" class="fplusbtn">친구 추가</button>
			</div>
			<div class="imft3">
				<img src='/images/green-bar.png' class="imgfpls1 hidden" id="btn3">
				<img src='/images/yellow-bar.png' class="imgfpls2">
				<button id="toggleBtn3" class="fplsbtn">친구 요청</button>
			</div>
			</div>
			<!-- 친구 목록창 -->
			<div id="content1">
				<c:forEach var="friend" items="${friends}">
					<!-- playerFrom 출력: 자신의 닉네임이 아닐 경우만 -->
					<c:if test="${friend.playerFrom.id != sessionScope.session_id}">
						<div class="friend-item">
							<img src='/images/index/msball.png' class="msball">
							<div class="woodpanner">
							<img src='/images/index/wood_panner.png' class="wdpanner">
							<h3 class="fname">Lv.${friend.playerFrom.lv}
								${friend.playerFrom.nickname}
								<span class="tag">${friend.playerFrom.tag}</span>
								</h3>
							</div>
							<div class="woodtrade">
							<img src='/images/index/wood_trade.png' class="wdtrade">
							<button class="tradebtn">포켓몬 교환</button>
							<img src='/images/index/wood_trade.png' class="wdtrade">
	                        <button id="deletefr1" class="delete-friend" data-player-from="${friend.playerFrom.id}">친구 삭제</button>
							</div>
						</div>
					</c:if>
					<!-- playerTo 출력: 자신의 닉네임이 아닐 경우만 -->
					<c:if test="${friend.playerTo.id != sessionScope.session_id}">
						<div class="friend-item">
							<img src='/images/index/msball.png' class="msball"> 
						<div class="woodpanner">
							<img src='/images/index/wood_panner.png' class="wdpanner">
							<h3 class="fname">Lv.${friend.playerTo.lv}
								${friend.playerTo.nickname}
								<span class="tag">${friend.playerTo.tag}</span></h3>
						</div>
						<div class="woodtrade">
							<img src='/images/index/wood_trade.png' class="wdtrade">
							<button class="tradebtn">포켓몬 교환</button>
							<img src='/images/index/wood_trade.png' class="wdtrade">
	                        <button id="deletefr2" class="delete-friend" data-player-to="${friend.playerTo.id}">친구 삭제</button>
						</div>
						</div>
					</c:if>
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
				<c:forEach var="pending" items="${pendings}">
				<c:if test="${pending.playerFrom.id !=sessionScope.session_id}">
						<div class="frequest" id="friend-requests">
							<img src='/images/index/msball.png' class="msball">
							<div class="woodpanner">
							<img src='/images/index/wood_panner.png' class="wdpanner">
							<h3 class="fname">Lv.${pending.playerFrom.lv}
								${pending.playerFrom.nickname}
								<span class="tag">${pending.playerFrom.tag}</span>
								</h3>
							</div>
							<div class="request-actions">
							<img src='/images/index/wood_trade.png' class="wdtrade">
	                        <button class="accept-request" data-player-from="${pending.playerFrom.id}">수락</button>
							<img src='/images/index/wood_trade.png' class="wdtrade">
	                        <button class="cancel-request" data-player-from="${pending.playerFrom.id}">거절</button>
	                    	</div>
						</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
		<img src='/images/close.png' class="close">
		<button id="closeBtn" class="closebtn"></button>
	</div>
	<script src="/js/play/ingame.js"></script>
	<script src="/js/index.js"></script>
	<script src="/js/member/friend.js"></script>
</body>
</html>