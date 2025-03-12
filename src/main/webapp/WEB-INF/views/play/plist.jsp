<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/plist.css">
  <title>play</title>
</head>
<body>
  <div id="back"><img src="../images/play/back.png"/>
    <div id="dark">
      <div id="chang">
        <div id="head_chang">
          <a href="/"><img src="../images/play/plist/ax.png"/></a>
        </div>
        <div class="line">
          <a href="/play/dungeon"><img src="../images/play/plist/dongon1.png"/></a>
          <a href="/play/sdungeon" id="sdungeon_link"><img src="../images/play/plist/dongon2.png"/></a>
          <span id="attempts_info"></span>  <!-- 시도 횟수를 표시할 곳 -->
          <button id="week_button"><img src="../images/play/plist/dongon3.png"/></button>
        </div>
        <div class="line">
          <button id="event_button"><img src="../images/play/plist/dongon4.png"/></button>
          <button id="pvp_button"><img src="../images/play/plist/dongon5.png"/></button>
          <button id="duoble_button"><img src="../images/play/plist/dongon6.png"/></button>
        </div>
      </div>
    </div>
  </div>

  <script>
    // 다른 버튼에 이벤트 리스너 추가
    document.getElementById('week_button').addEventListener('click', function() {
      alert("플레이어 레벨이 부족합니다");
    });

    document.getElementById('event_button').addEventListener('click', function() {
      alert("준비중입니다.");
    });

    document.getElementById('pvp_button').addEventListener('click', function() {
      alert("플레이어 레벨이 부족합니다");
    });

    document.getElementById('duoble_button').addEventListener('click', function() {
      alert("플레이어 레벨이 부족합니다");
    });
  </script>
</body>
</html>
