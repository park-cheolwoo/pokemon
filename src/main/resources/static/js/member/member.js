$(function() {
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

    $('#backBtn').on('click', backBtn);
	$('.duplication').on('click', check);
	$('input[name="password"], input[name="password2"]').on('keyup', checkPasswords);
	});