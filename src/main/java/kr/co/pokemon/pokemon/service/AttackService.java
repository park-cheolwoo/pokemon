package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.AttackDTO;

public interface AttackService extends APIGetable<AttackDTO> {

	List<AttackDTO> getByTypeId(int typesId);

}
