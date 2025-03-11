<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="content-container" class="mypage-content-container">
	<div class="item-view-info-container">
		<div class="item-img">
			<img src="/images/monsterball.png" alt="몬스터볼 이미지">
		</div>
		<div class="item-text-container">
			<div class="item-text">
				<div class="item-name">몬스터볼</div>
				<div class="item-info">아이템 내용</div>
			</div>
		</div>
	</div>
	<div class="mypage-container">
		<ul class="mypage-banner-tab">
			<li class="banner-item" data-tab="monsterball-item">
				<span>몬스터볼</span>
			</li>
			<li class="banner-item" data-tab="battle-item">
				<span>배틀아이템</span>
			</li>
			<li class="banner-item" data-tab="training-item">
				<span>육성아이템</span>
			</li>
		</ul>

		<!-- 탭 컨텐츠 영역 -->
		<div class="item-info-text-container">
			<div class="tab-content" id="monsterball-item">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="monsterball-item-list-body">
						<tr>
							<td>이미지</td>
							<td>몬스터볼</td>
							<td>1개</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-content" id="battle-item">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="battle-item-list-body">
						<tr>
							<td>이미지</td>
							<td>배틀 아이템</td>
							<td>1개</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-content" id="training-item">
				<table class="item-table">
					<thead>
						<tr>
							<th>이미지</th>
							<th>이름</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody id="training-item-list-body">
						<tr>
							<td>이미지</td>
							<td>육성 아이템</td>
							<td>1개</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>



	</div>
</div>
