const habitatButtons = document.querySelectorAll('.habitat-btn');
const difficultyButtons = document.querySelectorAll('.difficulty-btn');
const environmentImage = document.getElementById('environment-image');
const subtitle = document.querySelector('.subtitle');
const explanation = document.querySelector('.explanation');
const habitatValues = {
    "cave": 0,
    "forest": 20,
    "meadow": 40,
    "mountain": 60,
    "rare": 80,
    "rough": 100,
    "sea": 120,
    "city": 140,
    "shore": 160
};

const difficultyValues = {
    "1": 1,
    "2": 2,
    "3": 3,
    "4": 4,
    "5": 5,
    "6": 6
};

const habitatButtonTextMap = {
    "동굴": "cave",
    "숲": "forest",
    "목초지": "meadow",
    "산": "mountain",
    "희귀한": "rare",
    "거친": "rough",
    "바다": "sea",
    "도시": "city",
    "물가": "shore"
};

const difficultyButtonTextMap = {
    "1 단계": "1",
    "2 단계": "2",
    "3 단계": "3",
    "4 단계": "4",
    "5 단계": "5",
    "6 단계": "6"
};

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

// 시작 버튼 클릭 이벤트
//document.getElementById('dungeon_go').addEventListener('click', () => {
//    // 값이 비어 있으면 기본값 설정
//    if (!selectedHabitat) {
//        selectedHabitat = '동굴';  // 기본값 '동굴'
//    }
//    if (!selectedDifficulty) {
//        selectedDifficulty = '1 단계';  // 기본값 '1 단계'
//    }

//    console.log(`selectedHabitat: ${selectedHabitat}`); // 값을 확인
//    console.log(`selectedDifficulty: ${selectedDifficulty}`); // 값을 확인

//    // habitat 값 가져오기 (한글 -> 영어 매핑)
//    const habitatKey = habitatButtonTextMap[selectedHabitat];
//    const habitatValue = habitatValues[habitatKey];
//    const difficultyValue = difficultyValues[difficultyButtonTextMap[selectedDifficulty]];
//
//    console.log(`habitatValue: ${habitatValue}, difficultyValue: ${difficultyValue}`);  // 값을 확인
//
//    // stageId 계산
//    const stageId = habitatValue + difficultyValue;
//
//    console.log(`선택된 stageId: ${stageId}`);  // 계산된 stageId 출력
//
//    // 페이지 이동
//    const url = `/play/battle1?stageId=${stageId}`;
//    window.location.href = url;
//});

$(function() {
    $.ajax({
        url: "/game-stage/all",
        type: "GET",
        success: function(stage) {
            if (stage !== undefined) {
                $.ajax({
                    url: "/data/habitat",
                    type: "GET",
                    success: function(habitat) {
                        console.log(habitat);
                        habitat.forEach(h => {
                            const htn = `
                            <button id="${h.id}" class="habitat-btn" style="background-image: url(../images/play/dungeon/signboard_0.png)">${h.name}</button>
                            `;
                            $(".Habitat_Selection").append(htn);
                        });
                    }
                });

                // 서식지 버튼 클릭 이벤트
                $(document).on("click", ".habitat-btn", function() {
                    $("#difficulty-level-container").children().remove();
                    const habitatId = $(this).attr("id");

                    // 해당 서식지에 맞는 스테이지만 필터링 후 버튼 추가
                    stage.forEach(s => {
                        if (s.habitatId == habitatId) {
                            const sBtn = `
                            <button class="difficulty" data-habitat-id="${s.habitatId}" data-stage="${s.stage}" style="background-image: url(../images/play/dungeon/Difficulty_level_1.png);">${s.stage} 단계</button>
                            `;
                            $("#difficulty-level-container").append(sBtn);
                        }
                    });
                });

                // 난이도 버튼 클릭 이벤트
                $(document).on("click", ".difficulty", function() {

	                    const stageData = {
	                        habitatId: $(this).data("habitat-id"), // 선택된 habitatId
	                        stage: $(this).data("stage") // 선택된 stage
	                    };
					$(document).on("click", "#dungeon_go", function() {
	                    console.log("보낼 데이터:", stageData); // 디버깅용
	                    $.ajax({
	                        url: "/ingame/stage",
	                        type: "POST",
	                        data: JSON.stringify(stageData),
	                        success: function() {
	                            location.href = "/play/battle1"; // 성공 시 페이지 이동
	                        },
	                        error: function(xhr, status, error) {
	                            console.error("전송 실패:", error);
	                        }
	                    });
					});
                });
            }
        }
    });
});
