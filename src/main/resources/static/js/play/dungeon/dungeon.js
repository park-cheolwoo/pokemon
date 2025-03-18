const habitatButtons = document.querySelectorAll('.habitat-btn');
const difficultyButtons = document.querySelectorAll('.difficulty-btn');
const environmentImage = document.getElementById('environment-image');
const subtitle = document.querySelector('.subtitle');
const explanation = document.querySelector('.explanation');
let selectedHabitat = '';
let selectedDifficulty = '';

// 페이지가 로드될 때 자동으로 동굴과 1단계 버튼을 클릭 상태로 설정합니다.
document.addEventListener('DOMContentLoaded', () => {
  // 초기 선택 상태로 '동굴' 버튼을 클릭합니다.
  const initialHabitatButton = document.querySelector('.habitat-btn[data-habitat="cave"]');
  if (initialHabitatButton) {
    initialHabitatButton.click();
  }

  // 초기 선택 상태로 '1 단계' 버튼을 클릭합니다.
  const initialDifficultyButton = document.querySelector('.difficulty-btn[data-level="1"]');
  if (initialDifficultyButton) {
    initialDifficultyButton.click();
  }
});

// 각 habitat 버튼에 클릭 이벤트를 추가합니다.
habitatButtons.forEach(button => {
  button.addEventListener('click', () => {
    // 모든 habitat 버튼의 background-image를 signboard_0.png로 초기화합니다.
    habitatButtons.forEach(btn => {
      btn.style.backgroundImage = 'url(../images/play/dungeon/signboard_0.png)';
    });

    // 클릭한 habitat 버튼의 background-image를 signboard_1.png로 바꿉니다.
    button.style.backgroundImage = 'url(../images/play/dungeon/signboard_1.png)';

    // 클릭한 habitat 버튼의 텍스트를 selectedHabitat에 저장합니다.
    selectedHabitat = button.textContent;

    // subtitle을 업데이트합니다.
    if (selectedHabitat && selectedDifficulty) {
      subtitle.textContent = `${selectedHabitat} ${selectedDifficulty}`;
    }

    // 버튼에 따라 해당하는 이미지를 변경합니다.
    switch (button.getAttribute('data-habitat')) {
      case 'cave':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment1.png">';
        break;
      case 'forest':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment2.png">';
        break;
      case 'meadow':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment3.png">';
        break;
      case 'mountain':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment4.png">';
        break;
      case 'rare':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment5.png">';
        break;
      case 'rough':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment6.png">';
        break;
      case 'sea':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment7.png">';
        break;
      case 'city':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment8.png">';
        break;
      case 'shore':
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment9.png">';
        break;
      default:
        environmentImage.innerHTML = '<img src="../images/play/dungeon/Battle_environment1.png">';
    }
  });
});

// 각 difficulty 버튼에 클릭 이벤트를 추가합니다.
difficultyButtons.forEach(button => {
  button.addEventListener('click', () => {
    // 모든 difficulty 버튼의 background-image를 초기화합니다.
    difficultyButtons.forEach(btn => {
      btn.style.backgroundImage = 'url(../images/play/dungeon/Difficulty_level.png)';
    });

    // 클릭한 difficulty 버튼의 background-image를 Difficulty_level_1.png로 바꿉니다.
    button.style.backgroundImage = 'url(../images/play/dungeon/Difficulty_level_1.png)';

    // 클릭한 difficulty 버튼의 텍스트를 selectedDifficulty에 저장합니다.
    selectedDifficulty = button.textContent;

    // subtitle을 업데이트합니다.
    if (selectedHabitat && selectedDifficulty) {
      subtitle.textContent = `${selectedHabitat} ${selectedDifficulty}`;
    }

    // 각 difficulty에 맞는 설명을 업데이트합니다.
    switch (button.getAttribute('data-level')) {
      case '1':
        explanation.textContent = 'Lv.1 ~ Lv.5';
        break;
      case '2':
        explanation.textContent = 'Lv.6 ~ Lv.10';
        break;
      case '3':
        explanation.textContent = 'Lv.11 ~ Lv.15';
        break;
      case '4':
        explanation.textContent = 'Lv.16 ~ Lv.20';
        break;
      case '5':
        explanation.textContent = 'Lv.21 ~ Lv.25';
        break;
      case '6':
        explanation.textContent = 'Lv.26 ~ Lv.30';
        break;
      default:
        explanation.textContent = 'Lv.1 ~ Lv.5';
    }
  });
});
