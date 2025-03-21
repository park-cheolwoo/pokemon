<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="content-container" class="mypage-content-container">
	<div class="item-view-info-container">
		<div class="mypage-item-img">
			<img/>
		</div>
		<div class="mypage-item-text-container">
			<div class="mypage-item-text">
				<div class="mypage-item-name"></div>
				<div class="mypage-item-info"></div>
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
					</tbody>
				</table>
			</div>
		</div>



	</div>
</div>
