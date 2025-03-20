// 원정대 관련 기능을 위한 모듈
(function() {
    // 원정대 초기 상태 저장 (수정 감지용)
    let initialExpeditionState = [];

    // 문서 로드 완료 시 이벤트 리스너 등록
    $(document).ready(function() {
        console.log('원정대 모듈 로드 완료');

        // 저장 버튼 클릭 이벤트
        $(document).on('click', '#save-expedition-btn', function() {
            console.log('저장 버튼 클릭됨');
            expeditionSaveList();
        });
        
        // 수정 버튼 클릭 이벤트
        $(document).on('click', '#edit-expedition-btn', function() {
            console.log('수정 버튼 클릭됨');
            expeditionEnableEditMode();
        });

        // 원정대에서 포켓몬 제거 이벤트 (동적으로 생성된 요소에 대한 이벤트 위임)
        $(document).on('click', '.remove-pokemon-btn', function(e) {
            e.stopPropagation(); // 이벤트 버블링 방지
            
            const expeditionItem = $(this).closest('.expedition-item');
            
            // 포켓몬 제거 (confirm 대화상자 없이 바로 삭제)
            expeditionItem.find('img').attr('src', '');
            expeditionItem.find('img').attr('alt', '');
            expeditionItem.find('.expedition-description').text('');
            expeditionItem.removeAttr('data-pokemon-id');
            $(this).remove(); // 삭제 버튼 제거
            
            // 원정대 변경 감지 및 버튼 상태 업데이트
            expeditionUpdateButtons();
        });
    });

    // 원정대 초기화 함수 - mypage.js에서 호출됨
    window.expeditionInitialize = function() {
        // 원정대 UI 초기화
        const expeditionItems = $('.expedition-item');
        expeditionItems.each(function() {
            $(this).find('img').attr('src', '');
            $(this).find('.expedition-description').text('');
            $(this).removeAttr('data-pokemon-id');
        });

        // 소유한 포켓몬 목록 컨테이너 초기화
        $('.image-container').empty();

        // 서버에서 원정대 목록과 소유한 포켓몬 목록을 병렬로 가져옴
        $.when(
            // 원정대 목록 가져오기
            $.ajax({
                url: '/ingame/expedition',
                type: 'GET'
            }),
            // 소유한 포켓몬 목록 가져오기
            $.ajax({
                url: '/player/pokemon/me',
                type: 'GET'
            })
        ).then(function(expeditionResponse, playerPokemonResponse) {
            console.log('원정대 및 소유 포켓몬 데이터 로드 완료');
            
            // 플레이어 포켓몬 데이터를 ID를 키로 하는 맵으로 변환
            const playerPokemonMap = {};
            if (playerPokemonResponse[0].status === 'success' && playerPokemonResponse[0].data) {
                playerPokemonResponse[0].data.forEach(function(pokemon) {
                    playerPokemonMap[pokemon.id] = pokemon;
                });
            }
            
            // 원정대 데이터 처리
            if (expeditionResponse[0].status === 'success' && expeditionResponse[0].data) {
                console.log('원정대 목록 조회 성공:', expeditionResponse[0].data);
                
                // 원정대 리스트에 포켓몬 추가
                expeditionResponse[0].data.forEach(function(pokemon) {
                    // 플레이어 포켓몬 데이터에서 이름 가져오기
                    const playerPokemonData = playerPokemonMap[pokemon.id];
                    
                    // 포켓몬 데이터 가공
                    const playerPokemon = {
                        id: pokemon.id,
                        name: (playerPokemonData && playerPokemonData.name) || pokemon.name || '포켓몬 #' + pokemon.id,
                        image: (playerPokemonData && playerPokemonData.image) || pokemon.image || `/images/pokemon/${pokemon.id}.png`
                    };
                    
                    // 원정대에 추가
                    const expeditionItems = $('.expedition-item');
                    let slotIndex = pokemon.slot;
                    
                    // 슬롯 인덱스가 유효한 범위인지 확인
                    if (slotIndex >= 0 && slotIndex < expeditionItems.length) {
                        const item = expeditionItems.eq(slotIndex);
                        const imgElement = item.find('img');
                        const descElement = item.find('.expedition-description');
                        
                        // 포켓몬 이미지와 이름 설정
                        imgElement.attr('src', playerPokemon.image);
                        imgElement.attr('alt', playerPokemon.name);
                        descElement.text(playerPokemon.name);
                        
                        // 포켓몬 ID 저장
                        item.attr('data-pokemon-id', playerPokemon.id);
                    }
                });
            }
            
            // 소유한 포켓몬 목록 처리
            if (playerPokemonResponse[0].status === 'success' && playerPokemonResponse[0].data) {
                console.log('소유한 포켓몬 목록 조회 성공:', playerPokemonResponse[0].data);
                
                // 포켓몬 데이터 처리 및 UI에 표시
                expeditionProcessPlayerPokemonData(playerPokemonResponse[0], function(processedPokemons) {
                    if (processedPokemons && processedPokemons.length > 0) {
                        // 포켓몬 목록을 UI에 추가
                        processedPokemons.forEach(function(pokemon) {
                            expeditionAppendPokemonToContainer(pokemon);
                        });
                    } else {
                        $('.image-container').html('<p>소유한 포켓몬이 없습니다.</p>');
                    }
                });
            } else {
                $('.image-container').html('<p>소유한 포켓몬 정보를 가져오는데 실패했습니다.</p>');
            }
            
            // 원정대 초기 상태 저장 (수정 감지용)
            expeditionSaveInitialState();
            
            // 원정대 변경 감지 및 버튼 상태 업데이트
            expeditionUpdateButtons();
        }).fail(function(error) {
            console.error('데이터 로드 실패:', error);
            $('.image-container').html('<p>데이터를 가져오는데 실패했습니다.</p>');
            
            // 오류 발생 시에도 버튼 상태 업데이트
            expeditionUpdateButtons();
        });
    };

    // 포켓몬을 컨테이너에 추가하는 함수
    function expeditionAppendPokemonToContainer(pokemon) {
        if (!pokemon) return;
        
        // 포켓몬 요소 생성
        const pokemonElement = expeditionCreatePokemonElement(pokemon);
        
        // 컨테이너에 추가
        $('.image-container').append(pokemonElement);
    }

    // 포켓몬 요소 생성 함수
    function expeditionCreatePokemonElement(pokemon) {
        const pokemonElement = $(`
            <div class="pokemon-item owned" data-pokemon-id="${pokemon.id}">
                <img src="${pokemon.image}" alt="${pokemon.name}" onerror="this.src='/images/close.png'" />
                <p>${pokemon.name}</p>
                ${pokemon.level ? `<p>레벨: ${pokemon.level}</p>` : ''}
            </div>
        `);
        
        // 포켓몬 클릭 이벤트 처리
        pokemonElement.on('click', function() {
            // 원정대에 포켓몬 추가
            expeditionAddPokemon(pokemon);
        });
        
        return pokemonElement;
    }

    // 플레이어 포켓몬 데이터 처리 함수
    function expeditionProcessPlayerPokemonData(response, callback) {
        console.log("플레이어 포켓몬 데이터:", response);
        
        // response가 배열인 경우 (이미 포켓몬 목록인 경우)
        if (Array.isArray(response)) {
            processPokemons(response);
        }
        // response가 객체이고 data 속성이 있는 경우
        else if (response && response.data) {
            processPokemons(response.data);
        } else {
            console.error("유효하지 않은 포켓몬 데이터 형식:", response);
            callback([]);
            return;
        }
        
        function processPokemons(playerPokemons) {
            if (!playerPokemons || playerPokemons.length === 0) {
                callback([]);
                return;
            }
            
            // 포켓몬 상세 정보 가져오기
            const promises = playerPokemons.map(playerPokemon => {
                // pokemonId가 없는 경우 id를 사용
                const pokemonId = playerPokemon.pokemonId || playerPokemon.id;
                
                if (!pokemonId) {
                    console.error('포켓몬 ID가 없습니다:', playerPokemon);
                    return Promise.resolve(null);
                }
                
                return new Promise((resolve, reject) => {
                    $.ajax({
                        url: `/data/pokemon/${pokemonId}`,
                        type: 'GET',
                        dataType: 'json',
                        cache: false,
                        success: function(pokemonDetail) {
                            resolve({
                                id: playerPokemon.id,
                                pokemonId: pokemonId,
                                name: playerPokemon.name || pokemonDetail.name || `포켓몬 #${pokemonId}`,
                                image: pokemonDetail.image || playerPokemon.image || `/images/pokemon/${pokemonId}.png`,
                                level: playerPokemon.level,
                                hp: playerPokemon.hp,
                                exp: playerPokemon.exp,
                                isOwned: true
                            });
                        },
                        error: function(error) {
                            console.error(`포켓몬 ${pokemonId} 정보 가져오기 실패:`, error);
                            resolve({
                                id: playerPokemon.id,
                                pokemonId: pokemonId,
                                name: playerPokemon.name || `포켓몬 #${pokemonId}`,
                                image: playerPokemon.image || `/images/pokemon/${pokemonId}.png`,
                                level: playerPokemon.level,
                                hp: playerPokemon.hp,
                                exp: playerPokemon.exp,
                                isOwned: true
                            });
                        }
                    });
                });
            });
            
            Promise.all(promises)
                .then(results => {
                    // null 값 필터링
                    const filteredResults = results.filter(result => result !== null);
                    callback(filteredResults);
                })
                .catch(error => {
                    console.error("포켓몬 데이터 처리 중 오류 발생:", error);
                    callback([]);
                });
        }
    }

    // 원정대 리스트에 포켓몬 추가 함수
    function expeditionAddPokemon(playerPokemon) {
        // 이미 원정대에 추가된 포켓몬인지 확인
        const alreadyAdded = expeditionCheckPokemonAlreadyAdded(playerPokemon.id);
        if (alreadyAdded) {
            alert(`${playerPokemon.name}은(는) 이미 원정대에 추가되어 있습니다.`);
            return;
        }
        
        // 현재 원정대 리스트의 빈 슬롯 찾기
        const expeditionItems = $('.expedition-item');
        let emptySlotFound = false;
        
        expeditionItems.each(function(index) {
            const imgElement = $(this).find('img');
            const descElement = $(this).find('.expedition-description');
            
            // 이미지 소스가 없거나 비어있는 경우 빈 슬롯으로 간주
            if (!imgElement.attr('src') || imgElement.attr('src') === '') {
                // 포켓몬 이미지와 이름 설정
                imgElement.attr('src', playerPokemon.image);
                imgElement.attr('alt', playerPokemon.name);
                descElement.text(playerPokemon.name);
                
                // 포켓몬 ID 저장 (나중에 서버에 전송하기 위해)
                $(this).attr('data-pokemon-id', playerPokemon.id);
                
                // 삭제 버튼 추가
                if (!$(this).find('.remove-pokemon-btn').length) {
                    $(this).append('<button class="remove-pokemon-btn">✕</button>');
                }
                
                console.log(`포켓몬 ${playerPokemon.name}(ID: ${playerPokemon.id})이(가) 원정대 슬롯 ${index+1}에 추가되었습니다.`);
                
                emptySlotFound = true;
                
                // 원정대 변경 감지 및 버튼 상태 업데이트
                expeditionUpdateButtons();
                
                return false; // each 루프 중단
            }
        });
        
        // 빈 슬롯이 없는 경우 알림
        if (!emptySlotFound) {
            alert('원정대가 가득 찼습니다. 다른 포켓몬을 제거한 후 다시 시도하세요.');
        }
    }

    // 포켓몬이 이미 원정대에 추가되어 있는지 확인하는 함수
    function expeditionCheckPokemonAlreadyAdded(pokemonId) {
        let isAlreadyAdded = false;
        
        $('.expedition-item').each(function() {
            const addedPokemonId = $(this).attr('data-pokemon-id');
            if (addedPokemonId && addedPokemonId == pokemonId) {
                isAlreadyAdded = true;
                return false; // each 루프 중단
            }
        });
        
        return isAlreadyAdded;
    }

    // 원정대 리스트 저장 함수
    function expeditionSaveList() {
        const expeditionPokemonIds = [];
        
        // 원정대에 있는 포켓몬 ID 수집
        $('.expedition-item').each(function() {
            const pokemonId = $(this).attr('data-pokemon-id');
            if (pokemonId) {
                expeditionPokemonIds.push(parseInt(pokemonId));
            }
        });
        
        if (expeditionPokemonIds.length === 0) {
            alert('저장할 원정대 포켓몬이 없습니다.');
            return;
        }

        console.log('저장할 원정대 포켓몬 ID 목록:', expeditionPokemonIds);
        
        // 서버에 원정대 리스트 저장 요청
        $.ajax({
            url: '/ingame/expedition/save',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(expeditionPokemonIds),
            success: function(response) {
                console.log('원정대 리스트 저장 성공:', response);
                
                // 저장 후 버튼 상태 업데이트
                $('#save-expedition-btn').prop('disabled', true);
                $('#edit-expedition-btn').prop('disabled', false);
                
                // 모든 X 버튼 제거
                $('.remove-pokemon-btn').remove();
                
                // editable 클래스 제거
                $('.expedition-item').removeClass('editable');
                
                // 인라인 스타일 제거 (만약 있다면)
                $('style:contains(".expedition-item:not([data-pokemon-id]) .remove-pokemon-btn")').remove();
                
                // 원정대 초기 상태 저장 (수정 감지용)
                expeditionSaveInitialState();
            },
            error: function(error) {
                console.error('원정대 리스트 저장 실패:', error);
                alert('원정대 리스트 저장에 실패했습니다. 오류: ' + (error.responseJSON ? error.responseJSON.message : error.statusText));
            }
        });
    }

    // 원정대 초기 상태 저장 (수정 감지용)
    function expeditionSaveInitialState() {
        initialExpeditionState = [];
        
        $('.expedition-item').each(function() {
            const pokemonId = $(this).attr('data-pokemon-id') || '';
            initialExpeditionState.push(pokemonId);
        });
    }

    // 원정대 변경 감지 및 버튼 상태 업데이트
    function expeditionUpdateButtons() {
        // 현재 원정대 상태 확인
        const currentState = [];
        let hasAnyPokemon = false;
        
        $('.expedition-item').each(function(index) {
            const pokemonId = $(this).attr('data-pokemon-id') || '';
            currentState.push(pokemonId);
            if (pokemonId) {
                hasAnyPokemon = true;
            }
        });
        
        // 초기 상태와 비교하여 변경 여부 확인
        let isChanged = false;
        
        if (initialExpeditionState.length === 0) {
            // 초기 상태가 없는 경우 (첫 로드)
            isChanged = hasAnyPokemon;
        } else {
            // 초기 상태와 비교
            for (let i = 0; i < currentState.length; i++) {
                if (currentState[i] !== initialExpeditionState[i]) {
                    isChanged = true;
                    break;
                }
            }
        }
        
        // 버튼 상태 업데이트
        $('#save-expedition-btn').prop('disabled', !isChanged);
        $('#edit-expedition-btn').prop('disabled', !hasAnyPokemon);
    }

    // 원정대 리스트 수정 모드 활성화 함수
    function expeditionEnableEditMode() {
        // 수정 모드 활성화 로직
        $('.expedition-item').addClass('editable');
        
        // 먼저 기존 이벤트 핸들러와 버튼 제거
        $('.expedition-item.editable').off('click');
        $('.remove-pokemon-btn').remove();
        
        // 포켓몬이 있는 아이템에만 X 버튼 추가
        $('.expedition-item.editable').each(function() {
            const pokemonId = $(this).attr('data-pokemon-id');
            if (pokemonId) {
                // X 버튼 추가
                const removeBtn = $('<button class="remove-pokemon-btn">X</button>');
                $(this).append(removeBtn);
                
                // X 버튼에 클릭 이벤트 추가
                removeBtn.on('click', function(e) {
                    e.stopPropagation(); // 이벤트 버블링 방지
                    
                    // 포켓몬 제거
                    const item = $(this).parent();
                    item.find('img').attr('src', '');
                    item.find('img').attr('alt', '');
                    item.find('.expedition-description').text('');
                    item.removeAttr('data-pokemon-id');
                    $(this).remove(); // 삭제 버튼 제거
                    
                    // 원정대 변경 감지 및 버튼 상태 업데이트
                    expeditionUpdateButtons();
                });
            }
        });
        
        // 인라인 스타일 추가 - 빈 슬롯에 호버 효과 방지
        $('<style>')
            .prop('type', 'text/css')
            .html(`
                .expedition-item:not([data-pokemon-id]) .remove-pokemon-btn { 
                    display: none !important; 
                }
                .expedition-item:not([data-pokemon-id]):hover .remove-pokemon-btn { 
                    display: none !important; 
                }
            `)
            .appendTo('head');
    }
})();