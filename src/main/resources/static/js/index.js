// scripts.js
$(function() {
	// 페이지 이동 관련 함수들
	function mypage() {
		$.ajax({
			url: "/mypage/mypage",
			type: 'GET',
			success: function(response) {
				location.href = "/mypage/mypage";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	function store() {
		$.ajax({
			url: "/store/basicStore",
			type: 'GET',
			success: function(response) {
				location.href = "/store/basicStore";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	function play() {
		$.ajax({
			url: "/play/plist",
			type: 'GET',
			success: function(response) {
				location.href = "/play/plist";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	function quest() {
		$.ajax({
			url: "/play/quest",
			type: 'GET',
			success: function(response) {
				location.href = "/play/quest";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	function social() {
		$.ajax({
			url: "/friend/flist",
			type: 'GET',
			success: function(response) {
				location.href = "/friend/flist";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	// 콘텐츠 및 버튼 토글 관련 함수
	function toggle(number) {
		var content1 = $("#content1");
		var content2 = $("#content2");
		var content3 = $("#content3");

		content1.addClass("hiddenbox");
		content2.addClass("hiddenbox");
		content3.addClass("hiddenbox");

		if (number === 1) {
			content1.removeClass("hiddenbox");
		} else if (number === 2) {
			content2.removeClass("hiddenbox");
		} else if (number === 3) {
			content3.removeClass("hiddenbox");
		}
	}

	function togglebtn(number) {
		var btn1 = $("#btn1");
		var btn2 = $("#btn2");
		var btn3 = $("#btn3");

		btn1.addClass("hidden");
		btn2.addClass("hidden");
		btn3.addClass("hidden");

		if (number === 1) {
			btn1.removeClass("hidden");
		} else if (number === 2) {
			btn2.removeClass("hidden");
		} else if (number === 3) {
			btn3.removeClass("hidden");
		}
	}

	function closebtn() {
		$.ajax({
			url: "/",
			type: 'GET',
			success: function(response) {
				location.href = "/";
			},
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}

	function fetchRandomPokemons() {
		$.ajax({
			url: 'https://pokeapi.co/api/v2/pokemon?limit=1000',
			type: 'GET',
			success: function(response) {
				const pokemonList = response.results;
				const randomPokemons = [];
				while (randomPokemons.length < 6) {
					const randomIndex = Math.floor(Math.random() * pokemonList.length);
					const randomPokemon = pokemonList[randomIndex];
					if (!randomPokemons.includes(randomPokemon)) {
						randomPokemons.push(randomPokemon);
					}
				}
				randomPokemons.forEach(pokemon => {
					fetchPokemonData(pokemon.url); // 랜덤으로 선택된 포켓몬의 데이터 가져오기
				});
			},
			error: function(error) {
				console.error('Error fetching Pokemon list:', error);
			}
		});
	}

	// 선택된 포켓몬의 데이터를 가져오는 함수
	function fetchPokemonData(url) {
		$.ajax({
			url: url, 
			type: 'GET',
			success: function(response) {
				displayPokemonImage(response);
			},
			error: function(error) {
				console.error('Error fetching Pokemon data:', error);
			}
		});
	}

	// 포켓몬 이미지를 화면에 표시하는 함수
	function displayPokemonImage(pokemon) {
		const container = $('#pokemon-container');

		const card = $('<div class="pokemon-card"></div>');
		card.append(`<img src="${pokemon.sprites.other['home'].front_default}" alt="${pokemon.name} Home Image">`);
		container.append(card);
	}

	fetchRandomPokemons();
	
	
	
	$("#mypageBtn").click(function() { mypage(); });
	$("#storeBtn").click(function() { store(); });
	$("#playBtn").click(function() { play(); });
	$("#questBtn").click(function() { quest(); });
	$("#socialBtn").click(function() { social(); });
	$("#closeBtn").click(function() { closebtn(); });
	$("#toggleBtn1").click(function() { toggle(1); togglebtn(1); });
	$("#toggleBtn2").click(function() { toggle(2); togglebtn(2); });
	$("#toggleBtn3").click(function() { toggle(3); togglebtn(3); });
});
