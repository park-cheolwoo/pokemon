function initializeItemTabs() {
    const itemTabs = $('.mypage-banner-tab .banner-item');

    const defaultItemTab = $('.mypage-banner-tab .banner-item[data-tab="monsterball-item"]');
    defaultItemTab.addClass('active');

    // 각 탭에 해당하는 카테고리 ID 목록
    const categoryMapping = {
        'monsterball-item': [33, 34],
        'battle-item': [1, 11, 27, 28, 29, 30],
        'training-item': [10, 26, 37]
    };

    // 기본 아이템 로드
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

// 아이템을 테이블에 추가하는 함수
function addItemToTable(tableBody, item) {
    const row = $('<tr></tr>');

    // 이미지 셀 추가
    const imgCell = $('<td class="item-img"></td>');
    const img = $('<img>')
        .attr('src', item.image)
        .attr('alt', item.name)
        .attr('width', '50')
        .attr('height', '50')
        .attr('onerror', "this.src='/images/close.png'");
    imgCell.append(img);

    // 이름 셀 추가
    const nameCell = $('<td class="item-name"></td>').text(item.name);

    // 수량 셀 추가
    const countCell = $('<td class="item-quantity"></td>').text(`${item.count}개`);

    // 셀들을 행에 추가
    row.append(imgCell, nameCell, countCell);

    // 아이템 클릭 이벤트 추가
    row.on('click', function () {
        showItemDetails(item);
    });

    // 행을 테이블에 추가
    tableBody.append(row);
}

function loadItemsByCategory(categoryIds, tableBodyId) {
    const tableBody = $('#' + tableBodyId);
    if (!tableBody.length) return;

    // 사용자 아이템을 가져옵니다
    $.ajax({
        url: '/player/items/my-items',
        type: 'GET',
        success: function (userItems) {
            console.log("사용자 아이템 데이터:", userItems);
            console.log("현재 카테고리 IDs:", categoryIds);

            if (userItems && userItems.length > 0) {
                // 사용자 아이템이 있으면 테이블을 비우고 사용자 아이템을 표시
                tableBody.empty();

                // 각 아이템에 대해 상세 정보를 가져옵니다
                const promises = userItems.map(userItem => {
                    return new Promise((resolve, reject) => {
                        // 아이템 ID로 아이템 상세 정보를 가져옵니다
                        $.get(`/items/${userItem.itemId}`)
                            .done(function (itemDetail) {
                                console.log(`아이템 ${userItem.itemId} 상세 정보:`, itemDetail);
                                // 사용자 아이템 정보와 아이템 상세 정보를 합칩니다
                                resolve({
                                    ...userItem,
                                    name: itemDetail.name || `아이템 ${userItem.itemId}`,
                                    image: itemDetail.image || `/images/items/item_${userItem.itemId}.png`,
                                    description: itemDetail.description,
                                    flavorText: itemDetail.flavorText,
                                    categoryId: itemDetail.categoryId
                                });
                            })
                            .fail(function (error) {
                                console.error(`아이템 ${userItem.itemId} 정보 가져오기 실패:`, error);
                                // 아이템 상세 정보를 가져오지 못한 경우
                                resolve({
                                    ...userItem,
                                    name: `아이템 ${userItem.itemId}`,
                                    image: `/images/items/item_${userItem.itemId}.png`,
                                    categoryId: -1 // 카테고리 정보를 가져오지 못한 경우
                                });
                            });
                    });
                });

                // 모든 아이템 정보를 가져온 후 화면에 표시합니다
                Promise.all(promises).then(itemsWithDetails => {
                    // 현재 카테고리에 해당하는 아이템만 필터링
                    const filteredItems = itemsWithDetails.filter(item =>
                        categoryIds.includes(item.categoryId)
                    );

                    console.log("카테고리 필터링 후 아이템:", filteredItems);

                    if (filteredItems.length > 0) {
                        filteredItems.forEach(item => {
                            // 아이템을 테이블에 추가
                            addItemToTable(tableBody, item);
                        });
                    } else {
                        // 해당 카테고리에 아이템이 없는 경우
                        tableBody.html('<tr><td colspan="3">보유한 아이템이 없습니다.</td></tr>');
                    }
                }).catch(error => {
                    console.error("아이템 상세 정보를 가져오는 중 오류 발생:", error);
                    tableBody.html('<tr><td colspan="3">아이템 정보를 불러오는 중 오류가 발생했습니다.</td></tr>');
                });
            } else {
                // 사용자 아이템이 없으면 '보유한 아이템이 없습니다' 메시지 표시
                tableBody.html('<tr><td colspan="3">보유한 아이템이 없습니다.</td></tr>');
            }
        },
        error: function (error) {
            console.error("Error fetching user items:", error);
            tableBody.html('<tr><td colspan="3">아이템 정보를 불러오는 중 오류가 발생했습니다.</td></tr>');
        }
    });
}

// 아이템 상세 정보 표시 함수
function showItemDetails(item) {
    // 이미지 업데이트
    $('.mypage-item-img img')
        .attr('src', item.image || '/images/close.png')
        .attr('alt', item.name)
        .attr('onerror', "this.src='/images/close.png'");

    // 이름 업데이트
    $('.mypage-item-name').text(item.name);

    // 설명 업데이트
    $('.mypage-item-info').text(item.flavorText || '설명이 없습니다.');
}