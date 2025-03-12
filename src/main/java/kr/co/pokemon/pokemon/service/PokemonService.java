package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

public interface PokemonService extends APIGetable<PokemonDTO> {

	List<PokemonDTO> getByName(String keyword);



}
