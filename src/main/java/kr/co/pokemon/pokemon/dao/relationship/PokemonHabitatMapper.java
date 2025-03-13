package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonHabitatDTO;

@Mapper
public interface PokemonHabitatMapper {
	
	List<PokemonHabitatDTO> selectAll(PageRequestDTO page);
	
	PokemonHabitatDTO selectById(int id);
	
	List<HabitatDTO> selectHabitatByPokemonId(int pokemonId);
	
	List<PokemonDTO> selectPokemonByHabitatId(int habitatId);
	
	void insert(PokemonHabitatDTO pokemonHabitat);
	
	void insertAll(List<PokemonHabitatDTO> pokemonHabitats);

}
