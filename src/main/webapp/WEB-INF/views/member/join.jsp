<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link type="text/css" rel="stylesheet" href="/css/memberstyles.css" >
<link type="text/css" rel="stylesheet" href="/css/styles.css" >
</head>
<body>
<form action="/member/join" method="post">
<div class="join-container">
	<div class="join-box">
	<div class="insert">회원가입</div>
	<input class="put1" type="text" name="id" placeholder="  ID" ><br/>
	<button type="button" class="duplication">중복확인</button>
	<p class="idmatch"></p>
	<input class="put2" type="password" name="password" placeholder="  PW"><br/>
	<input class="put3" type="password" name="password2"placeholder="  PW 확인"><br/>
	<p class="pwmatch"></p>
	<input class="put4" type="text" name="nickname" placeholder="  닉네임"><br/>
	<button type="submit" class="accession">가입하기</button>
	<button type="button" id="backBtn" class="backbtn">로그인 창으로</button>
	</div>
</div>
</form>
	<script src="/js/member/member.js"></script>
</body>
</html>