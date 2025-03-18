package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Mapper
public interface PlayerMapper {

	List<PlayerDTO> selectAll(PageRequestDTO pDTO);
	
	PlayerDTO selectById(String id);
	
	void updatePlayerBySystem(PlayerDTO player);
	
	void deletePlayer(int id);
	
	PlayerDTO selectLogin(String id,String password);
	
	int insertPlayer(PlayerDTO player);
	
	PlayerDTO chooseById(String id);
	
	PlayerDTO findByTag(String tag);

	List<PlayerDTO> getByNickname(String keyword);

	void updateplayer(PlayerDTO player);

	PlayerDTO findById(String sessionId);
	
}
