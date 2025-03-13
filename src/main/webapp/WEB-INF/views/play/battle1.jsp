<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>배틀 페이지</title>
	<link type="text/css" rel="stylesheet" href="/css/styles.css" />
	<link type="text/css" rel="stylesheet" href="/css/play/battle1.css" />
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<main>
		<div class="container">
			<div id="text-box">건강한 씨는 무엇을 할까?</div>
			<div class="pokemon me">
				<div class="pokemon__info">
					<div class="pokemon__info--level">LV 5</div>
					<div class="pokemon__info--name">이상해씨</div>
					<div class="pokemon__info--hp hp-bar">
						<div class="hp-bar__value"></div>
					</div>
				</div>
				<div class="pokemon-image">
					<img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/back/1.gif" />
				</div>
			</div>
			<div class="selection-box">
				<div class="selection-box__btn">싸운다</div>
				<div class="selection-box__btn">포켓몬</div>
				<div class="selection-box__btn">아이템</div>
			</div>
		</div>
	</main>
	<script src="/js/play/battle/battle1.js"></script>
</body>
</html>