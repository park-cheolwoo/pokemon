<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mypage</title>
    <link type="text/css" rel="stylesheet" href="/css/styles.css"> <!-- 공통 스타일 -->
    <link type="text/css" rel="stylesheet" href="/css/pokedex.css"> <!-- 도감 관련 스타일 -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- 데이터 API 스크립트 추가 -->
    <script>
        // 데이터 API 미리 로드
        $(document).ready(function() {
            console.log('데이터 API 초기화');
            // 데이터 캐시 초기화
            window.dataCache = {
                pokemon: {},
                items: {}
            };
            
            // 포켓몬 데이터 미리 로드
            $.ajax({
                url: '/data/pokemon',
                method: 'GET',
                dataType: 'json',
                success: function(pokemonList) {
                    console.log('포켓몬 데이터 미리 로드 성공:', pokemonList.length);
                    // 캐시에 저장
                    pokemonList.forEach(pokemon => {
                        window.dataCache.pokemon[pokemon.id] = pokemon;
                    });
                },
                error: function(error) {
                    console.error('포켓몬 데이터 미리 로드 실패:', error);
                }
            });
            
            // 아이템 카테고리 미리 로드
            const itemCategories = [
                [33, 34], // 몬스터볼
                [1, 11, 27, 28, 29, 30], // 배틀 아이템
                [10, 26, 37] // 훈련 아이템
            ];
            
            itemCategories.forEach(categoryIds => {
                categoryIds.forEach(categoryId => {
                    $.ajax({
                        url: `/data/item/${categoryId}`,
                        method: 'GET',
                        dataType: 'json',
                        success: function(items) {
                            console.log(`아이템 카테고리 ${categoryId} 미리 로드 성공:`, items);
                            // 캐시에 저장
                            if (Array.isArray(items)) {
                                window.dataCache.items[categoryId] = items;
                            } else if (items && typeof items === 'object') {
                                const itemsArray = items.items || Object.values(items);
                                if (Array.isArray(itemsArray)) {
                                    window.dataCache.items[categoryId] = itemsArray;
                                }
                            }
                        },
                        error: function(error) {
                            console.error(`아이템 카테고리 ${categoryId} 미리 로드 실패:`, error);
                        }
                    });
                });
            });
        });
    </script>
</head>
<body>
    <div class="mypage-background-container">
        <div class="mypage-banner-container">
            <div class="mypage-banner">
                <div class="banner-item" data-tab="pokedex">
                    <span>도감</span>
                </div>
                <div class="banner-item" data-tab="item">
                    <span>아이템</span>
                </div>
                <div class="banner-item" data-tab="expedition">
                    <span>원정대</span>
                </div>
            </div>
            <a href="/"><div class="closeBtn"></div></a>
        </div>

        <div id="content-container" class="mypage-content-container">
            <!-- 콘텐츠는 JavaScript로 동적으로 로드됩니다 -->
        </div>
    </div>

    <!-- JavaScript 코드 -->
    <script src="/js/mypage/mypage.js"></script>
    <!-- 각 탭에 필요한 JavaScript 파일들 -->
    <script src="/js/mypage/pokedex.js"></script>
    <script src="/js/mypage/item.js"></script>
    <script src="/js/mypage/expedition.js"></script>
</body>
</html>
