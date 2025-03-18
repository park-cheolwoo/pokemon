// 전역 변수로 포켓몬 정보 관리
const pokemonIdMap = { 1: 1, 2: 4, 3: 7 }; // 포켓몬 ID 매핑
let selectedPokemonId = null;
let selectedPokemonName = ''; 
let selectedNickname = ''; 

// 엔터키를 눌렀을 때 텍스트를 변경하는 함수
document.addEventListener('keydown', function (event) {
  if (event.key === 'Enter') { // Enter 키가 눌렸을 때
    changeText();
  }
});

let step = 0;
const chatText = document.getElementById('chatText'); // 'chatText' ID로 참조
const enter = document.getElementById('enter'); // 'enter' ID로 참조

function changeText() {
  // 이전 텍스트를 사라지게 하기 위해 애니메이션 추가
  chatText.style.animation = 'chatDisappear 2s forwards';

  setTimeout(() => {
    if (step === 0) {
      chatText.innerHTML = '앞으로 당신과 함께할 포켓몬입니다.';
      step++;
    } else if (step === 1) {
      chatText.innerHTML = '원하는 포켓몬을 선택해 주세요.';
      step++;

      // "원하는 포켓몬을 선택해 주세요." 문구가 보일 때 enter 애니메이션 멈추기
      enter.style.animation = ''; // enter의 애니메이션 중지
      enter.style.opacity = '0'; // enter를 사라지게 설정

      // 이미지 등장시키기
      showImages();
    }

    // 텍스트 변경 후 다시 나타나게 애니메이션 설정
    chatText.style.animation = 'chatAppear 1s forwards';
  }, 500); // 애니메이션 시간과 동일한 시간(0.5초) 후에 텍스트 변경
}

// 이미지들이 순차적으로 등장하는 함수
function showImages() {
  const starting1 = document.getElementById('starting1');
  const starting2 = document.getElementById('starting2');
  const starting3 = document.getElementById('starting3');

  // 각각의 이미지를 순차적으로 등장시키기
  setTimeout(() => {
    starting1.style.opacity = 1; // 첫 번째 이미지
  }, 500);

  setTimeout(() => {
    starting2.style.opacity = 1; // 두 번째 이미지
  }, 1000);

  setTimeout(() => {
    starting3.style.opacity = 1; // 세 번째 이미지
  }, 1500);
}

// 포켓몬 이미지를 클릭했을 때 어두운 배경을 띄우고 해당 이미지만 남겨두기
function showDark(imageNumber) {
  const dark = document.getElementById('deep_dark');
  const chat1 = document.getElementById('chat_1');

  selectedPokemonId = pokemonIdMap[imageNumber]; // 포켓몬 ID 저장
  if (imageNumber === 1) {
    selectedPokemonName = '이상해씨';
    chat1.innerHTML = '이상해씨를 선택하시겠습니까?';
  } else if (imageNumber === 2) {
    selectedPokemonName = '파이리';
    chat1.innerHTML = '파이리를 선택하시겠습니까?';
  } else if (imageNumber === 3) {
    selectedPokemonName = '꼬북이';
    chat1.innerHTML = '꼬북이를 선택하시겠습니까?';
  }

  setTimeout(() => {
    dark.style.display = 'block'; // 어두운 배경 보이기
    chat1.style.opacity = 1; // 텍스트 표시

    const selectedImage = document.getElementById('starting' + imageNumber);
    selectedImage.style.zIndex = 20; // 클릭한 이미지를 강조
  }, 100);
}

// 첫 번째 확인 버튼 클릭
document.querySelector('.chack_1').addEventListener('click', function () {
  const nameboard1 = document.querySelectorAll('.nameboard')[0];
  const nameboard2 = document.querySelectorAll('.nameboard')[1];

  nameboard1.style.display = 'none'; // 첫 번째 nameboard 숨기기
  nameboard2.style.display = 'block'; // 두 번째 nameboard 보이기

  const chat2 = document.getElementById('chat_2');
  chat2.innerHTML = `${selectedPokemonName}의 이름을 짓겠습니까?`;
});

// 두 번째 확인 버튼 클릭
document.querySelector('.chack_2').addEventListener('click', function () {
  const nameboard2 = document.querySelectorAll('.nameboard')[1];
  const nameboard3 = document.querySelectorAll('.nameboard')[2];

  nameboard2.style.display = 'none'; // 두 번째 nameboard 숨기기
  nameboard3.style.display = 'block'; // 세 번째 nameboard 보이기

  const inputField = nameboard3.querySelector('input');
  inputField.focus(); // 입력란에 포커스
});

function savePokemonData() {
  fetch('/player/pokemon/select', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      pokemonId: selectedPokemonId,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.status === 'success') {
        console.log('포켓몬 저장 성공');
        chatText.innerHTML = '가자 포켓몬 세계로~~!';

        setTimeout(() => {
          window.location.href = '/';
        }, 1500);
		// 모든 nameboard 숨기기
		  const nameboards = document.querySelectorAll('.nameboard');
		  nameboards.forEach(board => {
		    board.style.display = 'none';
		  });

		  // starting 이미지 초기화
		  const startingImages = document.querySelectorAll('#starting1, #starting2, #starting3');
		  startingImages.forEach(image => {
		    image.style.opacity = '0'; // 이미지 숨기기
		  });

		  // 어두운 배경 숨기기
		  const darkBackground = document.getElementById('deep_dark');
		  darkBackground.style.display = 'none'; // 어두운 배경 숨기기
		  
      } else {
        console.error('포켓몬 저장 실패:', data.message);
      }
    })
    .catch((error) => console.error('저장 요청 중 에러 발생:', error));
}

// 기본 이름으로 저장 (.ax_2 클릭 시)
document.querySelector('.ax_2').addEventListener('click', function () {
  selectedNickname = selectedPokemonName; 
  savePokemonData();
});

// 사용자 입력 이름으로 저장 (.chack_3 클릭 시)
document.querySelector('.chack_3').addEventListener('click', function () {
  const namingInput = document.getElementById('naming');
  selectedNickname = namingInput.value.trim(); 

  if (!selectedNickname) {
    alert('포켓몬의 이름을 입력해주세요!');
    return;
  }

  savePokemonData();
});

// 취소 버튼 클릭 (.ax_1)
document.querySelector('.ax_1').addEventListener('click', function () {
  const dark = document.getElementById('deep_dark');
  dark.style.display = 'none'; // 어두운 배경 숨기기

  const images = document.querySelectorAll('#starting1, #starting2, #starting3');
  images.forEach(image => {
    image.style.zIndex = ''; // z-index 초기화
  });
});
