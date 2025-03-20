<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/quest.css">
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <script src="../js/play/quest/quest.js" defer></script>
  <link type="text/css" rel="stylesheet" href="/css/styles.css"> <!-- 공통 스타일 -->
  <title>quest</title>
</head>
<body>
  <div id="back"><img src="../images/play/back.png"/>
    <div id="dark">
      <div id="chang">  
        <div id="head_chang">
          <a href="/"><img src="../images/play/quest/ax.png"/></a>
        </div>
        <div id="oh">
          <img src="../images/play/quest/oh.png">
        </div>
        <div id="questboard">
          <img src="../images/play/quest/questboard.png">
        </div>
        
        <div class="uplist">
          <button id="uplist1" onclick="toggleQuestLists(1)">
            <img src="../images/play/quest/uplist1_1.png"/>
          </button>
        </div>
        <div class="uplist">
          <button id="uplist2" onclick="toggleQuestLists(2)">
            <img src="../images/play/quest/uplist2.png"/>
          </button>
        </div>
        <div class="uplist">
          <button id="uplist3" onclick="toggleQuestLists(3)">
            <img src="../images/play/quest/uplist3.png"/>
          </button>
        </div>


        <div id="questlist1">
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">일반던전 5회클리어  (${sdungeon.weeklyClearCount}/5)</div>
            <div class="compensationdetail1">500</div>
            <button onclick="location.href='/play/dungeon'"><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.daily1}특수던전클리어</div>
            <div class="compensationdetail1">500</div>
            <button class="chack1-button" onclick="location.href='/play/sdungeon'"><img src="../images/play/quest/chack1.png"/></button>
            <button class="chack2-button"><img src="../images/play/quest/chack2.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">요일던전클리어</div>
            <div class="compensationdetail1">500</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>
        <div id="questlist2" style="display: none;">
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.weekly1}일반던전 20회클리어  (${sdungeon.weeklyClearCount}/20)</div>
            <div class="compensationdetail2">1000</div>
            <button onclick="location.href='/play/dungeon'"><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.weekly1}특수던전 5회클리어  (${sdungeon.weeklyClearCount}/5)</div>
            <div class="compensationdetail2">1000</div>
            <button onclick="location.href='/play/sdungeon'"><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.weekly1}요일던전 5회클리어  (0/5)</div>
            <div class="compensationdetail2">1000</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>
        <div id="questlist3" style="display: none;">
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.total1}일반던전 50회클리어 (${sdungeon.totalCount}/50)</div>
            <div class="compensationdetail3">10</div>
            <button onclick="location.href='/play/dungeon'"><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.total1}특수던전 50회클리어 (${sdungeon.totalCount}/50)</div>
            <div class="compensationdetail3">10</div>
            <button onclick="location.href='/play/sdungeon'"><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">${quest.total1}요일던전 50회클리어 (0/50)</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">스킬 100회 사용</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">아이템 100회 사용</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="/js/play/ingame.js"></script>
  <script>
  // sdungeon.dailyClearCount 값이 1 이상인 경우
  const dailyClearCount = ${sdungeon.dailyClearCount};  // 이 값은 서버에서 JSP로 전달된 값이어야 합니다.

  const chack1Button = document.querySelector('.chack1-button');
  const chack2Button = document.querySelector('.chack2-button');

  if (dailyClearCount >= 1) {
    // dailyClearCount가 1 이상이면 chack1 버튼은 숨기고, chack2 버튼을 보이도록 설정
    chack1Button.style.display = 'none';
    chack2Button.style.backgroundColor = 'black';
    chack2Button.style.display = 'block';  // chack2 버튼 보이기
  } else {
    // dailyClearCount가 1 미만이면 chack1 버튼을 보이고, chack2 버튼은 숨기기
    chack1Button.style.display = 'block';
    chack2Button.style.display = 'none';
  }
  
  chack2Button.addEventListener('click', function() {
    const playerId = '${session.getAttribute("session_id")}'; // 세션에서 playerId 가져오기
    chack2Button.style.display = 'none';
    // 서버에 POST 요청 보내기
    fetch('/play/quest/increaseMoney', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: `playerId=${playerId}`  // playerId 파라미터를 서버로 전송
    })
    .then(response => response.text())
    .catch(error => {
      console.error('Error:', error);
      alert('오류가 발생했습니다.');
    });
  });
  </script>
</body>
</html>
