package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

public interface CharacteristicService extends APIGetable<CharacteristicDTO> {
	
	List<CharacteristicDTO> getCharacteristicsByStatId(int statId);

}
