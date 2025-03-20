<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/first/first.css">
  <link type="text/css" rel="stylesheet" href="/css/styles.css"> <!-- 공통 스타일 -->
  <script src="../js/first/first.js" defer></script>
  <title>첫만남</title>
</head>
<body>
  <div id="full_background">
    <img src="../images/first/first_backgrand.png" alt="Background Image"/>
    <div id="chat">
      <div id="chatText">포켓몬의 세계에 오신것을 환영합니다.</div>
      <div id="enter">↵</div>
    </div>
    <div id="starting1" onclick="showDark(1)">
      <img src="../images/first/starting1.png" alt="Starting 1" />
    </div>
    <div id="starting2" onclick="showDark(2)">
      <img src="../images/first/starting2.png" alt="Starting 4" />
    </div>
    <div id="starting3" onclick="showDark(3)">
      <img src="../images/first/starting3.png" alt="Starting 7" />
    </div>
    
    <div id="deep_dark">
      <div class="nameboard">
        <img src="../images/first/nameboard.png"/>
        <div id="chat_1"></div> 
        <button class="chack_1"><img src="../images/first/chack.png"/></button>
        <button class="ax_1"><img src="../images/first/ax.png"/></button>
      </div>

      <div class="nameboard" style="display: none">
        <img src="../images/first/nameboard.png"/>
        <div id="chat_2"></div> 
        <button class="chack_2"><img src="../images/first/chack.png"/></button>
        <button class="ax_2"><img src="../images/first/ax.png"/></button>
      </div>

      <div class="nameboard" style="display: none">
        <img src="../images/first/nameboard.png"/>
        <div id="chat_3"><input type="text" placeholder="포켓몬의 이름을 입력해주세요" id="naming"/></div> 
        <button class="chack_3"><img src="../images/first/chack.png"/></button>
      </div>

    </div>
  </div>
</body>
</html>
