package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.player.dto.FriendDTO;
//import kr.co.pokemon.player.controller.Player;
import kr.co.pokemon.player.dto.PlayerDTO;

public interface PlayerService {
	
	List<PlayerDTO> findAll(int size, int page);
	
	PlayerDTO findById(int id);

	PlayerDTO updatePlayer(int id, PlayerDTO player);
	
	void deletePlayer(int id);
	
	PlayerDTO login(String id, String password);

	boolean isIdAble(String id);

	boolean insertPlayer(PlayerDTO playerDto);


//	int countPlayerPokemons(String playerId);

}
