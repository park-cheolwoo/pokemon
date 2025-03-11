document.addEventListener("DOMContentLoaded", function() {
  const battleChat = document.querySelector('.battle_chat1');
  const text1 = "야생의 이상해씨가 나타났다.";
  const text2 = "가랏 건강한씨!!";
  const text3 = "건강한씨는 무엇을할까?";
  let i = 0;

  // 텍스트를 한 글자씩 표시하는 함수
  function typeText(text, targetElement, callback) {
    if (i < text.length) {
      targetElement.innerHTML += text.charAt(i);
      i++;
      setTimeout(() => typeText(text, targetElement, callback), 80);
    } else if (callback) {
      callback(); 
    }
  }

  // 첫 번째 텍스트 출력
  setTimeout(function() {
    battleChat.style.width = `${battleChat.scrollWidth}px`; 
    typeText(text1, battleChat, function() {
      setTimeout(function() {
        battleChat.innerHTML = "";
        i = 0;
        typeText(text2, battleChat, null);
        setTimeout(function() {
          battleChat.innerHTML = "";
          i = 0;
          typeText(text3, battleChat, null);
        }, 1700);
      }, 800);
    });
  }, 2000);

  // "싸운다" 버튼 클릭 이벤트
  const battleFightButton = document.querySelector('.battle_fight');
  const battleAction = document.querySelector('.battle_action');
  const battleTechnology = document.querySelector('.battle_technology');

  battleFightButton.addEventListener('click', function() {
    battleAction.style.display = 'none';
    battleTechnology.style.display = 'block';
  });

  // "뒤로가기" 버튼 클릭 이벤트 (battle_technology를 닫고 battle_action을 열기)
  const battleTechnologyBackButton = document.querySelector('.battle_technology_back');
  battleTechnologyBackButton.addEventListener('click', function() {
    battleTechnology.style.display = 'none';
    battleAction.style.animation = 'none'; 
    battleAction.style.animationDelay = '0s';
    battleAction.style.opacity = 1;
    battleAction.style.display = 'block';
    
    setTimeout(function() {
      battleAction.style.animation = ''; 
    }, 10);
  });
  // "배틀 포켓몬" 버튼 클릭 이벤트
  const battlePokemonButton = document.querySelector('.battle_pokemon'); 
  const battlePokemonChang = document.querySelector('.battle_pokemon_chang'); 
  const battleDark = document.querySelector('.battle_dark');
  const battleChang1 = document.querySelector('.battle_chang1');

  battlePokemonButton.addEventListener('click', function() {
    battleAction.style.display = 'none';
    battleChang1.style.display = 'none';
    battlePokemonChang.style.display = 'block';
    battleDark.style.display = 'block';
  });
  // "포켓몬 뒤로가기" 버튼 클릭 이벤트
  const battlePokemonBeforeButton = document.querySelector('#battle_pokemon_before');
  const battleChat2SelectNo = document.querySelector('.battle_chat2_select_pokemon_no'); 
  battlePokemonBeforeButton.addEventListener('click', function() {
    battleDark.style.display = 'none';
    battlePokemonChang.style.display = 'none';
    battleAction.style.animation = 'none';
    battleAction.style.animationDelay = '0s';
    battleAction.style.opacity = 1;
    battleAction.style.display = 'block';
    battleChang1.style.animation = 'none';
    battleChang1.style.animationDelay = '0s';
    battleChang1.style.opacity = 1;
    battleChang1.style.display = 'block';
    battleChat2SelectNo.click();

    setTimeout(function() {
        battleAction.style.animation = ''; 
    }, 10);
  });
  // "아이템" 버튼 클릭 이벤트
  const battleItem = document.querySelector('.battle_item');
  const battleIitemSelect = document.querySelector('.battle_item_select');

  battleItem.addEventListener('click', function() {
    battleAction.style.display = 'none';
    battleChang1.style.display = 'none';
    battleIitemSelect.style.display = 'block';
    battleDark.style.display = 'block';
  });
  // "아이템 뒤로가기" 버튼 클릭 이벤트
  const battleItemSelectBefore = document.querySelector('#battle_item_select_before');
  battleItemSelectBefore.addEventListener('click',function(){
    battleDark.style.display = 'none';
    battleIitemSelect.style.display = 'none';
    battleAction.style.animation = 'none';
    battleAction.style.animationDelay = '0s';
    battleAction.style.opacity = 1;
    battleAction.style.display = 'block';
    battleChang1.style.animation = 'none';
    battleChang1.style.animationDelay = '0s';
    battleChang1.style.opacity = 1;
    battleChang1.style.display = 'block';

    setTimeout(function() {
        battleAction.style.animation = ''; 
    }, 10);
  });
  // "회복 아이템" 버틑 클릭 이벤트
  const battleRecovery = document.querySelector('.battle_recovery');
  const battleItemUse = document.querySelector('.battle_item_use');
  const battleItemRecovery = document.querySelector('.battle_item_recovery');
  battleRecovery.addEventListener('click',function(){
    battleIitemSelect.style.display = 'none';
    battleItemUse.style.display = 'block';
    battleItemRecovery.style.display = 'block';
  });
  // "회복 아이템 뒤로가기" 버튼 클릭 이벤트
  const battleItemUseBefore1 = document.querySelector('#battle_item_use_before1');
  const battleChat2SelectRecoveryNo = document.querySelector('.battle_chat2_select_recovery_no');
  battleItemUseBefore1.addEventListener('click',function(){
    battleIitemSelect.style.display = 'block';
    battleItemUse.style.display = 'none';
    battleItemRecovery.style.display = 'none';
    battleChat2SelectRecoveryNo.click();
  });
  // "볼 아이템" 버튼 클릭 이벤트
  const battleBall = document.querySelector('.battle_ball');
  const battleItemBall = document.querySelector('.battle_item_ball');
  battleBall.addEventListener('click',function(){
    battleIitemSelect.style.display = 'none';
    battleItemUse.style.display = 'block';
    battleItemBall.style.display = 'block';
  });
  // "볼 아이템 뒤로가기" 버튼 클릭 이벤트
  const battleItemUseBefore2 = document.querySelector('#battle_item_use_before2');
  battleItemUseBefore2.addEventListener('click',function(){
    battleIitemSelect.style.display = 'block';
    battleItemUse.style.display = 'none';
    battleItemBall.style.display = 'none';
  });
  
  // "상태이상 아이템" 버튼 클릭 이벤트
  const battleSituation = document.querySelector('.battle_situation');
  const battleItemSituation = document.querySelector('.battle_item_situation');
  battleSituation.addEventListener('click',function(){
    battleIitemSelect.style.display = 'none';
    battleItemUse.style.display = 'block';
    battleItemSituation.style.display = 'block';
  });
  // "상태이상 아이템 뒤로가기" 버튼 클릭 이벤트
  const battleItemUseBefore3 = document.querySelector('#battle_item_use_before3');
  battleItemUseBefore3.addEventListener('click',function(){
    battleIitemSelect.style.display = 'block';
    battleItemUse.style.display = 'none';
    battleItemSituation.style.display = 'none';
  });
  // "배틀 아이템" 버튼 클릭 이벤트
  const battleBattle = document.querySelector('.battle_battle');
  const battleItemBattle = document.querySelector('.battle_item_battle');
  battleBattle.addEventListener('click',function(){
    battleIitemSelect.style.display = 'none';
    battleItemUse.style.display = 'block';
    battleItemBattle.style.display = 'block';
  });
  // "배틀 아이템 뒤로가기" 버튼 클릭 이벤트
  const battleItemUseBefore4 = document.querySelector('#battle_item_use_before4');
  battleItemUseBefore4.addEventListener('click',function(){
    battleIitemSelect.style.display = 'block';
    battleItemUse.style.display = 'none';
    battleItemBattle.style.display = 'none';
  });
});


