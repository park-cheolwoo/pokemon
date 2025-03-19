<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/plist.css">
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
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
          <button id="sdungeon_link"><img src="../images/play/plist/dongon2.png"/></button>
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
  // JSP에서 message 값을 JavaScript 변수로 전달
  var message = "${message}";

  document.getElementById('sdungeon_link').addEventListener('click', function() {
    if (message) {
      // message가 있을 경우 알림을 띄우기
      alert(message);
    } else {
      // message가 없을 경우 /play/sdungeon으로 이동
      window.location.href = '/play/sdungeon';
    }
  });
  
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
  <script src="/js/play/ingame.js"></script>
</body>
</html>
