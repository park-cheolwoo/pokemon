package kr.co.pokemon.player.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired PlayerMapper playerMapper;
	
	@Override
	public List<PlayerDTO> findAll(int size, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerDTO findById(int id) {
		Map<String, Object> map = new HashMap<>();
		PlayerDTO playerDto = playerMapper.selectById(id);
		map.put("playerDto", playerDto);
		return playerDto;
	}

	@Override
	public boolean createPlayer(PlayerDTO player) {
		if (player.getTag() == null || player.getTag().isEmpty()) {
            player.setTag(generateRandomTag());  // 랜덤 태그 값 설정
		}
        playerMapper.insertPlayer(player);
        return true;
	}
	
	private String generateRandomTag() {
        return "#"+UUID.randomUUID().toString().substring(0, 9); 
    }

	@Override
	public PlayerDTO updatePlayer(int id, PlayerDTO player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlayer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public PlayerDTO login(String id, String password) {
		PlayerDTO playerdto = playerMapper.selectLogin(id,password);
		return playerdto;
	}

	@Override
	public boolean isIdAble(String id) {
		PlayerDTO playerdto = playerMapper.chooseById(id);
		return playerdto == null;
	}

	@Override
	public PlayerDTO getRandomPlayer() {
		PlayerDTO randomPlayer = playerMapper.getRandomPlayer();
		return playerMapper.getRandomPlayer();
	}

	@Override
	public List<FriendDTO> getFriendList(String playerId) {
		return playerMapper.getFriendList(playerId);
	}

	@Override
	public boolean sendFriendRequest(String fromId, String toId) {
		FriendDTO existingRequest = playerMapper.checkFriendRequest(fromId, toId);
		if(existingRequest != null) {
		return false;
	}
		return playerMapper.insertFriendRequest(fromId, toId)>0;
	}

	@Override
	public boolean acceptFriendRequest(String requestId) {
		return playerMapper.updateFriendStatus(requestId) >0;
	}

//	@Override
//	public int countPlayerPokemons(String playerId) {
//		return playerMapper.countPlayerPokemons(playerId);
//	}
	
	
}