// 공통된 health bar 업데이트 함수
let currentHealth1 = 100;
let currentHealth2 = 100;

function updateHealthBar(healthBarSelector, healthPercentage) {
  const healthBar = document.querySelector(healthBarSelector);
  let width = healthPercentage;

  // 색상 설정
  if (width <= 10) {
    healthBar.style.backgroundColor = 'red';
  } else if (width <= 50) {
    healthBar.style.backgroundColor = 'yellow';
  } else {
    healthBar.style.backgroundColor = 'green';
  }

  healthBar.style.width = `${width}%`;
}
// 초기 상태 설정
updateHealthBar('.health_bar1', 100);
updateHealthBar('.health_bar2', 100);
updateHealthBar('.battle_pokemon1_health', 100);
updateHealthBar('.battle_pokemon2_health', 100);
updateHealthBar('.battle_pokemon3_health', 60);
updateHealthBar('.battle_pokemon4_health', 100);
updateHealthBar('.battle_pokemon5_health', 100);
updateHealthBar('.battle_pokemon6_health', 100);


const battleItemUseButton = document.querySelector('.battle_item_use_button');
const battleChang2 = document.querySelector('.battle_chang2');
const battleChat2 = document.querySelector('.battle_chat2');
const battleChat2SelectRecovery = document.querySelector('.battle_chat2_select_recovery');
const battleChat2SelectRecoveryNo = document.querySelector('.battle_chat2_select_recovery_no');
const battleChat2SelectRecoveryYes = document.querySelector('.battle_chat2_select_recovery_yes');
const battleItemRecovery = document.querySelector('.battle_item_recovery');
// battle_item_use_button 클릭 시
battleItemUseButton.addEventListener('click', function() {
  battleChat2.textContent = "상처약을 사용하시겠습니까?"; 
  battleChang2.style.display = 'block';
  battleChat2SelectRecovery.style.display = 'block';
});

