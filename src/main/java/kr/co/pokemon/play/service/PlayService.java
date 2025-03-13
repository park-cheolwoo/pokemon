package kr.co.pokemon.play.service;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;

public interface PlayService {

	CreatedPokemonDTO createPokemon(int pokemonId);
	
	CreatedPokemonDTO createPokemonByHabitatId(int habitatId);
	
	IngameDTO getIngame(int playerId);

}
