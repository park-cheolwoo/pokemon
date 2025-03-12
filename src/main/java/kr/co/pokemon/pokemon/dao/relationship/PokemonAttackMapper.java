package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonMoveDTO;

@Mapper
public interface PokemonAttackMapper {
	
	List<PokemonMoveDTO> selectAll(PageRequestDTO page);
	
	PokemonMoveDTO selectById(int id);
	
	List<Integer> selectByIds(List<Integer> ids);
	
	List<PokemonMoveDTO> selectByPokemonId(int pokemonId);
	
	List<PokemonMoveDTO> selectByAttackId(int AttackId);
	
	int existById(int id);
	
	void insert(PokemonMoveDTO pokemonAttack);
	
	void insertAll(List<PokemonMoveDTO> pokemonAttacks);

}
