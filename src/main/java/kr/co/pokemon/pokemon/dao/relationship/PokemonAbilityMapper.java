package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonAbilityDTO;

@Mapper
public interface PokemonAbilityMapper {
	
	List<PokemonAbilityDTO> selectAll(PageRequestDTO page);
	
	PokemonAbilityDTO selectById(int id);
	
	List<PokemonAbilityDTO> selectByPokemonId(int pokemonId);
	
	List<PokemonAbilityDTO> selectByAbilityId(int abilityId);
	
	int existById(int id);
	
	void insert(PokemonAbilityDTO pokemonAbility);
	
	void insertAll(List<PokemonAbilityDTO> pokemonAbilities);

}
