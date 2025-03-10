package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.player.dto.FriendDTO;
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
	
	PlayerDTO getRandomPlayer();
	
	List<FriendDTO> getFriendList(String playerId);
	
	FriendDTO checkFriendRequest(String fromId, String toId);
	
	int insertFriendRequest(String fromId, String toId);
	
	int updateFriendStatus(String requestId);
	
//	int countPlayerPokemons(String playerId);
	
}
