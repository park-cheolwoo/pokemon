<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../css/styles.css">
  <link rel="stylesheet" type="text/css" href="../css/play/battle.css">
  <script src="../js/play/battle/battle.js" defer></script>
  <script src="../js/play/battle/battle_attack.js" defer></script>
  <script src="../js/play/battle/battle_trade.js" defer></script>
  <title>Document</title>
</head>
<body>
  <div class="background_scenery">
    <!-- 대화창 -->
    <div class="battle_chang1">
      <div class="battle_chat1"></div>
    </div> 
    <!-- 상대 모습 -->
     <!-- 서식지중 랜덤포켓몬 정보를 받아와서 넣어야 하는데 난이도와 서식지는 play에서 받아와야한다 -->
    <div class="battle_bot">
      <div class="battle_lv_bot">LV.5</div>
      <div class="battle_name_bot">이상해씨</div>
      <div class="battle_health_bar1">
        <div class="health_bar1"></div>
      </div>
      <div class="battle_bot_img"><img src="../images/play/battle/battle_bot.gif"/></div>
    </div>
    <!-- 내모습 -->
     <!-- 내가 가진포켓몬중 1번이 나와야함 -->
    <div class="battle_my">
      <div class="battle_lv_my">LV.6</div>
      <div class="battle_name_my">건강한씨</div>
      <div class="battle_health_bar2">
        <div class="health_bar2"></div>
      </div>
      <div class="battle_my_img"><img id="myPokemonImage" src="../images/play/battle/battle_my.gif"/></div>
    </div>
    <!-- 행동 -->
     <div class="battle_action">
      <button class="battle_fight"><div>싸운다</div></button>
      <button class="battle_pokemon"><div>포켓몬</div></button>
      <button class="battle_item"><div>아이템</div></button>
      <button class="battle_run"><div>도망간다</div> </button>
    </div>
    <!-- 기술 -->
    <div class="battle_technology">
      <!-- 포켓몬이 가지고 있는 기술 올려넣기 for문 넣으면 위치 어근나고 첫번째 포켓몬이 바뀔때 기술 교체되는거 없음 -->
      <button class="battle_technology_back"></button>
      <button class="battle_technology_1">
        <div class="battle_technology_name1">몸통박치기</div>
        <div class="battle_technology_PP1">PP 35/35</div>
      </button>
      <button class="battle_technology_2">
        <div class="battle_technology_name2">째려보기</div>
        <div class="battle_technology_PP2">PP 25/25</div>
      </button>
      <button class="battle_technology_3">
        <div class="battle_technology_name3">씨뿌리기</div>
        <div class="battle_technology_PP3">PP 15/15</div>
      </button>
      <button class="battle_technology_4">
        <div class="battle_technology_name4">성장</div>
        <div class="battle_technology_PP4">PP 10/10</div>
      </button> 
    </div>

    <div class="battle_dark">
      <div class="battle_function_chang">
        
        <!-- 설명창 -->
        <div class="battle_chang2">
          <div class="battle_chat2"></div>
          
          <div class="battle_chat2_select_pokemon">
            <div class="battle_chat2_select">
              <button class="battle_chat2_select_pokemon_yes">예</button>
              <button class="battle_chat2_select_pokemon_no">아니요</button>
            </div>
          </div>

          <div class="battle_chat2_select_recovery">
            <div class="battle_chat2_select">
              <button class="battle_chat2_select_recovery_yes">예</button>
              <button class="battle_chat2_select_recovery_no">아니요</button>
            </div>
          </div>

        </div>

        <!-- 포켓몬선택창 -->
        <div class="battle_pokemon_chang">
          <button class="battle_function_before" id="battle_pokemon_before"><img src="../images/play/battle/ax.png"/></button>
          <!-- 각각의 위치에 내가 지닌 포켓몬 정보 올려넣기 for 문넣으면 위치 어근남 -->
          <button class="battle_pokemon1" data-pokemon="1">
            <div class="battle_pokemon1_img"><img src="../images/play/battle/battle_bot.gif"/></div>
            <div class="battle_pokemon1_health_bar">
              <div class="battle_pokemon1_health"></div>
            </div>
            <div class="battle_pokemon1_information">LV. 6 건강한씨</div>
          </button>
          <button class="battle_pokemon2" data-pokemon="2">
            <div class="battle_pokemon2_img"><img src="../images/play/battle/battle_bot2.gif"/></div>
            <div class="battle_pokemon2_health_bar">
              <div class="battle_pokemon2_health"></div>
            </div>
            <div class="battle_pokemon2_information">LV. 5 강한씨</div>
          </button>
          <button class="battle_pokemon3" data-pokemon="3">
            <div class="battle_pokemon3_img"><img src="../images/play/battle/battle_bot.gif"/></div>
            <div class="battle_pokemon3_health_bar">
              <div class="battle_pokemon3_health"></div>
            </div>
            <div class="battle_pokemon3_information">LV. 4 보통씨</div>
          </button>
          <button class="battle_pokemon4" data-pokemon="4">
            <div class="battle_pokemon4_img"><img src="../images/play/battle/battle_bot.gif"/></div>
            <div class="battle_pokemon4_health_bar">
              <div class="battle_pokemon4_health"></div>
            </div>
            <div class="battle_pokemon4_information">LV. 3 약한씨</div>
          </button>
          <button class="battle_pokemon5" data-pokemon="5">
            <div class="battle_pokemon5_img"><img src="../images/play/battle/battle_bot.gif"/></div>
            <div class="battle_pokemon5_health_bar">
              <div class="battle_pokemon5_health"></div>
            </div>
            <div class="battle_pokemon5_information">LV. 2 연약한씨</div>
          </button>
          <button class="battle_pokemon6" data-pokemon="6">
            <div class="battle_pokemon6_img"><img src="../images/play/battle/battle_bot.gif"/></div>
            <div class="battle_pokemon6_health_bar">
              <div class="battle_pokemon6_health"></div>
            </div>
            <div class="battle_pokemon6_information">LV. 1 쭉정이</div>
          </button>
        </div>


        <!-- 아이템 선택창 -->
        <div class="battle_item_select">
          <button class="battle_function_before" id="battle_item_select_before"><img src="../images/play/battle/ax.png"/></button>
          <button class="battle_recovery">
            <div class="battle_recovery_item">회복</div>
          </button>
          <button class="battle_ball">
            <div class="battle_ball_item">볼</div>
          </button>
          <button class="battle_situation">
            <div class="battle_situation_item">상태이상</div>
          </button>
          <button class="battle_battle">
            <div class="battle_battle_item">배틀</div>
          </button>
        </div>


        <!-- 아이템 사용창 -->
        <div class="battle_item_use">

          <div class="battle_item_recovery">
            <button class="battle_function_before" id="battle_item_use_before1"><img src="../images/play/battle/ax.png"/></button>
            <div class="battle_item_particular_Board">
              <div class="battle_item_scroll">
                <!-- for문 걸고 가지고 있는 회복 아이템 집어넣기 -->
                <butotn class="battle_item_use_button">
                  <div class="battle_item_use_img"><img src="../images/play/battle/recovery.png"/></div>
                  <div class="battle_item_use_text">상처약</div>
                  <div class="battle_item_use_quantity">10개</div>
                </button>
              </div>
            </div>
          </div>

          <div class="battle_item_ball">
            <button class="battle_function_before" id="battle_item_use_before2"><img src="../images/play/battle/ax.png"/></button>
            <div class="battle_item_particular_Board">
              <div class="battle_item_scroll">
                <!-- for문 걸고 가지고 있는 볼 아이템 집어넣기 -->
                <butotn class="battle_item_use_button">
                  <div class="battle_item_use_img"><img src="../images/play/battle/ball.png"/></div>
                  <div class="battle_item_use_text">몬스터볼</div>
                  <div class="battle_item_use_quantity">10개</div>
                </button>
              </div>
            </div>
          </div>

          <div class="battle_item_situation">
            <button class="battle_function_before" id="battle_item_use_before3"><img src="../images/play/battle/ax.png"/></button>
            <div class="battle_item_particular_Board">
              <div class="battle_item_scroll">
                <!-- for문 걸고 가지고 있는 상태이상 아이템 집어넣기 -->
                <butotn class="battle_item_use_button">
                  <div class="battle_item_use_img"><img src="../images/play/battle/decoding.png"/></div>
                  <div class="battle_item_use_text">해독체</div>
                  <div class="battle_item_use_quantity">10개</div>
                </button>
              </div>
            </div>
          </div>

          <div class="battle_item_battle">
            <button class="battle_function_before" id="battle_item_use_before4"><img src="../images/play/battle/ax.png"/></button>
            <div class="battle_item_particular_Board">
              <div class="battle_item_scroll">
                <!-- for문 걸고 가지고 있는 배틀 아이템 집어넣기 -->
                <butotn class="battle_item_use_button">
                  <div class="battle_item_use_img"><img src="../images/play/battle/stat_boosts.png"/></div>
                  <div class="battle_item_use_text">이펙트가드</div>
                  <div class="battle_item_use_quantity">10개</div>
                </button>
              </div>
            </div>
          </div>

        </div>

      </div>
    </div>
    <!-- 어두운 배경 레이어 -->
    <div class="dark-overlay" style="display: none;"></div>

  </div>
</body>
</html>