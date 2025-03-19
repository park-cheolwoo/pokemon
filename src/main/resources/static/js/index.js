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
			url: "/store",
			type: 'GET',
			success: function(response) {
				location.href = "/store";
			},
			error: function(error) {
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
			error: function(error) {
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
	function pending(){
		$.ajax({
			url:"/friend/pending",
			type:'GET',
			success:function(response){
				console.log("응답 데이터:", response);
			},
			error:function(error){
				console.error('실패',error);
			}
		})
	}
	
	
	
	function logout(){
		$.ajax({
			url:"/logout",
			type: 'GET',
			success: function(){
				location.href = "/member/login";
			},
			error:function(error){
				console.error("logout failed",error);
			}
		});
	};
	

	// 콘텐츠 및 버튼 토글 관련 함수
	function toggle(number) {
		var content1 = $("#content1");
		var content2 = $("#content2");
		var content3 = $("#content3");

		content1.addClass("hiddenbox");
		content2.addClass("hiddenbox");
		content3.addClass("hiddenbox");

		if (number == 1) {
			content1.removeClass("hiddenbox");
		} else if (number == 2) {
			content2.removeClass("hiddenbox");
		} else if (number == 3) {
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

		if (number == 1) {
			btn1.removeClass("hidden");
		} else if (number == 2) {
			btn2.removeClass("hidden");
		} else if (number == 3) {
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
			error: function(error) {
				console.error('Error:', error);
			}
		});
	}
	$(document).ready(function () {
	    function formatCurrency(amount) {
	        return parseInt(amount).toLocaleString('ko-KR') + ' 원';
	    }

	    function updateResourceDisplay() {
	        const gameMoneyElement = $('.resource-group:nth-child(1) .resource-info'); 
	        const realMoneyElement = $('.resource-group:nth-child(2) .resource-info'); 

	        const gameMoney = gameMoneyElement.text().replace(/[^0-9]/g, ''); 
	        const realMoney = realMoneyElement.text().replace(/[^0-9]/g, ''); 

	        gameMoneyElement.text(formatCurrency(gameMoney));
	        realMoneyElement.text(formatCurrency(realMoney));
	    }
	    updateResourceDisplay();
	});

	//메인화면 소유 포켓몬 불러오기
	$(document).ready(function () {
	    function loadMinePokemons() {
	        $.ajax({
	            url: '/player/pokemon/mine',
	            type: 'GET',
	            dataType: 'json',
	            success: function (response) {
	                if (response.status === 'success') {
	                    renderPokemonImages(response.data); 
	                } else {
	                    console.error('데이터를 불러오지 못했습니다:', response.message);
	                }
	            },
	            error: function (error) {
	                console.error('API 요청 실패:', error);
	            }
	        });
	    }

	    function renderPokemonImages(images) {
	        const container = $('#pokemon-images-container');
	        container.empty();
	        images.forEach((image) => {
	            const imageHTML = `<img src="${image}" alt="포켓몬 이미지" class="pokemon-image" />`;
	            container.append(imageHTML);
	        });
	    }

	    loadMinePokemons();
	});
	function updateSessionDisplay() {
	    $.ajax({
	        url: "/member/sessionData",
	        type: "GET",
	        dataType: "json",
	        success: function(data) {
	            $(".player-info").text("Lv." + data.session_lv + " " + data.session_nickname);
	            $(".resource-group:first .resource-info").text(Number(data.session_gameMoney).toLocaleString() + " 원");
	            $(".resource-group:last .resource-info").text(Number(data.session_realMoney).toLocaleString() + " 원");
	        },
	        error: function(err) {
	            console.error("세션 데이터 업데이트 실패:", err);
	        }
	    });
	}

	$(document).ready(function() {
	    // 페이지 로드 시 자동으로 최신 세션 데이터를 업데이트합니다.
	    updateSessionDisplay();
	});
	
	$("#mypageBtn").click(function() { mypage(); });
	$("#storeBtn").click(function() { store(); });
	$("#playBtn").click(function() { play(); });
	$("#questBtn").click(function() { quest(); });
	$("#socialBtn").click(function() { social(); pending(); });
	$("#closeBtn").click(function() { closebtn(); });
	$("#logoutBtn").click(function() { logout(); });
	$("#toggleBtn1").click(function() { toggle(1); togglebtn(1); });
	$("#toggleBtn2").click(function() { toggle(2); togglebtn(2); });
	$("#toggleBtn3").click(function() { toggle(3); togglebtn(3); });
});