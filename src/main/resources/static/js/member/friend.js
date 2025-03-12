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
			},
		});
	});

	// 요청 수락 버튼
	$('#friend-requests').on('click', '.accept-request', function() {
		const requestId = $(this).data('id'); // 요청 ID 가져오기

		$.ajax({
			url: '/friend/accept', // 수락 서버로 POST 요청
			type: 'POST',
			data: { id: requestId },
			success: function(response) {
				if (response == 'SUCCESS') {
					alert('친구 요청을 수락했습니다.');
					location.reload(); // UI 갱신
				} else {
					alert('요청 수락 중 오류가 발생했습니다.');
				}
			},
			error: function() {
				alert('서버와 통신 중 오류가 발생했습니다.');
			},
		});
	});
});
