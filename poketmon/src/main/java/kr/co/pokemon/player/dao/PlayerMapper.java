package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.player.dto.PlayerDTO;

@Mapper
public interface PlayerMapper {

	List<PlayerDTO> selectAll();
	PlayerDTO selectById(int id);
	
	void insertPlayer(PlayerDTO player);
	void updatePlayer(PlayerDTO player);
	
	void updatePlayerBySystem(PlayerDTO player);
	
	void deletePlayer(int id);
	
}
