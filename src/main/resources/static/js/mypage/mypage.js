document.addEventListener('DOMContentLoaded', function () {
    // 상단 탭 요소들 (도감, 아이템, 원정대, 내 포켓몬)
    const tabs = document.querySelectorAll('.banner-item');
    const contentContainer = document.querySelector('#content-container');  // 콘텐츠 컨테이너

    // 기본적으로 "도감" 탭에 active 클래스 추가
    const defaultTab = document.querySelector('.banner-item[data-tab="pokedex"]');
    defaultTab.classList.add('active');
    
    // 기본적으로 "도감" 탭 내용 로드
    loadPage('/mypage/pokedex');  // 절대 경로로 수정

    // 탭 클릭 시 해당하는 페이지 로드
    initializeTabs(tabs);

    // 페이지를 로드하는 함수
    function loadPage(page) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', page, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                contentContainer.innerHTML = xhr.responseText;
                
                // 페이지 로드 후, 아이템 탭 안의 세부 탭 기능을 추가하기 위해
                if (page === '/mypage/item') {
                    initializeItemTabs();  // 아이템 탭 세부 탭 초기화
                }

                // 도감 페이지 로드 후, '자세히'와 '내 포켓몬' 버튼의 클릭 이벤트 추가
                if (page === '/mypage/pokedex') {
                    initializePokedexButtons();
                }

                // pokedexView 페이지 로드 후, 세부 탭 초기화
                if (page === '/mypage/pokedexView') {
                    initializePokedexViewTabs();  // pokedexView 탭 초기화
                }

                // myPokemon 페이지 로드 후, 세부 탭 초기화
                if (page === '/mypage/myPokemon') {
                    initializeMyPokemonTabs();  // myPokemon 탭 초기화
                }
            } else if (xhr.readyState === 4) {
                // 로드 실패 시 에러 메시지
                contentContainer.innerHTML = "페이지를 로드하는데 실패했습니다.";
            }
        };

        xhr.send();
    }

    // 탭을 초기화하는 함수 (아이템, 도감, 원정대 등 모든 탭을 처리)
    function initializeTabs(tabs) {
        tabs.forEach(tab => {
            tab.addEventListener('click', function () {
                // 탭 클릭 시 active 클래스를 해당 탭에만 적용
                tabs.forEach(tab => tab.classList.remove('active'));
                this.classList.add('active');
                
                const tabId = this.getAttribute('data-tab');
                
                // URL을 변경
                updateURL(tabId);  // URL 업데이트 함수 호출
                
                // 현재 탭에 해당하는 jsp 파일을 동적으로 로드
                switch (tabId) {
                    case 'pokedex':
                        loadPage('/mypage/pokedex');  // 절대 경로로 수정
                        break;
                    case 'item':
                        loadPage('/mypage/item');    // 절대 경로로 수정
                        break;
                    case 'expedition':
                        loadPage('/mypage/expedition');  // 절대 경로로 수정
                        break;
                    case 'myPokemon':
                        loadPage('/mypage/myPokemon');  // myPokemon 페이지 로드
                        break;
                    default:
                        loadPage('/mypage/pokedex');  // 기본으로 'pokedex.jsp' 로드
                        break;
                }
            });
        });
    }

    // 아이템 탭 내의 세부 탭 초기화
    function initializeItemTabs() {
        const itemTabs = document.querySelectorAll('.mypage-banner-tab .banner-item');  // 아이템 세부 탭
        const itemContentContainer = document.querySelector('#item-content-container');  // 아이템 탭 내의 콘텐츠 영역

        // 기본적으로 "몬스터볼" 탭에 active 클래스 추가
        const defaultItemTab = document.querySelector('.mypage-banner-tab .banner-item[data-tab="monsterball-item"]');
        defaultItemTab.classList.add('active');

        // 기본적으로 "몬스터볼" 아이템 테이블을 로드
        loadItemContent('monsterball-item');  // 탭 ID로 호출

        // 세부 아이템 탭 클릭 시 해당 내용 로드
        itemTabs.forEach(itemTab => {
            itemTab.addEventListener('click', function () {
                // 클릭된 탭에 active 클래스 추가
                itemTabs.forEach(itemTab => itemTab.classList.remove('active'));
                this.classList.add('active');
                
                const itemTabId = this.getAttribute('data-tab');
                
                // 해당 아이템 테이블을 동적으로 로드
                loadItemContent(itemTabId);  // 탭 ID로 호출
            });
        });
    }

    // 아이템 탭 세부 항목의 테이블을 로드하는 함수
    function loadItemContent(tabId) {
        const contentTabs = document.querySelectorAll('.tab-content'); 
        contentTabs.forEach(contentTab => contentTab.style.display = 'none');  // 모든 테이블 숨기기

        const activeTab = document.querySelector(`#${tabId}`);
        if (activeTab) {
            activeTab.style.display = 'block';
        }
    }

    // pokedexView에서 탭을 초기화하는 함수
    function initializePokedexViewTabs() {
        const viewTabs = document.querySelectorAll('.mypage-banner-tab .banner-item');  // pokedexView 탭
        const contentContainer = document.querySelector('.pokedexView-info-text-container');  // 탭 콘텐츠 영역

        // 기본적으로 "기본 정보" 탭에 active 클래스 추가
        const defaultViewTab = document.querySelector('.mypage-banner-tab .banner-item[data-tab="pokemon-info"]');
        defaultViewTab.classList.add('active');

        // 기본적으로 "기본 정보" 탭 내용 로드
        loadPokedexViewContent('pokemon-info');  // 탭 ID로 호출

        // 세부 탭 클릭 시 해당 내용 로드
        viewTabs.forEach(tab => {
            tab.addEventListener('click', function () {
                // 클릭된 탭에 active 클래스 추가
                viewTabs.forEach(tab => tab.classList.remove('active'));
                this.classList.add('active');
                
                const tabId = this.getAttribute('data-tab');
                
                // 해당 탭의 내용을 동적으로 로드
                loadPokedexViewContent(tabId);  // 탭 ID로 호출
            });
        });
    }

    // pokedexView의 탭 콘텐츠를 로드하는 함수
    function loadPokedexViewContent(tabId) {
        const contentTabs = document.querySelectorAll('.tab-content'); 
        contentTabs.forEach(contentTab => contentTab.style.display = 'none');  // 모든 테이블 숨기기

        const activeTab = document.querySelector(`#${tabId}`);
        if (activeTab) {
            activeTab.style.display = 'block';
        }
    }

    // 도감 내 '자세히'와 '내 포켓몬' 버튼 클릭 시 이벤트 처리
    function initializePokedexButtons() {
        const viewBarContainer = document.querySelector('.view-bar-container');
        
        // '자세히' 버튼 클릭 시
        const detailButton = viewBarContainer.querySelector('div:nth-child(1)');
        detailButton.addEventListener('click', function () {
            // URL을 '/mypage/pokedexView'로 변경
            history.pushState(null, '', '/mypage/pokedexView');
            
            // '자세히' 클릭 시 'pokedexView.jsp' 로드
            loadPage('/mypage/pokedexView');  
        });

        // '내 포켓몬' 버튼 클릭 시
        const myPokemonButton = viewBarContainer.querySelector('div:nth-child(2)');
        myPokemonButton.addEventListener('click', function () {
            // URL을 '/mypage/myPokemon'으로 변경
            history.pushState(null, '', '/mypage/myPokemon');
            
            // '내 포켓몬' 클릭 시 'myPokemon.jsp' 로드
            loadPage('/mypage/myPokemon');  
        });
    }

    // myPokemon 탭을 초기화하는 함수
    function initializeMyPokemonTabs() {
        const myPokemonTabs = document.querySelectorAll('.mypage-banner-tab .banner-item');  // myPokemon 탭
        const contentContainer = document.querySelector('.myPokemon-info-text-container');  // 탭 콘텐츠 영역

        // 기본적으로 "내 포켓몬" 탭에 active 클래스 추가
        const defaultMyPokemonTab = document.querySelector('.mypage-banner-tab .banner-item[data-tab="myPokemon-pokemon-info"]');
        defaultMyPokemonTab.classList.add('active');

        // 기본적으로 "내 포켓몬 목록" 탭 내용 로드
        loadMyPokemonContent('myPokemon-pokemon-info');  // 탭 ID로 호출

        // 세부 탭 클릭 시 해당 내용 로드
        myPokemonTabs.forEach(tab => {
            tab.addEventListener('click', function () {
                // 클릭된 탭에 active 클래스 추가
                myPokemonTabs.forEach(tab => tab.classList.remove('active'));
                this.classList.add('active');
                
                const tabId = this.getAttribute('data-tab');
                
                // 해당 탭의 내용을 동적으로 로드
                loadMyPokemonContent(tabId);  // 탭 ID로 호출
            });
        });
    }

    // myPokemon의 탭 콘텐츠를 로드하는 함수
    function loadMyPokemonContent(tabId) {
        const contentTabs = document.querySelectorAll('.tab-content'); 
        contentTabs.forEach(contentTab => contentTab.style.display = 'none');  // 모든 테이블 숨기기

        const activeTab = document.querySelector(`#${tabId}`);
        if (activeTab) {
            activeTab.style.display = 'block';
        }
    }

    // URL을 변경하는 함수
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

        // history.pushState는 새 URL을 브라우저 주소 표시줄에 추가하고 페이지를 새로고침하지 않습니다
        history.pushState(null, '', url);
    }
	
	function NotReload(){
	    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
	        event.keyCode = 0;
	        event.cancelBubble = true;
	        event.returnValue = false;
	    } 
	}
	document.onkeydown = NotReload;
	
});
