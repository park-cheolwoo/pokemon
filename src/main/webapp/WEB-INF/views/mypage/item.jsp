<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="content-container" class="mypage-content-container">
	<div class="item-view-info-container">
		<div class="item-img">
			<img src="/static/images/monsterball.png" alt="몬스터볼 이미지">
		</div>
		<div class="item-text-container">
			<div class="item-text">
				<div class="item-name">몬스터볼</div>
				<div class="item-info">아이템 내용</div>
			</div>
		</div>
	</div>
	<div class="mypage-container">
		<div class="mypage-banner-tab">
			<div class="banner-item" data-tab="monsterball-item">
				<span>몬스터볼</span>
			</div>
			<div class="banner-item" data-tab="revive-item">
				<span>회복 아이템</span>
			</div>
			<div class="banner-item" data-tab="battle-item">
				<span>배틀 아이템</span>
			</div>
		</div>
		<!-- 탭 컨텐츠 영역 -->
		<div class="item-info-text-container">
			<div class="tab-content" id="monsterball-item-content"
				style="display: none;">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="item-list-body">
						<!-- 탭 클릭 시 내용이 동적으로 추가될 영역 -->
					</tbody>
				</table>
			</div>
			<div class="tab-content" id="revive-item-content"
				style="display: none;">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="revive-item-list-body">
						<!-- 회복 아이템 -->
					</tbody>
				</table>
			</div>
			<div class="tab-content" id="battle-item-content"
				style="display: none;">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="battle-item-list-body">
						<!-- 배틀 아이템 -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
