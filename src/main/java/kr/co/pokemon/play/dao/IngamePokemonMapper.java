package kr.co.pokemon.play.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.dto.UpdateHpPokemonDTO;

@Mapper
public interface IngamePokemonMapper {

	List<IngamePokemonDTO> selectAll(PageRequestDTO page);
	
	IngamePokemonDTO selectById(int id);
	
	List<IngamePokemonDTO> selectByPlayerId(String playerId);
	
	void insert(IngamePokemonDTO ingamePokemon);
	
	void insertAll(List<IngamePokemonDTO> list);
	
	void updateHp(UpdateHpPokemonDTO updateHp);
	
	void deleteByPlayerId(String playerId);
	
}