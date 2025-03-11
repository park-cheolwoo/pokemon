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
    // 하루 3회 제한을 설정하고, 몇 번째로 클릭했는지 표시하는 함수
    function checkAndLimitDungeonAccess() {
      const maxAttempts = 3;
      const currentDate = new Date().toLocaleDateString();  // 오늘 날짜를 가져옴
      const storedDate = localStorage.getItem('lastAccessDate');
      const attempts = parseInt(localStorage.getItem('attempts'), 10) || 0;

      // 새로운 날이 시작되었을 때 시도 횟수 리셋
      if (storedDate !== currentDate) {
        localStorage.setItem('attempts', 0);
      }

      // 시도 횟수를 화면에 표시

      // 시도 횟수가 초과되었으면 알림 표시하고 링크 클릭을 막음
      if (attempts >= maxAttempts) {
        document.getElementById('sdungeon_link').addEventListener('click', function(event) {
          event.preventDefault(); // 링크 클릭을 막음
          alert("오늘의 플레이 횟수가 초과되었습니다. 내일 다시 시도해주세요.");
        });
      } else {
        // 시도 횟수가 초과되지 않으면 클릭할 때마다 시도 횟수를 증가
        document.getElementById('sdungeon_link').addEventListener('click', function() {
          localStorage.setItem('attempts', attempts + 1);
          localStorage.setItem('lastAccessDate', currentDate);  // 오늘 날짜 저장
          // 시도 횟수를 화면에 업데이트
          document.getElementById('attempts_info').textContent = `${attempts + 1}/${maxAttempts}`;
        });
      }
    }

    // 페이지가 로드될 때 checkAndLimitDungeonAccess 함수 실행
    window.onload = checkAndLimitDungeonAccess;

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
