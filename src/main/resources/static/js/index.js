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
		//친구 요청 데이터
		fetchpending();
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
	
	
	// 친구 요청 데이터를 가져오는 함수
	function fetchpending() {
	    console.log("AJAX 요청 시작"); // 테스트용 로그
	    $.ajax({
	        url: "/friend/pending",
	        type: 'GET',
	        success: function(pendingRequests) {
	            console.log("Pending Requests:", pendingRequests);
	            renderpending(pendingRequests);
	        },
	        error: function(xhr, status, error) {
	            console.error("AJAX 요청 실패:", error);
	        }
	    });
	}

	// 요청 데이터를 요청 창에 렌더링하는 함수
	function renderpending(pendingRequests) {
		const requestList = $("#friend-requests");
		requestList.empty();

		if (pendingRequests && pendingRequests.length > 0) {
			pendingRequests.forEach(request => {
				const level = request.lv || "알 수 없음"; // lv 필드
				const nickname = request.nickname || "알 수 없음"; // nickname 필드

				const listItem = `
	                <li class="request-item">
	                    <div class="request-details">
	                        <p>From: Lv.${level} ${nickname}</p>
	                    </div>
	                    <div class="request-actions">
	                        <button class="accept-request" data-id="${request.id}">수락</button>
	                        <button class="decline-request" data-id="${request.id}">거절</button>
	                    </div>
	                </li>`;
				requestList.append(listItem);
				console.log("Rendered items:", requestList.html());
			});
		} else {
			requestList.append('<li>받은 친구 요청이 없습니다.</li>');
		}
	}

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
			error: function(xhr, status, error) {
				console.error('Error:', error);
			}
		});
	}



	$("#mypageBtn").click(function() { mypage(); });
	$("#storeBtn").click(function() { store(); });
	$("#playBtn").click(function() { play(); });
	$("#questBtn").click(function() { quest(); });
	$("#socialBtn").click(function() { social(); });
	$("#closeBtn").click(function() { closebtn(); });
	$("#logoutBtn").click(function() { logout(); });
	$("#toggleBtn1").click(function() { toggle(1); togglebtn(1); });
	$("#toggleBtn2").click(function() { toggle(2); togglebtn(2); });
	$("#toggleBtn3").click(function() { toggle(3); togglebtn(3); });
});