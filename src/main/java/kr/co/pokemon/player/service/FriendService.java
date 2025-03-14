package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.player.dto.FriendDTO;

public interface FriendService {
	

	List<FriendDTO> getFriendList(String playerId);

	List<FriendDTO> getPendingList(String playerId);

	boolean addFriend(String sessionId, String tag);

	boolean acceptFriend(String session_id, String playerFrom);

	boolean cancelFriend(String session_id, String playerFrom);

	boolean deleteFriend1(String session_id, String playerFrom);

	boolean deleteFriend2(String session_id, String playerTo);

}
