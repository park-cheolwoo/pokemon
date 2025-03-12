// 포켓몬 교체
const battleChat2SelectPokemon = document.querySelector('.battle_chat2_select_pokemon'); // battle_chat2_select_pokemon
const battleChat2SelectYes = document.querySelector('.battle_chat2_select_pokemon_yes');
const battleChat2SelectNo = document.querySelector('.battle_chat2_select_pokemon_no');
const battleDark = document.querySelector('.battle_dark');
const battlePokemonChang = document.querySelector('.battle_pokemon_chang'); 
const battleAction = document.querySelector('.battle_action');
const battleChang1 = document.querySelector('.battle_chang1');
let selectedPokemon = null; // 선택된 포켓몬을 전역 변수로 저장

// 각 버튼에 이벤트 리스너를 추가하여 포켓몬 선택 후 확인창 표시
pokemonSelectButtons.forEach(button => {
  button.addEventListener('click', function() {
    selectedPokemon = button.getAttribute('data-pokemon'); // 클릭한 버튼의 data-pokemon 값 (1~6)
    
    // 포켓몬 이름 가져오기
    let pokemonName;
    switch (selectedPokemon) {
      case '1':
        pokemonName = "건강한씨";
        break;
      case '2':
        pokemonName = "강한씨";
        break;
      case '3':
        pokemonName = "보통씨";
        break;
      case '4':
        pokemonName = "약한씨";
        break;
      case '5':
        pokemonName = "연약한씨";
        break;
      case '6':
        pokemonName = "쭉정이";
        break;
    }

    // battle_chat2에 메시지 삽입
    battleChat2.textContent = `${pokemonName} 으로 교체하시겠습니까?`;

    // battle_chang2, battle_chat2_select를 보여주기
    battleChang2.style.display = 'block';
    battleChat2SelectPokemon.style.display = 'block';
  });
});

