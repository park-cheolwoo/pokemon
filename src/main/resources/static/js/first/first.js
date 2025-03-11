// 엔터키를 눌렀을 때 텍스트를 변경하는 함수
document.addEventListener('keydown', function(event) {
  if (event.key === 'Enter') {  // Enter 키가 눌렸을 때
    changeText();
  }
});

let step = 0;
const chatText = document.getElementById('chatText');  // 'chatText' ID로 참조
const enter = document.getElementById('enter');  // 'enter' ID로 참조

function changeText() {
  // 이전 텍스트를 사라지게 하기 위해 애니메이션을 추가
  chatText.style.animation = 'chatDisappear 2s forwards';

  // 애니메이션 끝난 후 텍스트를 변경하고 다시 나타나게 하기
  setTimeout(() => {
    if (step === 0) {
      chatText.innerHTML = '앞으로 당신과 함께할 포켓몬입니다.';
      step++;
    } else if (step === 1) {
      chatText.innerHTML = '원하는 포켓몬을 선택해 주세요.';
      step++;

      // "원하는 포켓몬을 선택해 주세요." 문구가 보일 때 enter 깜빡임 멈추기
      enter.style.animation = '';  // enter의 애니메이션을 중지시켜줍니다.
      enter.style.opacity = '0';   // enter를 사라지게 설정

      // 이미지들 등장시키기
      showImages();
    }

    // 텍스트 변경 후 다시 나타나게 애니메이션 설정
    chatText.style.animation = 'chatAppear 1s forwards';
  }, 500); // 애니메이션 시간과 동일한 시간(2초) 후에 텍스트 변경
}

// 이미지들이 순차적으로 등장하는 함수
function showImages() {
  const starting1 = document.getElementById('starting1');
  const starting2 = document.getElementById('starting2');
  const starting3 = document.getElementById('starting3');

  // 각각의 이미지를 0.5초 간격으로 나타나게 설정
  setTimeout(() => {
    starting1.style.opacity = 1; // 첫 번째 이미지 등장
  }, 500);  // 500ms 후 첫 번째 이미지 등장

  setTimeout(() => {
    starting2.style.opacity = 1; // 두 번째 이미지 등장
  }, 1000); // 1500ms 후 두 번째 이미지 등장

  setTimeout(() => {
    starting3.style.opacity = 1; // 세 번째 이미지 등장
  }, 1500); // 2500ms 후 세 번째 이미지 등장
}

// 포켓몬 이미지를 클릭했을 때 어두운 배경을 띄우고 해당 이미지만 남겨두기
function showDark(imageNumber) {
  const dark = document.getElementById('deep_dark');
  const chat1 = document.getElementById('chat_1');

  // 일정 시간이 지난 후에 어두운 배경과 텍스트를 보여주기
  setTimeout(() => {
    dark.style.display = 'block';  // 어두운 배경을 보이게 설정
    chat1.style.opacity = 1;  // chat_1의 텍스트를 보이게 설정

    // 선택된 이미지에 'z-index'로 다른 요소 위로 올리기
    const selectedImage = document.getElementById('starting' + imageNumber);
    selectedImage.style.zIndex = 20; // 클릭한 이미지를 다른 요소 위로 올리기
  }, 100); // 100ms 후에 어두운 배경을 띄움

  // chat_1 텍스트 설정
  if (imageNumber === 1) {
    chat1.innerHTML = '이상해씨를 선택하시겠습니까?';
  } else if (imageNumber === 2) {
    chat1.innerHTML = '파이리를 선택하시겠습니까?';
  } else if (imageNumber === 3) {
    chat1.innerHTML = '꼬북이를 선택하시겠습니까?';
  }
}

// "chack_1" 버튼을 클릭할 때 첫 번째 nameboard를 숨기고 두 번째 nameboard를 표시하는 코드
const chackButton1 = document.querySelector('.chack_1');  // 첫 번째 chack 버튼

chackButton1.addEventListener('click', function() {
  // 첫 번째 nameboard 숨기기
  const nameboard1 = document.querySelectorAll('.nameboard')[0];  // 첫 번째 nameboard
  nameboard1.style.display = 'none';

  // 두 번째 nameboard 보이기
  const nameboard2 = document.querySelectorAll('.nameboard')[1];  // 두 번째 nameboard
  nameboard2.style.display = 'block';

  // 2그룹의 텍스트 변경: 선택된 포켓몬에 맞는 문구 삽입
  const chat2 = document.getElementById('chat_2');
  const chat1 = document.getElementById('chat_1');
  const selectedPokemon = chat1.innerHTML;

  if (selectedPokemon.includes('이상해씨')) {
    chat2.innerHTML = '이상해씨의 이름을 짓겠습니까?';
  } else if (selectedPokemon.includes('파이리')) {
    chat2.innerHTML = '파이리의 이름을 짓겠습니까?';
  } else if (selectedPokemon.includes('꼬북이')) {
    chat2.innerHTML = '꼬북이의 이름을 짓겠습니까?';
  }
});

// ax 버튼을 눌렀을 때만 hideDark()를 실행하도록 이벤트 리스너 추가
document.querySelector('.ax_1').addEventListener('click', function() {
  // 어두운 배경을 숨기는 함수
  function hideDark() {
    const deep_dark = document.getElementById('deep_dark');
    deep_dark.style.display = 'none'; // 어두운 배경을 숨기기
    // 클릭한 이미지의 'z-index'를 기본으로 돌려놓기
    const images = document.querySelectorAll('#starting1, #starting2, #starting3');
    images.forEach(image => {
      image.style.zIndex = ''; // 기본 'z-index' 값으로 되돌리기
    });
  }
  hideDark(); // ax 버튼 클릭 시에만 hideDark() 함수 호출
});

// chack_2 버튼 클릭 시 두 번째 nameboard를 숨기고 세 번째 nameboard를 보이게 하는 코드
document.querySelector('.chack_2').addEventListener('click', function() {
  // 두 번째 nameboard 숨기기
  const nameboard2 = document.querySelectorAll('.nameboard')[1]; // 두 번째 nameboard
  nameboard2.style.display = 'none';

  // 세 번째 nameboard 보이기
  const nameboard3 = document.querySelectorAll('.nameboard')[2]; // 세 번째 nameboard
  nameboard3.style.display = 'block';

  // 세 번째 nameboard의 input에 포커스를 설정
  const inputField = nameboard3.querySelector('input');
  inputField.focus();
});
// chack_3 버튼 클릭 시
document.querySelector('.chack_3').addEventListener('click', function() {
  // chatText 텍스트 변경
  const chatText = document.getElementById('chatText');
  chatText.innerHTML = '가자 포켓몬 세계로~~!';

  // 1초 후에 페이지 이동
  setTimeout(function() {
    window.location.href = "/";  // 1초 후에 메인 페이지로 리디렉션
  }, 1500); // 1000ms (1초) 후에 실행

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
});

// ax_2 버튼 클릭 시
document.querySelector('.ax_2').addEventListener('click', function() {
  // chatText 텍스트 변경
  const chatText = document.getElementById('chatText');
  chatText.innerHTML = '가자 포켓몬 세계로~~!';

  // 1초 후에 페이지 이동
  setTimeout(function() {
    window.location.href = "/";  // 1초 후에 메인 페이지로 리디렉션
  }, 1500); // 1000ms (1초) 후에 실행

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
});
