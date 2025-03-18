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
            console.log('포켓몬 상세 정보:', pokemon);
            // 포켓몬 기본 정보 표시
            updatePokemonInfo(pokemon);
            
            // 진화 정보 로드 (evolutionId가 있는 경우)
            if (pokemon.evolutionId) {
                loadEvolutionChain(pokemon.evolutionId);
            } else {
                console.error('포켓몬의 진화 ID가 없습니다.');
                const evolutionTable = $('#evolution-tree table');
                evolutionTable.html('<tr><td colspan="2">진화 정보가 없습니다.</td></tr>');
            }
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
function loadEvolutionChain(evolutionId) {
    console.log('진화 정보 로드 시작 - evolutionId:', evolutionId);
    $.ajax({
        url: `/data/pokemon/evolution/${evolutionId}`,
        method: 'GET',
        success: function(evolutionData) {
            console.log('진화 정보 로드 성공:', evolutionData);
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
    console.log('진화 정보 업데이트 시작:', evolutionData);
    const evolutionTable = $('#evolution-tree table');
    console.log('진화 테이블 요소:', evolutionTable.length > 0 ? '찾음' : '찾지 못함');
    
    // 테이블 내용 초기화
    evolutionTable.empty();
    
    // 진화 데이터가 없는 경우
    if (!evolutionData || evolutionData.length === 0) {
        evolutionTable.append(`
            <tr>
                <td colspan="2">진화 정보가 없습니다.</td>
            </tr>
        `);
        return;
    }
    
    // 진화 체인 표시 - 테이블 헤더 추가
    evolutionTable.append(`
        <tr>
            <th colspan="${evolutionData.length * 2 - 1}"></th>
        </tr>
    `);
    
    // 진화 체인을 한 줄에 표시하기 위한 행 추가
    let evolutionRow = $('<tr></tr>');
    evolutionTable.append(evolutionRow);
    
    // 각 진화 단계 포켓몬 표시
    evolutionData.forEach((pokemon, index) => {
        // 진화 화살표 추가 (첫 번째 포켓몬 제외)
        if (index > 0) {
            evolutionRow.append(`
                <td class="evolution-arrow">
                    →
                </td>
            `);
        }
        
        // 포켓몬 이미지와 이름 추가
        evolutionRow.append(`
            <td class="evolution-pokemon">
                <img src="${pokemon.image}" 
                     alt="${pokemon.name}" 
                     onerror="this.src='/images/close.png'"
                     class="evolution-image">
                <div class="evolution-name">${pokemon.name}</div>
                <div class="evolution-pokemon-id">No.${pokemon.id}</div>
            </td>
        `);
    });
}