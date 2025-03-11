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
                    initializePokedexButtons();
                    initializePokedexList(); 
                }
                if (page === '/mypage/pokedexView') {
                    initializePokedexViewTabs();
                }
                if (page === '/mypage/myPokemon') {
                    initializeMyPokemonTabs();
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

    function initializeItemTabs() {
        const itemTabs = $('.mypage-banner-tab .banner-item');

        const defaultItemTab = $('.mypage-banner-tab .banner-item[data-tab="monsterball-item"]');
        defaultItemTab.addClass('active');

        const categoryMapping = {
            'monsterball-item': [33, 34],
            'battle-item': [1, 11, 27, 28, 29, 30],
            'training-item': [10, 26, 37]
        };

        loadItemContent('monsterball-item');
        loadItemsByCategory(categoryMapping['monsterball-item'], 'monsterball-item-list-body');

        itemTabs.on('click', function () {
            itemTabs.removeClass('active');
            $(this).addClass('active');

            const itemTabId = $(this).data('tab');
            loadItemContent(itemTabId);

            const categoryId = categoryMapping[itemTabId];
            if (categoryId) {
                loadItemsByCategory(categoryId, itemTabId + '-list-body');
            }
        });
    }

    function loadItemContent(tabId) {
        $('.tab-content').hide();
        $('#' + tabId).show();
    }

    function loadItemsByCategory(categoryIds, tableBodyId) {
        const tableBody = $('#' + tableBodyId);
        if (!tableBody.length) return;
    
        tableBody.html('<tr><td colspan="3">로딩 중...</td></tr>');
    
        categoryIds.forEach(categoryId => {
            $.get(`/api/items/category/${categoryId}`)
                .done(function (items) {
                    if (items && items.length > 0) {
                        // 첫 번째 아이템이 로드되면 테이블 내용을 비웁니다
                        if (tableBody.find('tr').length === 1 && tableBody.find('tr td').text() === '로딩 중...') {
                            tableBody.empty();
                        }
                        
                        items.forEach(item => {
                            const row = $('<tr></tr>');
                            row.data('item-id', item.id); // 아이템 ID 저장
                            
                            // 이미지 셀 추가
                            const imgCell = $('<td class="item-img"></td>');
                            // 이미지 경로는 item.image를 사용하고, 없을 경우 기본 이미지 사용
                            const imgSrc = item.image || `/images/items/default.png`;
                            const img = $('<img>').attr('src', imgSrc)
                                                .attr('alt', item.name)
                                                .attr('width', '50')
                                                .attr('height', '50')
                                                .attr('onerror', "this.src='/images/items/default.png'");
                            imgCell.append(img);
                            
                            // 이름 셀 추가
                            const nameCell = $('<td class="item-name"></td>').text(item.name);
                            
                            // 수량 셀 추가
                            const quantityCell = $('<td class="item-quantity"></td>').text(item.quantity || '1개');
                            
                            // 셀들을 행에 추가
                            row.append(imgCell, nameCell, quantityCell);
                            
                            // 아이템 클릭 이벤트 추가
                            row.on('click', function() {
                                showItemDetails(item);
                            });
                            
                            // 행을 테이블에 추가
                            tableBody.append(row);
                        });
                    } else {
                        if (tableBody.find('tr').length === 1 && tableBody.find('tr td').text() === '로딩 중...') {
                            tableBody.html('<tr><td colspan="3">아이템이 없습니다.</td></tr>');
                        }
                    }
                })
                .fail(function (error) {
                    console.error('Error:', error);
                    tableBody.html(`<tr><td colspan="3">오류가 발생했습니다: ${error.message}</td></tr>`);
                });
        });
    }

    // 아이템 상세 정보 표시 함수
    function showItemDetails(item) {
        // 이미지 업데이트
        $('.mypage-item-img img')
            .attr('src', item.image || '/images/items/default.png')
            .attr('alt', item.name)
            .attr('onerror', "this.src='/images/items/default.png'");

        // 이름 업데이트
        $('.mypage-item-name').text(item.name);

        // 설명 업데이트
        $('.mypage-item-info').text(item.description || '설명이 없습니다.');
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

    function initializePokedexButtons() {
        const viewBarContainer = $('.view-bar-container');

        // '자세히' 버튼 클릭 시
        const detailButton = viewBarContainer.find('div:nth-child(1)');
        detailButton.on('click', function () {
            history.pushState(null, '', '/mypage/pokedexView');
            loadPage('/mypage/pokedexView');
        });

        // '내 포켓몬' 버튼 클릭 시
        const myPokemonButton = viewBarContainer.find('div:nth-child(2)');
        myPokemonButton.on('click', function () {
            history.pushState(null, '', '/mypage/myPokemon');
            loadPage('/mypage/myPokemon');
        });
    }

    function NotReload(event) {
        if ((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116)) {
            event.preventDefault();
        }
    }

    function loadPokedexViewContent(tabId) {
        const contentTabs = $('.tab-content'); // 모든 탭 콘텐츠 선택
        contentTabs.hide();  // 모든 탭 콘텐츠 숨기기

        const activeTab = $('#' + tabId); // 활성화할 탭 선택
        if (activeTab.length) {
            activeTab.show();  // 해당 탭의 콘텐츠 표시
        }
    }

    function initializePokedexList() {
        const imageContainer = $('.image-container');
        if (!imageContainer.length) return;

        // 로딩 메시지 표시
        imageContainer.html('<div class="loading">포켓몬 데이터를 불러오는 중...</div>');

        // 포켓몬 데이터 가져오기 - 페이지 크기를 1000으로 설정하여 모든 포켓몬을 가져옴
        $.get('/data/pokemon?page=0&size=1000')
            .done(function (pokemonList) {
                if (pokemonList && pokemonList.length > 0) {
                    imageContainer.empty(); // 기존 내용 비우기

                    // ID 순으로 정렬
                    pokemonList.sort((a, b) => a.id - b.id);

                    // 각 포켓몬에 대한 이미지 요소 생성
                    pokemonList.forEach(pokemon => {
                        const pokemonDiv = $('<div class="pokemon-item"></div>');
                        const img = $('<img>')
                            .attr('src', pokemon.image || `/images/pokemon/${pokemon.id}.png`)
                            .attr('alt', pokemon.name)
                            .attr('data-id', pokemon.id)
                            .attr('onerror', "this.src='/images/pokemon/default.png'");
                        
                        // 이미지 클릭 시 포켓몬 상세 정보 표시
                        img.on('click', function() {
                            showPokemonDetails(pokemon.id);
                        });

                        pokemonDiv.append(img);
                        imageContainer.append(pokemonDiv);
                    });
                } else {
                    imageContainer.html('<div class="no-data">포켓몬 데이터가 없습니다.</div>');
                }
            })
            .fail(function (error) {
                console.error('Error loading pokemon list:', error);
                imageContainer.html('<div class="error">포켓몬 데이터를 불러오는데 실패했습니다.</div>');
            });
    }

    function showPokemonDetails(pokemonId) {
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
                      .attr('onerror', "this.src='/images/pokemon/default.png'");

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
                if (pokemon.description) {
                    pokeDesc.text(pokemon.description);
                } else {
                    pokeDesc.text('설명이 없습니다.');
                }
            })
            .fail(function (error) {
                console.error('Error loading pokemon details:', error);
            });
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

    document.onkeydown = NotReload;
});