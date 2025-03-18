package kr.co.pokemon.play.service;

import java.util.List;

import kr.co.pokemon.data.service.Getable;
import kr.co.pokemon.play.dto.IngameEnemyDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.dto.UpdateHpPokemonDTO;

public interface IngamePokemonService extends Getable<IngamePokemonDTO> {
	
	boolean saveIngamePokemons(List<IngamePokemonDTO> ownPokemons);
	
	boolean saveIngameEnemies(List<IngameEnemyDTO> enemies);
	
	boolean deleteEnemies(String playerId);

	List<IngamePokemonDTO> getIngamePokemons(String playerId);
	
	List<IngameEnemyDTO> getIngameEnemies(String playerId);
	
	void updateIngamePokemonHp(UpdateHpPokemonDTO updateHp);
	
	void updateIngameEnemyHp(UpdateHpPokemonDTO updateHp);

}