// battle_chat2_select_recovery_no 클릭 시
battleChat2SelectRecoveryNo.addEventListener('click', function() {
  battleChang2.style.display = 'none';
  battleChat2SelectRecovery.style.display = 'none';
});

// battle_chat2_select_recovery_yes 클릭시
battleChat2SelectRecoveryYes.addEventListener('click', function() {
  battleItemRecovery.style.display = 'none';
  battlePokemonChang.style.display = 'block';
  battleChat2.innerHTML = '어떤 캐릭에게 사용하시겠습니까?';
  
});
// 포켓몬 선택 버튼들
const pokemonSelectButtons = document.querySelectorAll('.battle_pokemon1, .battle_pokemon2, .battle_pokemon3, .battle_pokemon4, .battle_pokemon5, .battle_pokemon6');

// 각 버튼에 클릭 이벤트 추가
pokemonSelectButtons.forEach(button => {
  button.addEventListener('click', function() {
    // 클릭한 버튼의 data-pokemon 값에 따라 해당 포켓몬의 체력 바를 선택
    const pokemonIndex = button.getAttribute('data-pokemon'); // 1~6

    // 해당 포켓몬의 체력 바 선택
    const healthBar = document.querySelector(`.battle_pokemon${pokemonIndex} .battle_pokemon${pokemonIndex}_health`);

    // 현재 width 값을 가져와서 20만큼 증가
    let currentWidth = parseInt(healthBar.style.width) || 0; // 기본 값은 0
    let newWidth = currentWidth + 20; // 20만큼 증가

    // width가 100을 초과하지 않도록 제한
    if (newWidth > 100) {
      newWidth = 100; // 최대 100
    }

    // width 값 업데이트
    healthBar.style.width = newWidth + '%'; // % 단위로 변경
  });
});
