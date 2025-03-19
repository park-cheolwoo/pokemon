package kr.co.pokemon.player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemService;
import kr.co.pokemon.player.dao.relationship.PlayerItemMapper;
import kr.co.pokemon.player.dto.PlayerOwnItem;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;

@Service
public class PlayerItemServiceImpl implements PlayerItemService {

	   @Autowired
	    private PlayerItemMapper playerItemMapper;
	@Autowired
	private ItemService itemService;

	@Override
	    public List<PlayerItemDTO> getAll(PageRequestDTO page) {
	        return playerItemMapper.selectAll(page);
	    }
	    
	    @Override
	    public PlayerItemDTO getById(int id) {
	        return playerItemMapper.selectById(id);
	    }
	    
	    @Override
	    public List<ItemDTO> getItemsByPlayerId(String playerId) {
	        return playerItemMapper.selectItemsByPlayerId(playerId);
	    }
	    
	    @Override
	    public List<PlayerItemDTO> getByPlayerId(String playerId) {
	        return playerItemMapper.selectByPlayerId(playerId);
	    }
	    
	    @Override
	    public List<PlayerItemDTO> getByItemId(int itemId) {
	        return playerItemMapper.selectByItemId(itemId);
	    }

	@Override
	public List<PlayerOwnItem> getItemsInfoByPlayerId(String playerId) {
		return playerItemMapper.selectByPlayerId(playerId).stream()
			.map(playerItem -> {
				ItemDTO itemDTO = itemService.getById(playerItem.getItemId());
				return new PlayerOwnItem(playerItem, itemDTO);
			}).toList();
	}

	@Override
	    public void addItem(PlayerItemDTO playerItem) {
	        playerItemMapper.insert(playerItem);
	    }
	    
	    @Override
	    public void addItems(List<PlayerItemDTO> playerItems) {
	        playerItemMapper.insertAll(playerItems);
	    }
	    
	    @Override
	    public void updateItem(PlayerItemDTO playerItem) {
	        playerItemMapper.update(playerItem);
	    }
	    
	    @Override
			public void removeItem(int id) {
				playerItemMapper.delete(id);
			}
			
			@Override
			public PlayerItemDTO getByPlayerIdAndItemId(String playerId, int itemId) {
				return playerItemMapper.getByPlayerIdAndItemId(playerId, itemId);
			}

}
