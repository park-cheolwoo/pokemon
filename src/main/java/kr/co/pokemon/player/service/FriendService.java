package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.player.dto.FriendDTO;

public interface FriendService {
	

	List<FriendDTO> getFriendList(String playerId);

	boolean addFriend(String sessionId, String tag);

	boolean acceptFriend(String sessionId, String playerId);

	List<FriendDTO> getPending(String playerToId);

}
