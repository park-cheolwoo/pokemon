<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/play/sdungeon.css">
  <script src="../js/play/sdungeon/sdungeon.js" defer></script>
  <title>특수던전</title>
</head>
<body>
  <div id="back"><img src="../images/play/back.png"/>
    <div id="dark">
      <!-- 첫 번째 문제 -->
      <div class="schang1">
        <div class="sdungeon"><img src="../images/play/sdungeon/schang.png"/></div>
        <div>
          <div class="order">1/3</div>
          <div class="pokeimg1"><img src="" alt="Pokemon Image 1"></div>
          <div class="Answer_sheet" style="background-image: url('../images/play/sdungeon/Answer_sheet.png')">
            <input class="input1" type="text"/>
          </div>
          <button class="next1" onclick="showSchang(2)"><img src="../images/play/sdungeon/next.png"/></button>
        </div>
      </div>

      <!-- 두 번째 문제 -->
      <div class="schang2" style="display: none;">
        <div class="sdungeon"><img src="../images/play/sdungeon/schang.png"/></div>
        <div>
          <div class="order">2/3</div>
          <div class="pokeimg2"><img src="" alt="Pokemon Image 2"></div>
          <div class="Answer_sheet" style="background-image: url('../images/play/sdungeon/Answer_sheet.png')">
            <input class="input2" type="text"/>
          </div>
          <button class="prev1" onclick="showSchang(1)"><img src="../images/play/sdungeon/prev.png"/></button>
          <button class="next2" onclick="showSchang(3)"><img src="../images/play/sdungeon/next.png"/></button>
        </div>
      </div>

      <!-- 세 번째 문제 -->
      <div class="schang3" style="display: none;">
        <div class="sdungeon"><img src="../images/play/sdungeon/schang.png"/></div>
        <div>
          <div class="order">3/3</div>
          <div class="pokeimg3"><img src="" alt="Pokemon Image 3"></div>
          <div class="Answer_sheet" style="background-image: url('../images/play/sdungeon/Answer_sheet.png')">
            <input class="input3" type="text"/>
          </div>
          <button class="prev2" onclick="showSchang(2)"><img src="../images/play/sdungeon/prev.png"/></button>
          <button class="next3" onclick="showSchang(4)"><img src="../images/play/sdungeon/next.png"/></button>
        </div>
      </div>

      <!-- 보상 화면 -->
      <div class="schang4" style="display: none;">
        <div class="sdungeon"><img src="../images/play/sdungeon/compensation.png"/></div>
        <div>
          <div class="problem1"><img src="" alt="Pokemon Image 1"></div>
          <div class="inproble1"></div>
          <div class="wrong1"></div>
          <div class="Correct1"></div>
        </div>
        <div> 
          <div class="problem2"><img src="" alt="Pokemon Image 2"></div>
          <div class="inproble2"></div>
          <div class="wrong2"></div>
          <div class="Correct2"></div>
        </div>
        <div>
          <div class="problem3"><img src="" alt="Pokemon Image 3"></div>
          <div class="inproble3"></div>
          <div class="wrong3"></div>
          <div class="Correct3"></div>
        </div>
        <div class="score"></div>
        <div class="goldfinch"></div>
        <button class="get"><img src="../images/play/sdungeon/check1.png"></button>
      </div>
    </div> 
  </div>
 </body>
</html>
  