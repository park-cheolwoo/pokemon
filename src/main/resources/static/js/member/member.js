$(function() {
	
	
	function loginBtn(){
		var id= $("#id").val();
		var password = $("#password").val();
		
		$.ajax({
			url:"/member/login",
			type:'POST',
			data:{
				id:id,
				password:password
			},
			success: function(response){
				console.log("Response:",response);
				if(response.loginChk=="0"){
					alert("아이디 또는 패스워드가 일치하지 않습니다.")
				}else if(response.loginChk =="1"){
					alert("로그인 성공");
					location.href="/index";
				}
			},
			error:function(error){
				alert("로그인 중 오류가 발생했습니다. 다시 시도해주세요.")
			}
		});
	}
	
//회원가입창에서 로그인 화면으로 돌아가기
    function backBtn() {
        $.ajax({
            url: "/member/login",
            type: 'GET',
            success: function(response) {
                location.href = "/member/login";
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }
//아이디 중복체크
    function check() {
        var id = document.getElementsByName("id")[0].value;
        $.ajax({
            url: "/member/checkId",
            type: 'GET',
            data: { id: id },
            success: function(response) {
                if (response === "Able") {
                    document.getElementsByClassName("idmatch")[0].innerText = "사용가능한 ID입니다.";
                    document.getElementsByClassName("idmatch")[0].style.color = "green";
                } else {
                    document.getElementsByClassName("idmatch")[0].innerText = "이미 사용 중인 ID입니다.";
                    document.getElementsByClassName("idmatch")[0].style.color = "red";
                }
            }
        });
    }
//비밀번호 일치
	function checkPasswords() {
	        var password = document.getElementsByName("password")[0].value;
	        var password2 = document.getElementsByName("password2")[0].value;
	        if (password === password2) {
	            document.getElementsByClassName("pwmatch")[0].innerText = "패스워드가 일치합니다.";
	            document.getElementsByClassName("pwmatch")[0].style.color = "green";
	        } else {
	            document.getElementsByClassName("pwmatch")[0].innerText = "패스워드가 일치하지 않습니다.";
	            document.getElementsByClassName("pwmatch")[0].style.color = "red";
	        }
	    }
	$("#loginBtn").on('click',loginBtn);
    $('#backBtn').on('click', backBtn);
	$('.duplication').on('click', check);
	$('input[name="password"], input[name="password2"]').on('keyup', checkPasswords);
	});