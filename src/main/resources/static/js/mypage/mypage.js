$(document).ready(function () {
    const tabs = $('.banner-item');
    const contentContainer = $('#content-container');

    // 기본적으로 "도감" 탭에 active 클래스 추가
    const defaultTab = $('.banner-item[data-tab="pokedex"]');
    defaultTab.addClass('active');

    // 기본적으로 "도감" 탭 내용 로드
    loadPage('/mypage/pokedex');

    // 탭 클릭 시 해당하는 페이지 로드
    initializeTabs(tabs);

    function loadPage(page) {
        $.ajax({
            url: page,
            method: 'GET',
            success: function (response) {
                contentContainer.html(response);
                if (page === '/mypage/item') {
                    initializeItemTabs();
                }
                if (page === '/mypage/pokedex') {
                    initializePokedex();
                }
                if (page === '/mypage/pokedexView') {
                    initializePokedexViewTabs();
                }
                if (page === '/mypage/myPokemon') {
                    initializeMyPokemonTabs();
                }
                if (page === '/mypage/expedition') {
                    expeditionInitialize();
                }
            },
            error: function () {
                contentContainer.html("페이지를 로드하는데 실패했습니다.");
            }
        });
    }

    function updateURL(tabId) {
        let url = '/mypage/';
        switch (tabId) {
            case 'pokedex':
                url += 'pokedex';
                break;
            case 'pokedexView':
                url += 'pokedexView';
                break;
            case 'item':
                url += 'item';
                break;
            case 'expedition':
                url += 'expedition';
                break;
            case 'myPokemon':
                url += 'myPokemon';
                break;
            default:
                url += 'pokedex';
                break;
        }
        history.pushState(null, '', url);
    }

    function initializeTabs(tabs) {
        tabs.on('click', function () {
            tabs.removeClass('active');
            $(this).addClass('active');

            const tabId = $(this).data('tab');
            updateURL(tabId); // URL 업데이트 함수 호출

            switch (tabId) {
                case 'pokedex':
                    loadPage('/mypage/pokedex');
                    break;
                case 'item':
                    loadPage('/mypage/item');
                    break;
                case 'expedition':
                    loadPage('/mypage/expedition');
                    break;
                case 'myPokemon':
                    loadPage('/mypage/myPokemon');
                    break;
                default:
                    loadPage('/mypage/pokedex');
                    break;
            }
        });
    }

    // 포켓몬 리스트 초기화 공통 함수
    function initializePokemonList(options) {
        const {
            containerSelector = '.image-container',
            dataUrl = '/data/pokemon',
            pageSize = 50,
            onPokemonClick = null,
            additionalDataProcessor = null,
            showOwnedStatus = true
        } = options;

        const imageContainer = $(containerSelector);
        if (!imageContainer.length) return;

        // 상태 변수 초기화
        let currentPage = 0;
        let isLoading = false;
        let hasMoreData = true;
        let lastScrollTop = 0;
        let scrollTimer = null;
        const loadedPokemonIds = new Set();

        // 초기 데이터 로드
        loadPokemonData();

        // 스크롤 이벤트 리스너 추가 (디바운싱 적용)
        imageContainer.on('scroll', function () {
            const scrollTop = $(this).scrollTop();
            const scrollDirection = scrollTop > lastScrollTop ? 'down' : 'up';
            lastScrollTop = scrollTop;

            // 아래로 스크롤할 때만 데이터 로드
            if (scrollDirection === 'down') {
                const scrollPosition = scrollTop + $(this).innerHeight();
                const scrollHeight = $(this)[0].scrollHeight;

                // 디바운싱 적용
                clearTimeout(scrollTimer);
                scrollTimer = setTimeout(() => {
                    if (scrollPosition >= scrollHeight - 100 && !isLoading && hasMoreData) {
                        loadPokemonData();
                    }
                }, 200);
            }
        });

        // 포켓몬 데이터 로드 함수
        function loadPokemonData() {
            isLoading = true;

            // 타임스탬프 추가로 캐시 방지
            const timestamp = new Date().getTime();
            const url = `${dataUrl}?page=${currentPage}&size=${pageSize}&_=${timestamp}`;

            $.ajax({
                url: url,
                type: 'GET',
                dataType: 'json',
                cache: false,
                success: function (response) {
                    console.log("API 응답:", response);

                    // 응답이 없거나 빈 배열인 경우
                    if (!response || (Array.isArray(response) && response.length === 0) ||
                        (response.data && Array.isArray(response.data) && response.data.length === 0)) {
                        hasMoreData = false;
                        isLoading = false;
                        if (currentPage === 0) {
                            imageContainer.append('<div class="no-pokemon-message">포켓몬이 없습니다.</div>');
                        }
                        return;
                    }

                    // 추가 데이터 처리가 필요한 경우
                    if (additionalDataProcessor) {
                        additionalDataProcessor(response, function (processedData) {
                            if (processedData && processedData.length > 0) {
                                processPokemonData(processedData);
                                currentPage++;
                            } else {
                                hasMoreData = false;
                                if (currentPage === 0) {
                                    imageContainer.append('<div class="no-pokemon-message">포켓몬이 없습니다.</div>');
                                }
                            }
                            isLoading = false;
                        });
                    } else {
                        // 일반 포켓몬 데이터 처리 (도감 탭)
                        let pokemonList = Array.isArray(response) ? response :
                            (response.data && Array.isArray(response.data)) ? response.data : [];

                        if (pokemonList.length > 0) {
                            if (showOwnedStatus) {
                                // 소유한 포켓몬 목록 가져오기
                                $.ajax({
                                    url: `/player/pokemon/me`,
                                    type: 'GET',
                                    dataType: 'json',
                                    cache: false,
                                    success: function (ownershipResponse) {
                                        if (ownershipResponse.status === "success" && ownershipResponse.data) {
                                            // 소유한 포켓몬 ID 목록 생성
                                            const ownedPokemonIds = new Set(ownershipResponse.data.map(pokemon => pokemon.pokemonId));

                                            // 포켓몬 데이터에 소유 여부 추가
                                            pokemonList.forEach(pokemon => {
                                                pokemon.isOwned = ownedPokemonIds.has(pokemon.id);
                                            });
                                        }

                                        processPokemonData(pokemonList);
                                        currentPage++;
                                        isLoading = false;
                                    },
                                    error: function (error) {
                                        console.error('소유 포켓몬 목록 가져오기 실패:', error);
                                        // 오류 발생 시 소유 여부 없이 처리
                                        processPokemonData(pokemonList);
                                        currentPage++;
                                        isLoading = false;
                                    }
                                });
                            } else {
                                processPokemonData(pokemonList);
                                currentPage++;
                                isLoading = false;
                            }
                        } else {
                            hasMoreData = false;
                            isLoading = false;
                            if (currentPage === 0) {
                                imageContainer.append('<div class="no-pokemon-message">포켓몬이 없습니다.</div>');
                            }
                        }
                    }
                },
                error: function (error) {
                    console.error('포켓몬 데이터 로드 실패:', error);
                    isLoading = false;
                    if (currentPage === 0) {
                        imageContainer.append('<div class="no-pokemon-message">포켓몬 정보를 불러오는데 실패했습니다.</div>');
                    }
                }
            });
        }

        // 포켓몬 데이터 처리 함수
        function processPokemonData(pokemonList) {
            if (!pokemonList || !Array.isArray(pokemonList) || pokemonList.length === 0) {
                console.log("처리할 포켓몬 데이터가 없습니다.");
                return;
            }

            console.log("처리할 포켓몬 데이터:", pokemonList);

            pokemonList.forEach(pokemon => {
                if (!pokemon) return;

                const pokemonId = pokemon.id || pokemon.pokemonId;
                if (!pokemonId) return;

                if (!loadedPokemonIds.has(pokemonId)) {
                    loadedPokemonIds.add(pokemonId);
                    appendPokemonToContainer(pokemon);
                }
            });
        }

        // 포켓몬을 컨테이너에 추가하는 함수
        function appendPokemonToContainer(pokemon) {
            const pokemonElement = createPokemonElement(pokemon);
            imageContainer.append(pokemonElement);
        }

        // 포켓몬 요소 생성 함수
        function createPokemonElement(pokemon) {
            // 소유 여부에 따라 클래스 추가
            const ownershipClass = pokemon.isOwned ? 'owned' : 'not-owned';

            const levelInfo = pokemon.level ? `<p>레벨: ${pokemon.level}</p>` : '';

            const pokemonElement = $(`
                <div class="pokemon-item ${ownershipClass}">
                    <img src="${pokemon.image}" alt="${pokemon.name}" onerror="this.src='/images/close.png'" />
                    <p>${pokemon.name}</p>
                    ${levelInfo}
                </div>
            `);

            // 포켓몬 클릭 이벤트 처리
            if (onPokemonClick) {
                pokemonElement.on('click', function () {
                    onPokemonClick(pokemon);
                });
            }

            return pokemonElement;
        }
    }

    // 도감 초기화 함수
    function initializePokedex() {
        initializePokedexButtons();

        // 도감 포켓몬 리스트 초기화
        initializePokemonList({
            containerSelector: '.image-container',
            dataUrl: '/data/pokemon',
            onPokemonClick: function (pokemon) {
                showPokemonDetails(pokemon.id);
            },
            showOwnedStatus: true
        });
    }

    // 플레이어 포켓몬 데이터 처리 함수
    function processPlayerPokemonData(response, callback) {
        console.log("플레이어 포켓몬 데이터:", response);

        // response가 배열인 경우 (이미 포켓몬 목록인 경우)
        if (Array.isArray(response)) {
            processPokemons(response);
        }
        // response가 객체이고 data 속성이 있는 경우
        else if (response && response.data) {
            processPokemons(response.data);
        }
        // 그 외의 경우 빈 배열 반환
        else {
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
                        success: function (pokemonDetail) {
                            resolve({
                                id: playerPokemon.id,
                                pokemonId: pokemonId,
                                name: playerPokemon.name || pokemonDetail.name, // 플레이어 포켓몬 이름 우선 사용
                                originalName: pokemonDetail.name, // 원래 포켓몬 이름도 저장
                                image: pokemonDetail.image,
                                types: pokemonDetail.types,
                                level: playerPokemon.level || 1,
                                exp: playerPokemon.exp || 0,
                                hp: playerPokemon.hp || 0,
                                attack: playerPokemon.attack || 0,
                                defense: playerPokemon.defense || 0,
                                specialAttack: playerPokemon.specialAttack || 0,
                                specialDefense: playerPokemon.specialDefense || 0,
                                speed: playerPokemon.speed || 0,
                                isOwned: true
                            });
                        },
                        error: function (error) {
                            console.error(`포켓몬 ${pokemonId} 정보 가져오기 실패:`, error);
                            resolve({
                                id: playerPokemon.id,
                                pokemonId: pokemonId,
                                name: playerPokemon.name || `포켓몬 #${pokemonId}`, // 플레이어 포켓몬 이름 우선 사용
                                image: `/images/pokemon/${pokemonId}.png`,
                                level: playerPokemon.level || 1,
                                isOwned: true
                            });
                        }
                    });
                });
            });

            Promise.all(promises)
                .then(pokemonList => {
                    // null 값 제거
                    const filteredList = pokemonList.filter(pokemon => pokemon !== null);
                    console.log("처리된 포켓몬 리스트:", filteredList);
                    callback(filteredList);
                })
                .catch(error => {
                    console.error('포켓몬 상세 정보 가져오기 실패:', error);
                    callback([]);
                });
        }
    }

    function initializePokedexViewTabs() {
        const viewTabs = $('.mypage-banner-tab .banner-item');

        const defaultViewTab = $('.mypage-banner-tab .banner-item[data-tab="pokemon-info"]');
        defaultViewTab.addClass('active');

        loadPokedexViewContent('pokemon-info');

        viewTabs.on('click', function () {
            viewTabs.removeClass('active');
            $(this).addClass('active');

            const tabId = $(this).data('tab');
            loadPokedexViewContent(tabId);
        });
    }

    function loadPokedexViewContent(tabId) {
        const contentTabs = $('.tab-content'); // 모든 탭 콘텐츠 선택
        contentTabs.hide();  // 모든 탭 콘텐츠 숨기기

        const activeTab = $('#' + tabId); // 활성화할 탭 선택
        if (activeTab.length) {
            activeTab.show();  // 해당 탭의 콘텐츠 표시
        }
    }

    function initializePokedexButtons() {
        const viewBarContainer = $('.view-bar-container');
        let selectedPokemonId = null;

        // '자세히' 버튼 클릭 시
        const detailButton = viewBarContainer.find('div:nth-child(1)');
        detailButton.on('click', function () {
            // 선택된 포켓몬 ID가 있는 경우에만 페이지 이동
            if (selectedPokemonId) {
                history.pushState(null, '', `/mypage/pokedexView?id=${selectedPokemonId}`);
                loadPage('/mypage/pokedexView');
            } else {
                console.warn('선택된 포켓몬이 없습니다.');
            }
        });

        // '내 포켓몬' 버튼 클릭 시
        const myPokemonButton = viewBarContainer.find('div:nth-child(2)');
        myPokemonButton.on('click', function () {
            history.pushState(null, '', '/mypage/myPokemon');
            loadPage('/mypage/myPokemon');
        });

        // 전역 변수로 선택된 포켓몬 ID를 저장하는 함수 추가
        window.setSelectedPokemonId = function(pokemonId) {
            selectedPokemonId = pokemonId;
        };
    }

    function initializeMyPokemonTabs() {
        const myPokemonTabs = $('.mypage-banner-tab .banner-item');

        const defaultMyPokemonTab = $('.mypage-banner-tab .banner-item[data-tab="myPokemon-pokemon-info"]');
        defaultMyPokemonTab.addClass('active');

        loadMyPokemonContent('myPokemon-pokemon-info');

        myPokemonTabs.on('click', function () {
            myPokemonTabs.removeClass('active');
            $(this).addClass('active');

            const tabId = $(this).data('tab');
            loadMyPokemonContent(tabId);
        });
    }

    function loadMyPokemonContent(tabId) {
        const contentTabs = $('.tab-content'); // 모든 탭 콘텐츠 선택
        contentTabs.hide();  // 모든 탭 콘텐츠 숨기기

        const activeTab = $('#' + tabId); // 활성화할 탭 선택
        if (activeTab.length) {
            activeTab.show();  // 해당 탭의 콘텐츠 표시
        }
    }

    function showPokemonDetails(pokemonId) {
        // 선택된 포켓몬 ID 저장
        window.setSelectedPokemonId(pokemonId);
        
        // 포켓몬 상세 정보 가져오기
        $.get(`/data/pokemon/${pokemonId}`)
            .done(function (pokemon) {
                // 포켓몬 정보 표시
                const pokeImg = $('.poke-img img');
                const pokeName = $('.poke-infoview-container h4');
                const pokeInfo = $('.poke-infoview-container div:nth-child(2)');
                const pokeDesc = $('.poke-infoview-container div:nth-child(3)');

                // 이미지 업데이트
                pokeImg.attr('src', pokemon.image || `/images/pokemon/${pokemon.id}.png`)
                    .attr('alt', pokemon.name)
                    .attr('onerror', "this.src='/images/close.png'");

                // 이름 업데이트
                pokeName.text(pokemon.name);

                // 정보 업데이트 (속성, 키, 무게, genus)
                let infoHtml = '';
                // genus 정보 추가
                if (pokemon.genus) {
                    infoHtml += `<div>분류: ${pokemon.genus}</div>`;
                }
                if (pokemon.types && pokemon.types.length > 0) {
                    const typeNames = pokemon.types.map(type => type.name).join(', ');
                    infoHtml += `<div>속성: ${typeNames}</div>`;
                }
                if (pokemon.height) {
                    infoHtml += `<div>키: ${pokemon.height / 10}m</div>`;
                }
                if (pokemon.weight) {
                    infoHtml += `<div>무게: ${pokemon.weight / 10}kg</div>`;
                }
                pokeInfo.html(infoHtml);

                // 설명 업데이트
                if (pokemon.flavorText) {
                    pokeDesc.text(pokemon.flavorText);
                } else {
                    pokeDesc.text('설명이 없습니다.');
                }
            })
            .fail(function (error) {
                console.error('Error loading pokemon details:', error);
            });
    }

    function NotReload(event) {
        if ((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116)) {
            event.preventDefault();
        }
    }
    document.onkeydown = NotReload;
});