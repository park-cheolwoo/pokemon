// 이미 사용된 randomId를 추적할 Set
const usedIds = new Set();

function getRandomPokemon() {
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

window.addEventListener('DOMContentLoaded', (event) => {
  const pokeimg1 = document.querySelector('.pokeimg1 img');
  const pokeimg2 = document.querySelector('.pokeimg2 img');
  const pokeimg3 = document.querySelector('.pokeimg3 img');
  
  const problem1 = document.querySelector('.problem1 img');
  const problem2 = document.querySelector('.problem2 img');
  const problem3 = document.querySelector('.problem3 img');

  const inproble1 = document.querySelector('.inproble1');
  const inproble2 = document.querySelector('.inproble2');
  const inproble3 = document.querySelector('.inproble3');

  const correct1 = document.querySelector('.Correct1');
  const correct2 = document.querySelector('.Correct2');
  const correct3 = document.querySelector('.Correct3');
  
  const wrong1 = document.querySelector('.wrong1');
  const wrong2 = document.querySelector('.wrong2');
  const wrong3 = document.querySelector('.wrong3');

  let score = 0;  // 맞힌 문제 수
  let goldfinch = 500;  // 보상 (골드)

  // 점수와 보상 갱신 함수
  function updateScoreAndReward() {
    const scoreElement = document.querySelector('.score');
    const goldfinchElement = document.querySelector('.goldfinch');
    scoreElement.textContent = `${score}/3`;
    goldfinchElement.textContent = goldfinch;
  }

  // 첫 번째 포켓몬 이미지 및 이름 삽입
  getRandomPokemon().then(pokemon => {
    if (pokeimg1 && pokemon.imageUrl) {
      pokeimg1.src = pokemon.imageUrl;
    }
    if (problem1 && pokemon.imageUrl) {
      problem1.src = pokemon.imageUrl;
    }
    if (correct1) {
      correct1.textContent = pokemon.name;
    }

    const input1 = document.querySelector('.input1');
    const next1Btn = document.querySelector('.next1');

    // 페이지 로드 시 자동으로 첫 번째 input에 포커스
    input1.focus();

    // 엔터키를 눌렀을 때 next 버튼 클릭 효과
    input1.addEventListener('keydown', (event) => {
      if (event.key === 'Enter') {
        next1Btn.click();
      }
    });

    next1Btn.addEventListener('click', () => {
      const userInput1 = input1.value.trim();

      if (userInput1 === correct1.textContent) {
        inproble1.style.display = 'block';
        inproble1.textContent = userInput1;
        wrong1.style.display = 'none';
        correct1.style.display = 'none';
        score++;  // 맞혔을 경우 점수 증가
        goldfinch += 500;  // 맞혔을 경우 보상 증가
      } else {
        wrong1.textContent = userInput1;
        inproble1.style.display = 'none';
        wrong1.style.display = 'block';
        correct1.style.display = 'block';
      }
      updateScoreAndReward();  // 점수와 보상 갱신

      // 'next' 버튼 클릭 후, 두 번째 input에 포커스
      const input2 = document.querySelector('.input2');
      input2.focus(); // input2에 포커스 이동
    });
  }).catch(error => {
    console.error('Error loading image and name for pokeimg1 and problem1:', error);
  });

  // 두 번째 포켓몬 이미지 및 이름 삽입
  getRandomPokemon().then(pokemon => {
    if (pokeimg2 && pokemon.imageUrl) {
      pokeimg2.src = pokemon.imageUrl;
    }
    if (problem2 && pokemon.imageUrl) {
      problem2.src = pokemon.imageUrl;
    }
    if (correct2) {
      correct2.textContent = pokemon.name;
    }

    const input2 = document.querySelector('.input2');
    const next2Btn = document.querySelector('.next2');

    // 페이지 로드 시 자동으로 두 번째 input에 포커스
    input2.focus();

    // 엔터키를 눌렀을 때 next 버튼 클릭 효과
    input2.addEventListener('keydown', (event) => {
      if (event.key === 'Enter') {
        next2Btn.click();
      }
    });

    next2Btn.addEventListener('click', () => {
      const userInput2 = input2.value.trim();

      if (userInput2 === correct2.textContent) {
        inproble2.style.display = 'block';
        inproble2.textContent = userInput2;
        wrong2.style.display = 'none';
        correct2.style.display = 'none';
        score++;
        goldfinch += 500;
      } else {
        wrong2.textContent = userInput2;
        inproble2.style.display = 'none';
        wrong2.style.display = 'block';
        correct2.style.display = 'block';
      }
      updateScoreAndReward();  // 점수와 보상 갱신

      // 'next' 버튼 클릭 후, 세 번째 input에 포커스
      const input3 = document.querySelector('.input3');
      input3.focus(); // input3에 포커스 이동
    });
  }).catch(error => {
    console.error('Error loading image and name for pokeimg2 and problem2:', error);
  });

  // 세 번째 포켓몬 이미지 및 이름 삽입
  getRandomPokemon().then(pokemon => {
    if (pokeimg3 && pokemon.imageUrl) {
      pokeimg3.src = pokemon.imageUrl;
    }
    if (problem3 && pokemon.imageUrl) {
      problem3.src = pokemon.imageUrl;
    }
    if (correct3) {
      correct3.textContent = pokemon.name;
    }

    const input3 = document.querySelector('.input3');
    const next3Btn = document.querySelector('.next3');

    // 페이지 로드 시 자동으로 세 번째 input에 포커스
    input3.focus();

    // 엔터키를 눌렀을 때 next 버튼 클릭 효과
    input3.addEventListener('keydown', (event) => {
      if (event.key === 'Enter') {
        next3Btn.click();
      }
    });

    next3Btn.addEventListener('click', () => {
      const userInput3 = input3.value.trim();

      if (userInput3 === correct3.textContent) {
        inproble3.style.display = 'block';
        inproble3.textContent = userInput3;
        wrong3.style.display = 'none';
        correct3.style.display = 'none';
        score++;
        goldfinch += 500;
      } else {
        wrong3.textContent = userInput3;
        inproble3.style.display = 'none';
        wrong3.style.display = 'block';
        correct3.style.display = 'block';
      }
      updateScoreAndReward();  // 점수와 보상 갱신

      // 'next' 버튼 클릭 후, 보상 화면으로 이동
      document.querySelector('.schang4').style.display = 'block';
    });
  }).catch(error => {
    console.error('Error loading image and name for pokeimg3 and problem3:', error);
  });
});







function showSchang(schangNumber) {
  const allSchangs = document.querySelectorAll('.schang1, .schang2, .schang3, .schang4');
  allSchangs.forEach(schang => {
    schang.style.display = 'none';
  });

  const selectedSchang = document.querySelector('.schang' + schangNumber);
  if (selectedSchang) {
    selectedSchang.style.display = 'block';
  }
}