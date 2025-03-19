<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="content-container" class="mypage-content-container">

	<div class="poke-info-container">
		<div class="poke-img">
			<img src=""/>
		</div>
	</div>
	<div class="mypage-container">
		<ul class="mypage-banner-tab">
			<li class="banner-item" data-tab="pokemon-info">
				<span>기본 정보</span>
			</li>
			<li class="banner-item" data-tab="evolution-tree">
				<span>진화트리</span>
			</li>
		</ul>
		<!-- 탭 컨텐츠 영역 -->
		<div class="pokedexView-info-text-container">
			<div class="tab-content" id="pokemon-info">
				<table class="pokedexView-table">
					<!-- 포켓몬 기본 정보가 여기에 표시됩니다 -->
				</table>
			</div>
			<div class="tab-content" id="evolution-tree">
				<div class="evolution-container">
					<table class="pokedexView-table">
						<!-- 진화 트리 정보가 여기에 표시됩니다 -->
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- pokedexView.js 스크립트 추가 -->
<script src="/js/play/ingame.js"></script>
<script src="/js/mypage/pokedexView.js"></script>