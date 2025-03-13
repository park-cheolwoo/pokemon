package kr.co.pokemon.player.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.player.dto.OwnPokemonSkill;

@Mapper
public interface OwnPokemonSkillMapper {
	
	OwnPokemonSkill selectAbilityById(int id);
	OwnPokemonSkill selectAttackById(int id);
	
	List<PokemonOwnAbility> selectAbilityByOwnPokemonId(int ownPokemonId);
	List<PokemonOwnAttack> selectAttackByOwnPokemonId(int ownPokemonId);
	
	void updateSlotAbility(Map<String, Integer> updateMap);
	void updateSlotAttack(Map<String, Integer> updateMap);
	
	void insertAbility(OwnPokemonSkill ability);
	void insertAttack(OwnPokemonSkill attack);

}
