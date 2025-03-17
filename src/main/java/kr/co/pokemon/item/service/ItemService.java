package kr.co.pokemon.item.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.item.dto.ItemDTO;

public interface ItemService extends APIGetable<ItemDTO> {

	List<ItemDTO> getByCategoryId(int categoryId);

	List<ItemDTO> getByNickname(String keyword);

	void UpdateItemBySystem(ItemDTO iDTO);
}
