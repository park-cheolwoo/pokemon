document.addEventListener('DOMContentLoaded', function () {
    // 탭 요소들
    const tabs = document.querySelectorAll('.banner-item');
    const contentContainer = document.querySelector('#content-container');  // 콘텐츠 컨테이너

    // 기본적으로 "도감" 탭에 active 클래스 추가
    const defaultTab = document.querySelector('.banner-item[data-tab="pokedex"]');
    defaultTab.classList.add('active');
    
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
                    loadPage('pokedex.jsp');
                    break;
                case 'item':
                    loadPage('item.jsp');
                    break;
                case 'expedition':
                    loadPage('expedition.jsp');
                    break;
                default:
                    loadPage('pokedex.jsp');
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
            }
        };

        xhr.send();
    }

    // 페이지 로드 시 기본 'pokedex.jsp' 로드
    loadPage('pokedex.jsp');
});
