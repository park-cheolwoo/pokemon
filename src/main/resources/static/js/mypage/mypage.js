document.addEventListener('DOMContentLoaded', function () {
    // 상단 탭 요소들
    const tabs = document.querySelectorAll('.banner-item');  // 상단 탭 (도감, 아이템, 원정대)
    const contentContainer = document.querySelector('#content-container');  // 콘텐츠 컨테이너

    // 기본적으로 "도감" 탭에 active 클래스 추가
    const defaultTab = document.querySelector('.banner-item[data-tab="pokedex"]');
    defaultTab.classList.add('active');
    
    // 기본적으로 "도감" 탭 내용 로드
    loadPage('/mypage/pokedex');  // 절대 경로로 수정

    // 탭 클릭 시 해당하는 페이지 로드
    tabs.forEach(tab => {
        tab.addEventListener('click', function () {
            // 탭 클릭 시 active 클래스를 해당 탭에만 적용
            tabs.forEach(tab => tab.classList.remove('active'));
            this.classList.add('active');
            
            const tabId = this.getAttribute('data-tab');
            
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
                default:
                    loadPage('/mypage/pokedex');  // 기본으로 'pokedex.jsp' 로드
                    break;
            }
        });
    });

    // 페이지를 로드하는 함수
    function loadPage(page) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', page, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                contentContainer.innerHTML = xhr.responseText;
                
                // 페이지 로드 후, 아이템 탭 안의 세부 탭 기능을 추가하기 위해
                if (page === '/mypage/item') {
                    // 아이템 탭의 세부 탭 기능을 초기화
                    initializeItemTabs();  // 아이템 탭 세부 탭 초기화
                }
            } else if (xhr.readyState === 4) {
                // 로드 실패 시 에러 메시지
                contentContainer.innerHTML = "페이지를 로드하는데 실패했습니다.";
            }
        };

        xhr.send();
    }

	// 아이템 탭 내의 세부 탭 초기화
    function initializeItemTabs() {
        const itemTabs = document.querySelectorAll('.mypage-banner-tab .banner-item');  // 아이템 세부 탭
        const itemContentContainer = document.querySelector('#item-content-container');  // 아이템 탭 내의 콘텐츠 영역

        // 기본적으로 "몬스터볼" 탭에 active 클래스 추가
        const defaultItemTab = document.querySelector('.mypage-banner-tab .banner-item[data-tab="monsterball-item"]');
        defaultItemTab.classList.add('active');

        // 기본적으로 "몬스터볼" 아이템 테이블을 로드
        loadItemContent('/mypage/item/monsterball');  // 절대 경로로 수정

        // 세부 아이템 탭 클릭 시 해당 내용 로드
        itemTabs.forEach(itemTab => {
            itemTab.addEventListener('click', function () {
                // 클릭된 탭에 active 클래스 추가
                itemTabs.forEach(itemTab => itemTab.classList.remove('active'));
                this.classList.add('active');
                
                const itemTabId = this.getAttribute('data-tab');
                
                // 해당 아이템 테이블을 동적으로 로드
                switch (itemTabId) {
                    case 'monsterball-item':
                        loadItemContent('/mypage/item/monsterball');  // 절대 경로로 수정
                        break;
                    case 'revive-item':
                        loadItemContent('/mypage/item/revive');  // 절대 경로로 수정
                        break;
                    case 'battle-item':
                        loadItemContent('/mypage/item/battle');  // 절대 경로로 수정
                        break;
                    default:
                        loadItemContent('/mypage/item/monsterball');  // 기본은 "몬스터볼"
                        break;
                }
            });
        });
    }

    // 아이템 탭 세부 항목의 테이블을 로드하는 함수
    function loadItemContent(page) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', page, true);

        xhr.onreadystatechange = function () {
            const itemContentContainer = document.querySelector('#item-content-container');
            if (xhr.readyState === 4 && xhr.status === 200) {
                itemContentContainer.innerHTML = xhr.responseText;

                // 아이템 세부 테이블들을 가져와서 display를 처리
                const tables = itemContentContainer.querySelectorAll('.tab-content'); 
                tables.forEach(table => table.style.display = 'none');  // 모든 테이블 숨기기

                // 로드된 테이블만 보이도록 설정
                const activeTable = itemContentContainer.querySelector('.tab-content.active');
                if (activeTable) {
                    activeTable.style.display = 'block';
                }
            } else if (xhr.readyState === 4) {
                itemContentContainer.innerHTML = "아이템 내용을 로드하는데 실패했습니다.";
            }
        };

        xhr.send();
    }
});
