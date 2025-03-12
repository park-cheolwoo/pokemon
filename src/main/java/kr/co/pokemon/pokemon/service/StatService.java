package kr.co.pokemon.pokemon.service;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.StatDTO;

public interface StatService extends APIGetable<StatDTO> {
	
	boolean existById(int id);

}
