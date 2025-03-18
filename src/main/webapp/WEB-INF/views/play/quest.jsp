<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/quest.css">
  <script src="../js/play/quest/quest.js" defer></script>
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
            <div class="questdetail">일반던전 10회 (0/10)</div>
            <div class="compensationdetail1">500</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">특수던전 10회 (0/10)</div>
            <div class="compensationdetail1">500</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">요일던전 10회 (0/10)</div>
            <div class="compensationdetail1">500</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>
        <div id="questlist2" style="display: none;">
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">일반던전 100회 (0/100)</div>
            <div class="compensationdetail2">1000</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">특수던전 100회 (0/100)</div>
            <div class="compensationdetail2">1000</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questgold.png'); background-repeat: no-repeat;">
            <div class="questdetail">요일던전 100회 (0/100)</div>
            <div class="compensationdetail2">1000</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>
        <div id="questlist3" style="display: none;">
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">도감 10개 채우기</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">스킬 1000회 사용</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
          <div class="qlist" style="background-image: url('../images/play/quest/questsujeong.png'); background-repeat: no-repeat;">
            <div class="questdetail">아이템 1000회 사용</div>
            <div class="compensationdetail3">10</div>
            <button><img src="../images/play/quest/chack1.png"/></button>
          </div>
        </div>

      </div>
    </div>
  </div>
</body>
</html>
