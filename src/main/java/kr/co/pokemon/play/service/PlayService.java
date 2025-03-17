package kr.co.pokemon.play.service;

import java.util.List;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;

public interface PlayService {

	CreatedPokemonDTO createPokemon(int pokemonId, int minLevel, int maxLevel);
	
	CreatedPokemonDTO createPokemonByHabitatId(int habitatId, int minLevel, int maxLevel);
	
	IngameDTO getIngame(int playerId);

	List<CreatedPokemonDTO> getIngamePokemons(String playerId);

}
