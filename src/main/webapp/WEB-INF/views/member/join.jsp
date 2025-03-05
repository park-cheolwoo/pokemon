<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link type="text/css" rel="stylesheet" href="/css/memberstyles.css" >
</head>
<body>
<form action="/member/join" method="post">
<div class="join-container">
	<div class="join-box">
	<div class="insert">회원가입</div>
	<input type="text" name="id" placeholder="  ID" ><br/>
	<button class="duplication" onclick="check()">중복확인</button>
	<p class="idmatch">사용가능한 ID입니다.</p>
	<input type="text" name="pw" placeholder="  PW"><br/>
	<input type="text" name="pw2"placeholder="  PW 확인"><br/>
	<p class="pwmatch">패스워드가 일치합니다.</p>
	<input type="text" name="nickname" placeholder="  닉네임"><br/>
	<button type="submit" class="accession">가입하기</button>
	</div>
</div>
</form>
	<div><a href="/member/login">로그인창 이동</a></div>
</body>
</html>