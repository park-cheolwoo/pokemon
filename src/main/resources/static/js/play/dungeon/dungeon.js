$(function() {
    let selectedHabitat = "";
    let selectedDifficulty = "";

    const habitatButtonTextMap = {
        "cave": "동굴",
        "forest": "숲",
        "grassland": "목초지",
        "mountain": "산",
        "rare": "희귀한",
        "rough-terrain": "거친",
        "sea": "바다",
        "urban": "도시",
        "waters-edge": "물가"
    };

    const difficultyLevels = {
        1: "Lv.1 ~ Lv.5",
        2: "Lv.6 ~ Lv.10",
        3: "Lv.11 ~ Lv.15",
        4: "Lv.16 ~ Lv.20",
        5: "Lv.21 ~ Lv.25",
        6: "Lv.26 ~ Lv.30"
    };

    function updateSubtitle() {
        if (selectedHabitat && selectedDifficulty) {
            $(".subtitle").text(`${selectedHabitat} ${selectedDifficulty}`);
        }
    }

    $.ajax({
        url: "/game-stage/all?size=1000",
        type: "GET",
        success: function(stage) {
            if (stage !== undefined) {
                $.ajax({
                    url: "/data/habitat",
                    type: "GET",
                    success: function(habitat) {
                        console.log(habitat);

                        habitat.forEach(h => {
                            const koreanName = habitatButtonTextMap[h.name] || h.name;
                            const htn = `<button id="${h.id}" class="habitat-btn" data-habitat="${h.name}"
                                style="background-image: url(../images/play/dungeon/signboard_0.png)">${koreanName}</button>`;
                            $(".Habitat_Selection").append(htn);
                        });

                        // ✅ AJAX 완료 후 '동굴' 자동 선택
                        setTimeout(() => {
                            const initialHabitat = $(".habitat-btn[data-habitat='cave']");
                            if (initialHabitat.length) {
                                initialHabitat.trigger("click");
                            }
                        }, 200);
                    }
                });

                // 🏕️ 서식지 버튼 클릭 이벤트
                $(document).on("click", ".habitat-btn", function() {
                    $(".habitat-btn").css("background-image", "url(../images/play/dungeon/signboard_0.png)");
                    $(this).css("background-image", "url(../images/play/dungeon/signboard_1.png)");

                    selectedHabitat = $(this).text();
                    updateSubtitle();

                    $("#difficulty-level-container").empty();
                    const habitatId = $(this).attr("id");
                    $("#environment-image img").attr("src", `../images/play/dungeon/Battle_environment${habitatId}.png`);

                    // 🏆 해당 서식지에 맞는 스테이지만 필터링 후 버튼 추가
                    stage.forEach(s => {
                        if (s.habitatId == habitatId) {
							const sbtn = $("<button>", {class: "difficulty-btn", id: s.id, text: `${s.stage} 단계` });
							
							sbtn.css({backgroundImage: "url(/images/play/dungeon/Difficulty_level.png)"});
							
							sbtn.attr("data-min", s.minLevel);
							sbtn.attr("data-max", s.maxLevel);

                            $("#difficulty-level-container").append(sbtn);
                        }
                    });

                    // ✅ 자동으로 '1단계' 선택
                    setTimeout(() => {
                        const initialDifficulty = $(".difficulty-btn[data-level='1']");
                        if (initialDifficulty.length) {
                            initialDifficulty.trigger("click");
                        }
                    }, 200);
                });

                // 🔥 난이도 버튼 클릭 이벤트
                $(document).on("click", ".difficulty-btn", function() {
                    $(".difficulty-btn").css("background-image", "url(../images/play/dungeon/Difficulty_level.png)");
                    $(this).css("background-image", "url(../images/play/dungeon/Difficulty_level_1.png)");
					const id = $(this).attr("id");
					const minLevel = $(this).attr("data-min");
					const maxLevel = $(this).attr("data-max");

                    selectedDifficulty = $(this).text();
                    updateSubtitle();

                    const level = $(this).data("level");
                    $(".explanation").text(`Lv. ${minLevel} ~ Lv. ${maxLevel}` || "Lv.1 ~ Lv.5");
					
					$("#dungeon_go").attr("data-id", id);
                });

				// 🎮 던전 입장 버튼 클릭 이벤트
				$(document).on("click", "#dungeon_go", function() {
				    const selectedDifficultyButton = $(".difficulty-btn[style*='Difficulty_level_1.png']");
					const go_id = $(this).attr("data-id");
				    
				    if (go_id === undefined) {
				        alert("난이도를 선택해주세요!");
				        return;
				    }

				    $.ajax({
				        url: `/ingame/stage/${go_id}`,
				        type: "POST",
				        data: session_id,
				        contentType: "application/json",
				        success: function(data) {
							console.log(data);
				            location.href = "/play/battle1";
				        },
				        error: function(xhr, status, error) {
				            console.error("전송 실패:", error);
				        }
				    });
				});

            }
        }
    });
});


//const habitatValues = {
//    "cave": 0,
//    "forest": 20,
//    "meadow": 40,
//    "mountain": 60,
//    "rare": 80,
//    "rough": 100,
//    "sea": 120,
//    "city": 140,
//    "shore": 160
//};

//const difficultyValues = {
//    "1": 1,
//    "2": 2,
//    "3": 3,
//    "4": 4,
//    "5": 5,
//    "6": 6
//};

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