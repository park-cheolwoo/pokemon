package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.player.dto.PlayerOwnItem;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;

public interface PlayerItemService{
    List<PlayerItemDTO> getAll(PageRequestDTO page);
    
    PlayerItemDTO getById(int id);
    
    List<ItemDTO> getItemsByPlayerId(String playerId);
    
    List<PlayerItemDTO> getByPlayerId(String playerId);
    
    List<PlayerItemDTO> getByItemId(int itemId);

    List<PlayerOwnItem> getItemsInfoByPlayerId(String playerId);

    PlayerItemDTO getByPlayerIdAndItemId(String playerId, int itemId); 
    
    void addItem(PlayerItemDTO playerItem);
    
    void addItems(List<PlayerItemDTO> playerItems);
    
    void updateItem(PlayerItemDTO playerItem);
    
    int useItem(String playerId, int playerItemId);
    
    void removeItem(int id);
}
