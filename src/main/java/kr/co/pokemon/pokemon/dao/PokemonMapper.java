package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO.PokemonSprites;

@Mapper
public interface PokemonMapper {

	List<PokemonDTO> selectAll(PageRequestDTO page);
	
	PokemonDTO selectById(int id);

	List<Integer> selectByIds(List<Integer> ids);
	
	int existById(int id);
	
	void insert(PokemonDTO pokemon);
	
	void insertAll(List<PokemonDTO> pokemons);
	
	PokemonSprites selectSpritesById(int id);
	
	void insertSprites(PokemonSprites sprites);
	
	void insertAllSprites(List<PokemonSprites> spritess);
	
}
