package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.player.dto.PlayerDTO;

public interface PlayerService {
	
	List<PlayerDTO> findAll(int size, int page);
	PlayerDTO findById(int id);

	PlayerDTO createPlayer(PlayerDTO player);	

	PlayerDTO updatePlayer(int id, PlayerDTO player);
	
	void deletePlayer(int id);

}
