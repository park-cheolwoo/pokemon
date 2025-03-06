<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/dungeon.css">
  <script src="../js/play/dungeon/dungeon.js" defer></script>
  <title>일반던전</title>
</head>
<body>
  <div id="back"><img src="../images/play/back.png"/>
    <div id="dark">
      <a href="/" class="get_out"><img src="../images/play/dungeon/ax.png"></a>
      <div class="dungeon"><img src="../images/play/dungeon/dungeon.png"></div>
      <div class="Habitat_Selection">
        <button class="habitat-btn" data-habitat="cave" style="background-image: url(../images/play/dungeon/signboard_1.png)">동굴</button>
        <button class="habitat-btn" data-habitat="forest" style="background-image: url(../images/play/dungeon/signboard_0.png)">숲</button>
        <button class="habitat-btn" data-habitat="meadow" style="background-image: url(../images/play/dungeon/signboard_0.png)">목초지</button>
        <button class="habitat-btn" data-habitat="mountain" style="background-image: url(../images/play/dungeon/signboard_0.png)">산</button>
        <button class="habitat-btn" data-habitat="rare" style="background-image: url(../images/play/dungeon/signboard_0.png)">희귀한</button>
        <button class="habitat-btn" data-habitat="rough" style="background-image: url(../images/play/dungeon/signboard_0.png)">거친</button>
        <button class="habitat-btn" data-habitat="sea" style="background-image: url(../images/play/dungeon/signboard_0.png)">바다</button>
        <button class="habitat-btn" data-habitat="city" style="background-image: url(../images/play/dungeon/signboard_0.png)">도시</button>
        <button class="habitat-btn" data-habitat="shore" style="background-image: url(../images/play/dungeon/signboard_0.png)">물가</button>
      </div>
      <div class="playboard" style="background-image: url(../images/play/dungeon/playboard.png);">
        <div class="Difficulty_level">
          <button class="difficulty-btn" data-level="1" style="background-image: url(../images/play/dungeon/Difficulty_level_1.png);">1 단계</button>
          <button class="difficulty-btn" data-level="2" style="background-image: url(../images/play/dungeon/Difficulty_level.png);">2 단계</button>
          <button class="difficulty-btn" data-level="3" style="background-image: url(../images/play/dungeon/Difficulty_level.png);">3 단계</button>
          <button class="difficulty-btn" data-level="4" style="background-image: url(../images/play/dungeon/Difficulty_level.png);">4 단계</button>
          <button class="difficulty-btn" data-level="5" style="background-image: url(../images/play/dungeon/Difficulty_level.png);">5 단계</button>
          <button class="difficulty-btn" data-level="6" style="background-image: url(../images/play/dungeon/Difficulty_level.png);">6 단계</button>
        </div>
        <div class="sideboard">
          <!-- 이 부분이 변경될 환경 이미지 -->
          <div id="environment-image"><img src="../images/play/dungeon/Battle_environment1.png"></div>
          <div class="subtitle"></div>
          <div class="explanation"></div>
          <button><img src="../images/play/dungeon/start1.png"></button>
        </div>
      </div>
    </div>  
  </div>
</body>
</html>
