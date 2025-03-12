package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Mapper
public interface FriendMapper {

	List<FriendDTO> getFriendList(String playerId);

	FriendDTO selectFriend(String playerId, String sessionId);

	void insertFriend(@Param("id") Long id, @Param("playerFrom") String playerFrom, @Param("playerTo") String playerTo);

	void updateFriendAccept(String playerId, String sessionId);
	
	List<FriendDTO> getPending(@Param("playerTo") String playerTo);

}
