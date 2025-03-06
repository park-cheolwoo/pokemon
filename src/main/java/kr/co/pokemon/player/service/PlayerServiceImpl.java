package kr.co.pokemon.player.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.player.dao.PlayerMapper;
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
		int result = playerMapper.insertPlayer(player);
        return result > 0;
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
}
