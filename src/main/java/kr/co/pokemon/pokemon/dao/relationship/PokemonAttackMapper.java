package kr.co.pokemon.pokemon.dao.relationship;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonMoveDTO;

@Mapper
public interface PokemonAttackMapper {
	
	List<PokemonMoveDTO> selectAll(PageRequestDTO page);
	
	PokemonMoveDTO selectById(int id);
	
	List<PokemonOwnAttack> selectNoDamageAttackByPokemonId(int pokemonId);

	List<PokemonOwnAttack> selectAttackByPokemonId(int pokemonId);

	List<PokemonOwnAttack> selectByPokemonIdAndTypeId(Map<String, Integer> pokemonIdAndTypeId);

	List<PokemonDTO> selectPokemonByAttackId(int attackId);
	
	List<Integer> selectByIds(List<Integer> ids);
	
	List<PokemonMoveDTO> selectByPokemonId(int pokemonId);
	
	List<PokemonMoveDTO> selectByAttackId(int AttackId);
	
	int existById(int id);
	
	int existAttackAndPokemonId(Map<String, Integer> existMap);
	
	void insert(PokemonMoveDTO pokemonAttack);
	
	void insertAll(List<PokemonMoveDTO> pokemonAttacks);

}
