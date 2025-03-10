package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;

@Mapper
public interface PokemonStatMapper {
	
	List<PokemonStatDTO> selectAll(PageRequestDTO page);
	
	PokemonStatDTO selectById(int id);
	
	List<PokemonStatDTO> selectByPokemonId(int pokemonId);
	
	void insert(PokemonStatDTO pokemonStat);
	
	void insertAll(List<PokemonStatDTO> pokemonStats);

}
