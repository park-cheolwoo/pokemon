package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

public interface HabitatService extends APIGetable<HabitatDTO> {
	
	List<HabitatDTO> getHabitatByPokemonId(int pokemonId);

	List<PokemonDTO> getPokemonByHabitatId(int habitatId);

}
