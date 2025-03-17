package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.PokemonOwnType;
import kr.co.pokemon.pokemon.dto.relationship.PokemonTypesDTO;

@Mapper
public interface PokemonTypesMapper {
	
	List<PokemonTypesDTO> selectAll(PageRequestDTO page);
	
	PokemonTypesDTO selectById(int id);
	
	List<PokemonTypesDTO> selectByPokemonId(int pokemonId);
	
	List<PokemonTypesDTO> selectByTypesId(int typesId);
	
	int existById(int id);

	List<PokemonOwnType> selectTypesByPokemonId(int pokemonId);
	
	void insert(PokemonTypesDTO pokemonTypes);
	
	void insertAll(List<PokemonTypesDTO> pokemonTypess);

}
