<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link type="text/css" rel="stylesheet" href="/css/memberstyles.css" >
<link type="text/css" rel="stylesheet" href="/css/styles.css" >
</head>
<body>
	<form action="/member/login" name="loginFrm" method="post">
	<div class="login-container">
		<div class="login-box">
		<input type="text" name="id" id="id" class="idtext" placeholder="  ID" /><br/>
		<input type="password" name="password" id="password" class="pwtext" placeholder="  PW" />
		
		<div class="login-join">
			<a href="/member/join" class="link1">회원가입</a>
			<a href="" class="link2">ID 찾기</a>
			<a href="" class="link3">PW 찾기</a>
		</div>
		
		<button type="submit" id="loginBtn">로그인</button>
		</div>
			<img src="/images/index/kakao_login.png" class="kakao">
			<img src="/images/index/naver_login.png" class="naver">
	</div>
	</form>
	<script src="/js/member/member.js"></script>
</body>
</html>