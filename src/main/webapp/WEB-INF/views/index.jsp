<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓몬</title>
<link type="text/css" rel="stylesheet" href="/css/memberstyles.css" >
<link type="text/css" rel="stylesheet" href="/css/styles.css" >
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <div class="main-container">
        <h2 class="player">Lv.${session_lv} ${session_id }</h2>
        <img src="/images/wood_button1.png" class="wood1">
        <img src="/images/gold.png" class="gold">
        <img src="/images/wood_button3.png" class="wood2">
        <h3 class="money1">${session_gameMoney} 원</h3>
        <img src="/images/diamond.png" class="diamond">
        <img src="/images/wood_button3.png" class="wood3">
        <h3 class="money2">${session_realMoney} 원</h3>
        <img src="/images/option.png" class="option">
        
        <img id="pokeimg1" class="poketmon1" alt="Pokemon Image 1">
        <img id="pokeimg2" class="poketmon2" alt="Pokemon Image 2">
        <img id="pokeimg3" class="poketmon3" alt="Pokemon Image 3">
        <img id="pokeimg4" class="poketmon4" alt="Pokemon Image 4">
        <img id="pokeimg5" class="poketmon5" alt="Pokemon Image 5">
        <img id="pokeimg6" src="/images/poketmon6.png" class="poketmon6" alt="Pokemon Image 6">
        
        <img src="/images/wood_button2.png" class="wood4">
        <button id="mypageBtn" class="indexbtn1">마이페이지</button>
        <img src="/images/wood_button2.png" class="wood5">
        <button id="storeBtn" class="indexbtn2">상점</button>
        <img src="/images/wood_button2.png" class="wood6">
        <button id="playBtn" class="indexbtn3">PLAY</button>
        <img src="/images/wood_button2.png" class="wood7">
        <button id="questBtn" class="indexbtn4">퀘스트</button>
        <img src="/images/wood_button2.png" class="wood8">
        <button id="socialBtn" class="indexbtn5">소셜</button>
        
        <a href="member/login">로그인</a>
    </div>
        
    <script src="/js/index.js"></script>
    <script>
        const usedIds = new Set(); // 중복된 ID를 추적하기 위한 Set

        function getPokemon() {
          let randomId;

          // 중복되지 않는 randomId를 찾을 때까지 반복
          do {
            randomId = Math.floor(Math.random() * 1000) + 1; // 1부터 1000까지의 랜덤 ID
          } while (usedIds.has(randomId));  // 이미 사용된 ID이면 다시 생성

          // 새로운 ID를 사용된 ID 목록에 추가
          usedIds.add(randomId);

          return fetch(`https://pokeapi.co/api/v2/pokemon/${randomId}`)
            .then(response => response.json())
            .then(data => {
              return fetch(`https://pokeapi.co/api/v2/pokemon-species/${randomId}`)
                .then(speciesResponse => speciesResponse.json())
                .then(speciesData => {
                  const koreanName = speciesData.names.find(nameObj => nameObj.language.name === 'ko');
                  return {
                    imageUrl: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${randomId}.png`,
                    name: koreanName ? koreanName.name : 'No Korean Name'
                  };
                });
            })
            .catch(error => {
              console.error('Error fetching Pokemon data:', error);
              return {
                imageUrl: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png', // 오류 발생 시 기본 이미지 URL
                name: 'Error'
              };
            });
        }

        // 포켓몬 이미지와 이름을 설정하는 함수
        function setPokemonImageAndName(imageId, nameId) {
          getPokemon().then(pokemon => {
            const pokeimg = document.getElementById(imageId);
            const pokemonName = document.getElementById(nameId);

            if (pokeimg && pokemon.imageUrl) {
              pokeimg.src = pokemon.imageUrl;
            }
            if (pokemonName) {
              pokemonName.textContent = pokemon.name;
            }
          });
        }

        // 페이지 로드 시 초기화
        document.addEventListener('DOMContentLoaded', () => {
          setPokemonImageAndName('pokeimg1', 'pokeName1');
          setPokemonImageAndName('pokeimg2', 'pokeName2');
          setPokemonImageAndName('pokeimg3', 'pokeName3');
          setPokemonImageAndName('pokeimg4', 'pokeName4');
          setPokemonImageAndName('pokeimg5', 'pokeName5');
          setPokemonImageAndName('pokeimg6', 'pokeName6');
        });
    </script>
</body>
</html>
