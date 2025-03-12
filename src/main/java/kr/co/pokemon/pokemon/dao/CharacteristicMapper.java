package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

@Mapper
public interface CharacteristicMapper {
	
	List<CharacteristicDTO> selectAll(PageRequestDTO page);
	
	CharacteristicDTO selectById(int id);
	
	List<CharacteristicDTO> selectByStatId(int statId);
	
	List<Integer> selectByIds(List<Integer> ids);
	
	int existById(int id);

	void insert(CharacteristicDTO characteristic);
	
	void insertAll(List<CharacteristicDTO> characteristics);

}
