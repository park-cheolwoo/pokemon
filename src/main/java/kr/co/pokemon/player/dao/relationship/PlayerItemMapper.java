package kr.co.pokemon.player.dao.relationship;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;

@Mapper
public interface PlayerItemMapper {

	List<PlayerItemDTO> selectAll(PageRequestDTO page);
	
	PlayerItemDTO selectById(int id);
	
	List<ItemDTO> selectItemsByPlayerId(String playerId);
	
	List<PlayerDTO> selectPlayersByItemId(int itemId);
	
	List<PlayerItemDTO> selectByPlayerId(String playerId);

	PlayerItemDTO getByPlayerIdAndItemId(String playerId, int itemId);
	
	List<PlayerItemDTO> selectByItemId(int itemId);
	
	int existById(int id);
	
	void insert(PlayerItemDTO playerItem);
	
	void insertAll(List<PlayerItemDTO> playerItems);
	
	void update(PlayerItemDTO playerItem);
	
	void delete(int id);
	
}