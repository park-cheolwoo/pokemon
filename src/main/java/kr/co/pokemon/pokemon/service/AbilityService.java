package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.pokemon.dto.AbilityDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

public interface AbilityService extends APIGetable<AbilityDTO> {
	
	boolean existAbilityAndPokemonId(int pokemonId, int abilityId);
	
	List<PokemonOwnAbility> getAbilitiesByPokemonId(int pokemonId);
	
	List<PokemonDTO> getPokemonByAbilityId(int abilityId);

}
