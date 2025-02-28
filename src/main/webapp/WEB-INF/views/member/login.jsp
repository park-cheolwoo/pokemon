<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h2>로그인</h2>
	<form action="/login" method="post">
		<input type="text" placeholder="id" />
		<input type="password" placeholder="pw" />
		
		<div>
			<a href="">회원가입</a>
			<a href="">ID 찾기</a>
			<a href="">PW 찾기</a>
		</div>
		
		<button id="loginBtn">로그인</button>
	</form>
	<script src="/js/member/member.js"></script>
</body>
</html>