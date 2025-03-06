package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.player.dto.PlayerDTO;

@Mapper
public interface PlayerMapper {

	List<PlayerDTO> selectAll();
	PlayerDTO selectById(int id);
	
	void updatePlayer(PlayerDTO player);
	
	void updatePlayerBySystem(PlayerDTO player);
	
	void deletePlayer(int id);
	
	PlayerDTO selectLogin(String id,String password);
	
	int insertPlayer(PlayerDTO player);
	PlayerDTO chooseById(String id);
	
}
