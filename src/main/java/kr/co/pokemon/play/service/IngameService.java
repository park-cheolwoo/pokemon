package kr.co.pokemon.play.service;

import java.util.List;

import kr.co.pokemon.data.service.Getable;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;

public interface IngameService extends Getable<IngameDTO> {
	
	IngameDTO getByPlayerId(String playerId);

	boolean updateSelectionIdx(String playerId, int idx);
	
	boolean updateIngameStatus(String playerId, boolean isInGame);
	
	IngameInfoDTO getIngameInfoByPlayerId(String playerId);

	List<CreatedPokemonDTO> getIngamePokemons(String playerId);
	
	List<CreatedPokemonDTO> getIngameEnemies(String playerId);

}
