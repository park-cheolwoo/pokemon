$(function() {
	// 친구 추가 버튼 클릭 이벤트
	$('.friendplusbtn').on('click', function() {
		const friendTag = $('.friendcode').val();

		if (!friendTag) {
			alert('친구 코드를 입력해주세요!');
			return;
		}
		$.ajax({
			url: '/friend/add',
			type: 'POST',
			data: { tag: friendTag },
			success: function(response) {
				if (response == 'SUCCESS') {
					alert('친구 요청이 성공적으로 전송되었습니다.');
				} else if (response == 'NOT_FOUND') {
					alert('해당 코드를 가진 사용자를 찾을 수 없습니다.');
				} else {
					alert('요청 중 오류가 발생했습니다.');
				}
			},
			error: function() {
				alert('서버와 통신 중 오류가 발생했습니다.');
			}
		});
	});
	  // 요청 수락 버튼 클릭 이벤트
	  $(document).on('click', '.accept-request', function () {
	    const playerFrom = $(this).data('player-from'); 
	    if (!playerFrom) {
	      alert('잘못된 요청입니다.');
	      return;
	    }
	    $.ajax({
	      url: '/friend/accept',
	      type: 'POST',
	      data: { playerFrom: playerFrom },
	      success: function (response) {
	        if (response == 'success') {
	          alert('친구 요청을 성공적으로 수락했습니다.');
	          location.reload();
	        } else {
	          alert('요청 수락 중 오류가 발생했습니다.');
	        }
	      },
	      error: function () {
	        alert('서버와 통신 중 문제가 발생했습니다.');
	      }
	    });
	});
	  // 요청 취소 버튼 클릭 이벤트
	  $(document).on('click', '.cancel-request', function () {
	    const playerFrom = $(this).data('player-from'); 
	    if (!playerFrom) {
	      alert('잘못된 요청입니다.');
	      return;
	    }
	    $.ajax({
	      url: '/friend/cancel', 
	      type: 'POST',
	      data: { playerFrom: playerFrom },
	      success: function (response) {
	        if (response == 'success') {
	          alert('친구 요청을 취소했습니다.');
	          location.reload(); 
	        } else {
	          alert('요청 수락 중 오류가 발생했습니다.');
	        }
	      },
	      error: function () {
	        alert('서버와 통신 중 문제가 발생했습니다.');
	      }
	  	});
	  });
	  // 친구 삭제 버튼1 클릭 이벤트
	  $(document).on('click', '#deletefr1', function () {
	    const playerFrom = $(this).data('player-from'); 
	    if (!playerFrom) {
	      alert('잘못된 요청입니다.');
	      return;
	    }
		//삭제 확인 여부
		const confirmation = confirm("정말 친구를 삭제하시겠습니까?");
		if (confirmation){
			$.ajax({
				      url: '/friend/delete1', 
				      type: 'POST',
				      data: { playerFrom: playerFrom },
				      success: function (response) {
				        if (response == 'success') {
				          alert('친구를 삭제했습니다.');
				          location.reload(); 
				        } else {
				          alert('친구 삭제 중 오류가 발생했습니다.');
				        }
				      },
				      error: function () {
				        alert('서버와 통신 중 문제가 발생했습니다.');
				      }
				  });
				  }else{
					conseol.log('친구 삭제가 취소되었습니다.')
				  }
			});
	  
	  // 친구 삭제 버튼2 클릭 이벤트
	  $(document).on('click', '#deletefr2', function () {
	    const playerTo = $(this).data('player-to'); 
	    if (!playerTo) {
	      alert('잘못된 요청입니다.');
	      return;
	    }
		//삭제 확인 여부
		const confirmation = confirm("정말 친구를 삭제하시겠습니까?");
		if (confirmation){
		    $.ajax({
		      url: '/friend/delete2', 
		      type: 'POST',
		      data: { playerTo: playerTo },
		      success: function (response) {
		        if (response == 'success') {
		          alert('친구를 삭제했습니다.');
		          location.reload(); 
		        } else {
		          alert('요청 수락 중 오류가 발생했습니다.');
		        }
		      },
		      error: function () {
		        alert('서버와 통신 중 문제가 발생했습니다.');
		      }
		    });
			}else{
				console.log('친구 삭제가 취소되었습니다.');
			}
		});
});
