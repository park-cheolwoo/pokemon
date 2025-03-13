package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO.PokemonSprites;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;

public interface PokemonService extends APIGetable<PokemonDTO> {
	
	List<PokemonStatDTO> getStatsByPokemonId(int id);
	
	boolean existById(int id);
	
	PokemonSprites getSpritesById(int id);

	List<PokemonDTO> getByName(String keyword);

	void setEvolutionId(int id, int evolutionId);
	
	PokemonDTO getById(int id);

}
