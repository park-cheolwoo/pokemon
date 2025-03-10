package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonEggGroupDTO;

@Mapper
public interface PokemonEggGroupMapper {
	
	List<PokemonEggGroupDTO> selectAll(PageRequestDTO page);
	
	PokemonEggGroupDTO selectById(int id);
	
	List<PokemonEggGroupDTO> selectByPokemonId(int pokemonId);
	
	void insert(PokemonEggGroupDTO pokemonEggGroup);
	
	void insertAll(List<PokemonEggGroupDTO> pokemonEggGroups);

}