// 예/아니요 버튼에 이벤트 추가
battleChat2SelectYes.addEventListener('click', function() {

  // 교체 후 battle_chang2 숨기기
  battleChang2.style.display = 'none';
  battleChat2SelectPokemon.style.display = 'none';
  battleDark.style.display = 'none';
  battlePokemonChang.style.display = 'none';

  // 애니메이션을 바로 적용
  battleChang1.style.animation = 'none';
  battleChang1.style.animationDelay = '0s';
  battleChang1.style.opacity = 1;
  battleChang1.style.display = 'block';

  // "돌아와 {건강한씨 이름}!" 메시지 타이핑 효과
  const battleChat1 = document.querySelector('.battle_chat1');
  const myPokemonName = document.querySelector('.battle_name_my').textContent; // '건강한씨'는 여기서 가져옴
  const message1 = `돌아와 ${myPokemonName}!`;
  let index1 = 0;
  battleChat1.innerHTML = ''; // 기존 메시지 지우기

  function typeText1() {
    if (index1 < message1.length) {
      battleChat1.innerHTML += message1.charAt(index1) === '\n' ? '<br>' : message1.charAt(index1);
      index1++;
      setTimeout(typeText1, 80); // 80ms마다 한 글자씩 타이핑
    } else {
      // "돌아와 건강한씨!" 타이핑 끝나면 다음 작업 시작
      setTimeout(() => {
        // battle_my 이미지 점차 사라지게 만들기
        const battleMy = document.querySelector('.battle_my');
        battleMy.style.animation = 'none';
        battleMy.style.animationDelay = '0s';
        battleMy.style.animation = 'fadeOut 1.5s forwards';

        // 2초 후 "나와랏 {강한씨 이름}!" 타이핑 시작
        setTimeout(() => {
          battleChat1.innerHTML = ''; // 기존 메시지 지우기
          
          // 선택된 포켓몬 이름을 가져옴
          let selectedPokemonName;
          switch (selectedPokemon) {
            case '1':
              selectedPokemonName = "건강한씨"; // 예시로 '강한씨'
              break;
            case '2':
              selectedPokemonName = "강한씨";
              break;
            case '3':
              selectedPokemonName = "보통씨";
              break;
            case '4':
              selectedPokemonName = "약한씨";
              break;
            case '5':
              selectedPokemonName = "연약한씨";
              break;
            case '6':
              selectedPokemonName = "쭉정이";
              break;
          }
          
          const message2 = `너만 믿어!!\n나와랏 ${selectedPokemonName}!`;
          let index2 = 0;

          function typeText2() {
            if (index2 < message2.length) {
              battleChat1.innerHTML += message2.charAt(index2) === '\n' ? '<br>' : message2.charAt(index2);
              index2++;
              setTimeout(typeText2, 80); // 80ms마다 한 글자씩 타이핑
            }
          }

          typeText2(); // "나와랏 강한씨!" 타이핑 시작

          if (!selectedPokemon) return; // selectedPokemon이 없으면 함수 종료

          // 선택된 포켓몬으로 교체하기 (이전 코드 내용 추가)
          const myPokemonImage = document.querySelector('#myPokemonImage'); // 내모습 이미지 요소
          const pokemonImages = {
            1: "../images/play/battle/battle_my.gif",  // 첫 번째 포켓몬
            2: "../images/play/battle/battle_my2.gif", // 두 번째 포켓몬
            3: "../images/play/battle/battle_my.gif",  // 첫 번째 포켓몬
            4: "../images/play/battle/battle_my.gif",  // 첫 번째 포켓몬
            5: "../images/play/battle/battle_my.gif",  // 첫 번째 포켓몬
            6: "../images/play/battle/battle_my.gif",  // 첫 번째 포켓몬
          };
          
          myPokemonImage.src = pokemonImages[selectedPokemon]; // 이미지를 교체
        
          // 포켓몬의 이름과 레벨을 교체
          const pokemonNameDisplay = document.querySelector('.battle_name_my');
          const pokemonLevelDisplay = document.querySelector('.battle_lv_my');
        
          switch (selectedPokemon) {
            case '1':
              pokemonNameDisplay.textContent = "건강한씨";
              pokemonLevelDisplay.textContent = "LV.6";
              break;
            case '2':
              pokemonNameDisplay.textContent = "강한씨";
              pokemonLevelDisplay.textContent = "LV.5";
              break;
            case '3':
              pokemonNameDisplay.textContent = "보통씨";
              pokemonLevelDisplay.textContent = "LV.4";
              break;
            case '4':
              pokemonNameDisplay.textContent = "약한씨";
              pokemonLevelDisplay.textContent = "LV.3";
              break;
            case '5':
              pokemonNameDisplay.textContent = "연약한씨";
              pokemonLevelDisplay.textContent = "LV.2";
              break;
            case '6':
              pokemonNameDisplay.textContent = "쭉정이";
              pokemonLevelDisplay.textContent = "LV.1";
              break;
          }
          
          // 타이핑 끝난 후 battle_my 다시 페이드인
          setTimeout(() => {
            const battleMy = document.querySelector('.battle_my');
            battleMy.style.animation = 'none'; // 애니메이션 초기화
            battleMy.style.opacity = 0; // 초기 상태로 설정 (사라짐 상태)
            battleMy.style.display = 'block'; // 요소를 보이게 설정

            // fadeIn 애니메이션 추가
            battleMy.style.animation = 'fadeIn 1.5s forwards';
            
            setTimeout(() => {
              const message3 = `${selectedPokemonName}는 무엇을 할까?`;
              let index3 = 0;
              battleChat1.innerHTML = ''; // 기존 메시지 지우기

              function typeText3() {
                if (index3 < message3.length) {
                  battleChat1.innerHTML += message3.charAt(index3) === '\n' ? '<br>' : message3.charAt(index3);
                  index3++;
                  setTimeout(typeText3, 80); // 80ms마다 한 글자씩 타이핑
                } else {
                  // "강한씨는 무엇을 할까?" 타이핑이 끝나면 battleAction을 표시
                  setTimeout(() => {
                    // battleAction에 애니메이션 적용
                    battleAction.style.animation = 'none';
                    battleAction.style.animationDelay = '0s';
                    battleAction.style.opacity = 1;
                    battleAction.style.display = 'block';
                  }, 500); // 0.5초 후에 battleAction 애니메이션 시작
                }
              }

              typeText3(); // "강한씨는 무엇을 할까?" 타이핑 시작
            }, message2.length * 80 + 800); // 타이핑 끝난 후에 실행
          }, 1500); // 2초 후 타이핑 시작
        }, 1500); // 2초 후 타이핑 시작
      }, 0);
    }
  }

  typeText1(); // 첫 번째 메시지 타이핑 시작
});



battleChat2SelectNo.addEventListener('click', function() {
  // 교체를 취소하고 battle_chang2 숨기기
  battleChang2.style.display = 'none';
  battleChat2SelectPokemon.style.display = 'none';
});

