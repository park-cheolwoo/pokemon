package kr.co.pokemon.play.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.service.Getable;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;

public interface IngameService extends Getable<IngameDTO> {

	IngameDTO getByPlayerId(String playerId);
	
	void insert(IngameDTO dto);
	
	boolean updateSelectionIdx(String playerId, int idx);
	
	boolean updateIngameStatus(String playerId, boolean isInGame);
	
	boolean updateIngameStage(String playerId, int stage);

	boolean updateIngameMaxStage(String playerId, int stage);
	
	IngameInfoDTO getIngameInfoByPlayerId(String playerId);
	
	List<CreatedPokemonDTO> getIngamePokemons(String playerId);
	
	List<CreatedPokemonDTO> getIngameEnemies(String playerId);
	
	// 원정대 관련 메서드
	List<IngamePokemonDTO> getExpeditionByPlayerId(String playerId);

	boolean saveExpeditionList(String playerId, List<Integer> expeditionPokemonIds);

}