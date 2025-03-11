package kr.co.pokemon.item.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;

@Mapper
public interface ItemMapper {

	List<ItemDTO> selectAll(PageRequestDTO page);

	ItemDTO selectById(int id);

	List<ItemDTO> selectByCategoryId(int categoryId);
	
	void insert(ItemDTO dto);
	
	void insertAll(List<ItemDTO> dtos);

}
