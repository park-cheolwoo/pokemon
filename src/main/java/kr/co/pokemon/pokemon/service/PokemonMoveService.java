package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonMoveDTO;

public interface PokemonMoveService extends APIGetable<PokemonMoveDTO> {
	
	List<PokemonOwnAttack> getAttacksByPokemonId(int pokemonId);
	
	List<PokemonDTO> getPokemonByAttackId(int attackId);

	List<PokemonOwnAttack> getAttacksByPokemonIdAndTypeId(int pokemonId, int typeId);
	
	boolean existAttackAndPokemonId(int pokemonId, int attackId);

}
