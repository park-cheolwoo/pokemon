package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

@Mapper
public interface CharacteristicMapper {
	
	List<CharacteristicDTO> selectAll(PageRequestDTO page);
	
	CharacteristicDTO selectById(int id);
	
	int existById(int id);

	void insert(CharacteristicDTO charracteristic);

}
