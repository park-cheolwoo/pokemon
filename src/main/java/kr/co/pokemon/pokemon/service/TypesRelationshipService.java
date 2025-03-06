package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.TypesRelationshipDTO;

public interface TypesRelationshipService extends APIGetable<TypesRelationshipDTO> {

	List<TypesRelationshipDTO> getByTypeId(int id, boolean isName);

}
