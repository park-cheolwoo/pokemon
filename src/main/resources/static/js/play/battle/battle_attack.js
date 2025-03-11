// 어택류 공격 (나도 공격 상대도 공격 죽을 때까지)
document.addEventListener("DOMContentLoaded", function() {
  const battleTechnologyButton1 = document.querySelector('.battle_technology_1');
  const battleMyImg = document.querySelector('.battle_my_img');
  const battleBotImg = document.querySelector('.battle_bot_img');
  const battleChat1 = document.querySelector('.battle_chat1'); 
  const battleTechnology = document.querySelector('.battle_technology'); 
  const battleAction = document.querySelector('.battle_action'); 
  const battlePokemonChang = document.querySelector('.battle_pokemon_chang'); 
  const battle_my = document.querySelector('.battle_my'); 
  const battleDark = document.querySelector('.battle_dark');
  const battleChang1 = document.querySelector('.battle_chang1');

  battleTechnologyButton1.addEventListener('click', function() {
    battleMyImg.style.animation = 'none';
    battleMyImg.offsetHeight; 
    battleMyImg.style.animation = 'moveLeft 0.8s ease';
    battleMyImg.style.animationDelay = '1.5s';

    battleBotImg.style.animation = 'none';
    battleBotImg.offsetHeight; 
    battleBotImg.style.animation = 'blink 0.2s ease-in-out 4';
    battleBotImg.style.animationDelay = '2.3s';

    const message1 = "건강한씨의\n몸통박치기!!";
    let index1 = 0;
    battleChat1.innerHTML = ''; 

    function typeText1() {
      if (index1 < message1.length) {
        battleChat1.innerHTML += message1.charAt(index1) === '\n' ? '<br>' : message1.charAt(index1);
        index1++;
        setTimeout(typeText1, 80); 
      }
    }
    typeText1();

    // 2.3초 뒤에 내 공격 데미지 적용
    setTimeout(() => {
      currentHealth2 -= 50;
      if (currentHealth2 < 0) currentHealth2 = 0;

      updateHealthBar('.health_bar1', currentHealth2);
    }, 2300);

    // 상대방 공격 애니메이션만 실행되기 전에 health_bar1이 0인지 확인
    setTimeout(() => {
      if (currentHealth2 <= 0) {
        // 상대방이 쓰러졌을 경우 "쓰러졌다" 메시지 출력
        const message4 = "야생 이상해씨은(는) 쓰러졌다!";
        let index4 = 0;

        setTimeout(function() {
          battleChat1.innerHTML = '';
          function typeText4() {
            if (index4 < message4.length) {
              battleChat1.innerHTML += message4.charAt(index4) === '\n' ? '<br>' : message4.charAt(index4);
              index4++;
              setTimeout(typeText4, 80); 
            }
          }
          typeText4();
        
          // "쓰러졌다" 메시지가 출력된 후에 battle_bot 전체 사라짐 애니메이션 적용
          setTimeout(function() {
            const battleBot = document.querySelector('.battle_bot'); // battle_bot 요소를 선택
            battleBot.classList.add('fadeOut'); // fadeOut 클래스를 추가하여 사라짐 애니메이션 시작
        
            // battle_bot이 사라진 후 "경험치를 얻었다" 메시지 출력
            setTimeout(function() {
              const message5 = "건강한 씨은(는) 24 경험치를 얻었다";
              let index5 = 0;
              battleChat1.innerHTML = ''; // 이전 메시지 클리어
        
              function typeText5() {
                if (index5 < message5.length) {
                  battleChat1.innerHTML += message5.charAt(index5) === '\n' ? '<br>' : message5.charAt(index5);
                  index5++;
                  setTimeout(typeText5, 80); 
                }
              }
              typeText5();
        
              // "경험치를 얻었다" 메시지가 끝난 후 화면 어두워지게 만들기
              setTimeout(function() {
                const darkOverlay = document.querySelector('.dark-overlay');
                darkOverlay.style.display = 'block'; // 어두운 배경을 나타내기
                darkOverlay.style.animation = 'none'; // 기존 애니메이션 제거
                darkOverlay.offsetHeight; // 애니메이션을 다시 실행하기 위해 강제로 reflow (갱신)
                darkOverlay.style.animation = 'fadeIn 2s ease-in-out forwards'; // 애니메이션을 다시 설정
                
                // 2초 후에 href="/"로 이동
                setTimeout(function() {
                  window.location.href = '/'; // 페이지 리다이렉션
                }, 2000); // 2초 후에 이동
              }, 2000); // 메시지가 출력된 후 2초 뒤에 어두운 배경을 보이도록 설정
        
            }, 2000); // 2초 후에 "경험치를 얻었다" 메시지 출력
          }, 3000); // "쓰러졌다" 메시지가 출력 후 3초 뒤에 battle_bot 사라짐 애니메이션 시작
        }, 3000); // "쓰러졌다" 메시지가 출력되는 시점 설정
        
      } else {
        // 상대방 공격 애니메이션
        battleBotImg.style.animation = 'none';
        battleBotImg.offsetHeight; 
        battleBotImg.style.animation = 'moveRight 0.8s ease';
        battleBotImg.style.animationDelay = '2s';

        battleMyImg.style.animation = 'none';
        battleMyImg.offsetHeight; 
        battleMyImg.style.animation = 'blink 0.2s ease-in-out 4';
        battleMyImg.style.animationDelay = '2.8s';

        const message2 = "야생 이상해씨의\n몸통박치기!!";
        let index2 = 0;

        setTimeout(function() {
          battleChat1.innerHTML = '';
          function typeText2() {
            if (index2 < message2.length) {
              battleChat1.innerHTML += message2.charAt(index2) === '\n' ? '<br>' : message2.charAt(index2);
              index2++;
              setTimeout(typeText2, 80); 
            }
          }
          typeText2();
        }, 500);

        setTimeout(() => {
          currentHealth1 -= 100; 
          if (currentHealth1 < 0) currentHealth1 = 0;

          updateHealthBar('.health_bar2', currentHealth1);
          updateHealthBar('.battle_pokemon1_health_bar', currentHealth1);

        }, 2800);
        
        // **여기서 나의 체력이 0 이하인지 확인하고, 원상복귀 처리하기 전에 조건을 체크**
        setTimeout(() => {
          if (currentHealth1 <= 0) {
            // 나의 체력이 0 이하일 경우 원상복귀 생략하고 "쓰러졌다" 메시지 출력
            const message3 = "건강한씨은(는) 쓰러졌다!";
            let index3 = 0;

            setTimeout(function() {
              battleChat1.innerHTML = ''; 
              function typeText3() {
                if (index3 < message3.length) {
                  battleChat1.innerHTML += message3.charAt(index3) === '\n' ? '<br>' : message3.charAt(index3);
                  index3++;
                  setTimeout(typeText3, 80); 
                }
              }
              typeText3();
            }, 1500);

            // "쓰러졌다" 메시지가 출력된 후 battle_technology를 none으로 바꾸고 battle_pokemon_chang를 보여줌
            setTimeout(function() {
              battleTechnology.style.display = 'none'; // battle_technology 숨기기
              battle_my.style.display = 'none';
              battleDark.style.display = 'block';
              battlePokemonChang.style.display = 'block'; // battle_pokemon_chang 보이기
              battleChang1.style.display = 'none'
            }, 3000); // "쓰러졌다" 메시지가 출력된 후 3초 뒤에 변경
            
          } else {
            // 원상복귀 처리
            const message3 = "건강한씨은(는) 무엇을할까?";
            let index3 = 0;
            
            setTimeout(function() {
              battleChat1.innerHTML = ''; 
              function typeText3() {
                if (index3 < message3.length) {
                  battleChat1.innerHTML += message3.charAt(index3) === '\n' ? '<br>' : message3.charAt(index3);
                  index3++;
                  setTimeout(typeText3, 80); 
                }
              }
              typeText3();
            }, 4000);
            
            setTimeout(() => {
              battleTechnology.style.display = 'none';
              battleAction.style.animationDelay = '0s';
              battleAction.style.opacity = 1;
              battleAction.style.display = 'block';
            }, 5000);
          }
        }, 2800); // 상대방 공격 후 2.8초 뒤에 나의 체력이 0인지 확인
      }
    }, 2500);

  });
});
