package kr.co.pokemon.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemCategoryDTO;

@Mapper
public interface ItemCategoryMapper{
	
	List<ItemCategoryDTO> selectAll(PageRequestDTO page);

	ItemCategoryDTO selectById(int id);

	void insert(ItemCategoryDTO dto);

	int existById(int id);
	
	void insertAll(List<ItemCategoryDTO> itemCategories);
	
}
