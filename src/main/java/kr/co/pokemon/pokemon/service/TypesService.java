package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.play.dto.PokemonOwnType;
import kr.co.pokemon.pokemon.dto.TypesDTO;
import kr.co.pokemon.pokemon.dto.relationship.TypesRelationshipDTO;

public interface TypesService extends APIGetable<TypesDTO> {

	List<PokemonOwnType> getTypesByPokemonId(int pokemonId);

	List<TypesRelationshipDTO> getTypesRelationshipByTypesId(int typesId);

}
