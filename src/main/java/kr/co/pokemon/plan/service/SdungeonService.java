package kr.co.pokemon.plan.service;

import kr.co.pokemon.plan.dto.SdungeonDTO;

public interface SdungeonService {
	// sdungeon 불러오기
	SdungeonDTO getSdungeonById(String playerId);
	
	// player 계정만들때 sdungeon 디비 같이 생성
	void createSdungeonForPlayer(String id);
	
	// 특수던전 클리어 횟수 증가
	void updateSdungeonCount(SdungeonDTO sdungeonDto);
	
	// 퀘스트에 sdungeon 정보 불러오기
	SdungeonDTO getSdungeonInfo(String playerId);


}
