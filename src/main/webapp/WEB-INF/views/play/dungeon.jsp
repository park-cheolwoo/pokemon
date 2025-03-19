<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/dungeon.css">
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <title>일반던전</title>
</head>
<body>
  <script>
	const session_id = "${session_id}";
  </script>
  <div id="back"><img src="../images/play/back.png"/>
    <div id="dark">
      <a href="/" class="get_out"><img src="../images/play/dungeon/ax.png"></a>
      <div class="dungeon"><img src="../images/play/dungeon/dungeon.png"></div>
      <div class="Habitat_Selection">
      </div>
      <div class="playboard" style="background-image: url(../images/play/dungeon/playboard.png);">
        <div class="Difficulty_level" id="difficulty-level-container">
        </div>
        <div class="sideboard">
          <!-- 이 부분이 변경될 환경 이미지 -->
          <div id="environment-image"><img src="../images/play/dungeon/Battle_environment1.png"></div>
          <div class="subtitle"></div>
          <div class="explanation"></div>
          <button id="dungeon_go"><img src="../images/play/dungeon/start1.png"></button>
        </div>
      </div>
    </div>  
  </div>
  <script src="../js/play/dungeon/dungeon.js" defer></script>
</body>
</html>
