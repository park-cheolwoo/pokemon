package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Mapper
public interface FriendMapper {

	List<FriendDTO> getFriendList(String playerId);

	List<FriendDTO> getPendingList(String playerId);

	void insertFriend(@Param("id") Long id, @Param("playerFrom") String playerFrom, @Param("playerTo") String playerTo);

	String selectFriendByPlayerFrom(String playerFrom, String session_id);

	void updateFriendAcceptByPlayerFrom(String playerFrom, String session_id);

	void cancelFriendByPlayerFrom(String playerFrom, String session_id);

	String selectFrinedFordelete1(String playerFrom, String session_id);

	void deleteFriendByPlayerFrom(String playerFrom, String session_id);

	String selectFrinedFordelete2(String playerTo, String session_id);

	void deleteFriendByPlayerTo(String playerTo, String session_id);


	

}
