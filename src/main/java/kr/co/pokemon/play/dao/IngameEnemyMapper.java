package kr.co.pokemon.play.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.IngameEnemyDTO;
import kr.co.pokemon.play.dto.UpdateHpPokemonDTO;

@Mapper
public interface IngameEnemyMapper {

	List<IngameEnemyDTO> selectAll(PageRequestDTO page);
	
	IngameEnemyDTO selectById(int id);
	
	List<IngameEnemyDTO> selectByPlayerId(String playerId);
	
	void insert(IngameEnemyDTO ingameEnemy);
	
	void insertAll(List<IngameEnemyDTO> list);
	
	void updateHp(UpdateHpPokemonDTO updateHp);
	
	void deleteByPlayerId(String playerId);

}