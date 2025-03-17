package kr.co.pokemon.plan.service;

import kr.co.pokemon.plan.dto.SdungeonDTO;

public interface SdungeonService {
	// sdungeon 불러오기
	SdungeonDTO getSdungeonById(String playerId);
	
	// player 계정만들때 sdungeon 디비 같이 생성
	void createSdungeonForPlayer(String id);

}
