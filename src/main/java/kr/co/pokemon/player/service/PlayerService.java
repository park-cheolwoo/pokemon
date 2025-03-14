package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.player.dto.FriendDTO;
//import kr.co.pokemon.player.controller.Player;
import kr.co.pokemon.player.dto.PlayerDTO;

public interface PlayerService {
	
	List<PlayerDTO> getAll(PageRequestDTO pDTO);
	
	//PlayerDTO findById(int id);
	
	PlayerDTO getById(String id);

	PlayerDTO updatePlayer(int id, PlayerDTO player);

	void updatePlayerBySystem(PlayerDTO player);
	
	void deletePlayer(int id);
	
	PlayerDTO login(String id, String password);

	boolean isIdAble(String id);

	boolean insertPlayer(PlayerDTO playerDto);

	List<PlayerDTO> getByNickname(String keyword);

//	int countPlayerPokemons(String playerId);

}
