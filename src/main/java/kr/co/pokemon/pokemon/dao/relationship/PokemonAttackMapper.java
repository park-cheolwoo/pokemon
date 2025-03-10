package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonAttackDTO;

@Mapper
public interface PokemonAttackMapper {
	
	List<PokemonAttackDTO> selectAll(PageRequestDTO page);
	
	PokemonAttackDTO selectById(int id);
	
	List<PokemonAttackDTO> selectByPokemonId(int pokemonId);
	
	List<PokemonAttackDTO> selectByAttackId(int AttackId);
	
	int existById(int id);
	
	void insert(PokemonAttackDTO pokemonAttack);
	
	void insertAll(List<PokemonAttackDTO> pokemonAttacks);

}
