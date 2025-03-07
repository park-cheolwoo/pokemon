package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

@Mapper
public interface PokemonMapper {

	List<PokemonDTO> selectAll(PageRequestDTO page);
	
	PokemonDTO selectById(int id);
	
	int existById(int id);
	
	void insert(PokemonDTO pokemon);
	
	void insertAll(List<PokemonDTO> pokemons);
	
}
