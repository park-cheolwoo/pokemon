package kr.co.pokemon.play.service;

import java.util.List;

import kr.co.pokemon.data.service.Getable;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;

public interface IngamePokemonService extends Getable<IngamePokemonDTO> {
	
	boolean saveIngamePokemons(List<Integer> ownPokemonIds);
	
	List<CreatedPokemonDTO> getIngamePokemons(int playerId);

}
