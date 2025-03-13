$(function() {

	const appState = {
		isValidated: false, // 중복 확인 상태
	};

	// 로그인 버튼 함수
	function loginBtn() {
		const id = $("#id").val();
		const password = $("#password").val();

		if (!id) {
			alert("아이디를 입력하세요.");
			return;
		}
		if (!password) {
			alert("비밀번호를 입력하세요.");
			return;
		}

		$.ajax({
			url: "/member/login",
			type: 'POST',
			data: {
				id: id,
				password: password
			},
			success: function(response) {
				if (response.loginChk == "0") {
					console.log("Incorrect ID or Password");
					alert("아이디 또는 패스워드가 일치하지 않습니다.");
				} else if (response.loginChk == "1") {
					alert("로그인 성공");
					location.href = "/index";
				}
			},
			error: function() {
				alert("로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
			}
		});
	}

	// 회원가입창에서 로그인 화면으로 돌아가기
	function backBtn() {
		window.location.href = "/member/login";
	}

	// 아이디 중복 체크 함수
	function check() {
		const id = $('input[name="id"]').val();
		if (!id) {
			alert("아이디를 입력해주세요!");
			return false;
		}

		let isValid = false; // 로컬 상태 관리

		$.ajax({
			url: "/member/checkId",
			type: "GET",
			data: { id: id },
			success: function(response) {
				if (response == "Able") {
					$(".idmatch").text("사용 가능한 ID입니다.").css("color", "green");
					appState.isValidated = true; 
				} else {
					$(".idmatch").text("이미 사용 중인 ID입니다.").css("color", "red");
					appState.isValidated = false; 
				}
				console.log("중복 확인 후 isValidated:", appState.isValidated);
			},
			error: function() {
				alert("중복 확인 중 오류가 발생했습니다.");
				appState.isValidated = false; // 오류 시 초기화
			}
		});

		return isValid; // 결과 반환
	}

	// 비밀번호 확인 함수
	function checkPasswords() {
		const password = $('input[name="password"]').val();
		const password2 = $('input[name="password2"]').val();

		if (!password && !password2) {
			$(".pwmatch").text("").css("color", ""); // 메시지 초기화
			return false;
		}

		if (password == password2) {
			$(".pwmatch").text("비밀번호가 일치합니다.").css("color", "green");
			return true;
		} else {
			$(".pwmatch").text("비밀번호가 일치하지 않습니다.").css("color", "red");
			return false;
		}
	}

	// 회원가입 버튼 클릭 이벤트 처리
	$('#joinBtn').click(function() {
		const joinid = $('input[name="id"]').val();
		const joinpassword = $('input[name="password"]').val();
		const joinnickname = $('input[name="nickname"]').val();
		if (!appState.isValidated) {
		        alert("아이디 중복 확인을 먼저 해주세요.");
		        return;
		    }
		if (!checkPasswords()) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		if (!joinnickname) {
			alert("닉네임을 입력해주세요.");
			return;
		}

		$.ajax({
			url: "/member/join",
			type: "POST",
			data: {
				id: joinid,
				password: joinpassword,
				nickname: joinnickname,
			},
			success: function() {
				alert("회원가입이 완료되었습니다!");
				window.location.href = "/member/login"; // 성공 시 로그인 페이지로 이동
			},
			error: function() {
				alert("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
			}
		});
	});

	if (window.location.pathname == "/member/join") {
		$(".duplication").on("click", check);
		$('input[name="password"], input[name="password2"]').on("keyup", checkPasswords);
		$("#backBtn").on("click", backBtn);
	}

	if (window.location.pathname == "/member/login") {
		$("#loginBtn").on("click", loginBtn);
		$("#backBtn").on("click", backBtn);
	}
});
