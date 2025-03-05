package kr.co.pokemon.player.service;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerDTO createPlayer(PlayerDTO player) {
		// TODO Auto-generated method stub
		return null;
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
	public PlayerDTO login(PlayerDTO pdto) {
		PlayerDTO playerDto = playerMapper.selectLogin(pdto);
		return playerDto;
	}

	@Override
	public PlayerDTO login(String id, String pw) {
		PlayerDTO playerdto = playerMapper.selectLogin2(id,pw);
		return null;
	}

}
