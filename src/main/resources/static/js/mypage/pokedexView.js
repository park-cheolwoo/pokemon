// pokedexView.js - 포켓몬 도감 상세 페이지 관련 스크립트

// 페이지 로드 시 실행
$(document).ready(function() {
    // URL에서 포켓몬 ID 가져오기
    const pokemonId = getUrlParameter('id');
    
    if (pokemonId) {
        // 포켓몬 상세 정보 로드
        loadPokemonDetails(pokemonId);
        // 진화 정보 로드
        loadEvolutionChain(pokemonId);
    } else {
        console.error('포켓몬 ID가 제공되지 않았습니다.');
        // 기본 메시지 표시
        $('.pokedexView-table').first().html('<tr><td colspan="2">포켓몬 정보를 찾을 수 없습니다.</td></tr>');
    }
});

// URL 파라미터 가져오는 함수
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    const results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

// 포켓몬 상세 정보 로드 함수
function loadPokemonDetails(pokemonId) {
    $.ajax({
        url: `/data/pokemon/${pokemonId}`,
        method: 'GET',
        success: function(pokemon) {
            // 포켓몬 기본 정보 표시
            updatePokemonInfo(pokemon);
        },
        error: function(error) {
            console.error('포켓몬 정보를 불러오는데 실패했습니다:', error);
            $('.pokedexView-table').first().html('<tr><td colspan="2">포켓몬 정보를 불러오는데 실패했습니다.</td></tr>');
        }
    });
}

// 포켓몬 기본 정보 업데이트 함수
function updatePokemonInfo(pokemon) {
    // 포켓몬 이미지 업데이트
    const pokemonImage = $('<img>')
        .attr('src', pokemon.image)
        .attr('alt', pokemon.name)
        .addClass('pokemon-detail-image');
    
    // 이미지 컨테이너에 추가
    $('.poke-img').html(pokemonImage);
    
    // 포켓몬 기본 정보 테이블 업데이트
    const infoTable = $('.pokedexView-table').first();
    
    // 테이블 내용 초기화
    infoTable.empty();
    
    // 이름 행 추가
    infoTable.append(`
        <tr>
            <td><strong>이름</strong></td>
            <td>${pokemon.name}</td>
        </tr>
    `);
    
    // 정보(분류) 행 추가
    infoTable.append(`
        <tr>
            <td><strong>정보</strong></td>
            <td>${pokemon.genus || '정보 없음'}</td>
        </tr>
    `);
    
    // 타입 행 추가
    let typeText = '정보 없음';
    if (pokemon.types && pokemon.types.length > 0) {
        typeText = pokemon.types.map(type => type.name).join(', ');
    }
    infoTable.append(`
        <tr>
            <td><strong>타입</strong></td>
            <td>${typeText}</td>
        </tr>
    `);
    
    // 키 행 추가
    infoTable.append(`
        <tr>
            <td><strong>키</strong></td>
            <td>${pokemon.height ? (pokemon.height / 10) + 'm' : '정보 없음'}</td>
        </tr>
    `);
    
    // 몸무게 행 추가
    infoTable.append(`
        <tr>
            <td><strong>몸무게</strong></td>
            <td>${pokemon.weight ? (pokemon.weight / 10) + 'kg' : '정보 없음'}</td>
        </tr>
    `);
    
    // 성별 행 추가
    infoTable.append(`
        <tr>
            <td><strong>성별</strong></td>
            <td>${pokemon.gender || '정보 없음'}</td>
        </tr>
    `);
    
    // 설명 추가
    if (pokemon.flavorText) {
        infoTable.append(`
            <tr>
                <td><strong>설명</strong></td>
                <td>${pokemon.flavorText}</td>
            </tr>
        `);
    }
}

// 진화 정보 로드 함수
function loadEvolutionChain(pokemonId) {
    $.ajax({
        url: `/data/pokemon/${pokemonId}/evolution`,
        method: 'GET',
        success: function(evolutionData) {
            updateEvolutionInfo(evolutionData);
        },
        error: function(error) {
            console.error('진화 정보를 불러오는데 실패했습니다:', error);
            const evolutionTable = $('.pokedexView-table').eq(1);
            evolutionTable.html('<tr><td colspan="2">진화 정보를 불러오는데 실패했습니다.</td></tr>');
        }
    });
}

// 진화 정보 업데이트 함수
function updateEvolutionInfo(evolutionData) {
    const evolutionTable = $('.pokedexView-table').eq(1);
    
    // 테이블 내용 초기화
    evolutionTable.empty();
    
    // 진화 데이터가 없는 경우
    if (!evolutionData || !evolutionData.chain || evolutionData.chain.length === 0) {
        evolutionTable.append(`
            <tr>
                <td colspan="2">진화 정보가 없습니다.</td>
            </tr>
        `);
        return;
    }
    
    // 진화 체인 표시
    evolutionData.chain.forEach((evolution, index) => {
        evolutionTable.append(`
            <tr>
                <td><strong>진화단계 ${index + 1}</strong></td>
                <td class="evolution-image-container" data-pokemon-id="${evolution.id}">
                    <img src="${evolution.image || `/images/pokemon/${evolution.id}.png`}" 
                         alt="${evolution.name}" 
                         onerror="this.src='/images/close.png'"
                         class="evolution-image">
                    <div class="evolution-name">${evolution.name}</div>
                </td>
            </tr>
        `);
    });
    
    // 진화 이미지 클릭 이벤트 - 해당 포켓몬 상세 페이지로 이동
    $('.evolution-image-container').on('click', function() {
        const pokemonId = $(this).data('pokemon-id');
        window.location.href = `/mypage/pokedexView?id=${pokemonId}`;
    });
}