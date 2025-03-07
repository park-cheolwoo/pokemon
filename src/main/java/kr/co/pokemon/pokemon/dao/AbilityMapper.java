package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.AbilityDTO;

@Mapper
public interface AbilityMapper {

	List<AbilityDTO> selectAll(PageRequestDTO page);
	
	AbilityDTO selectById(int id);
	
	int existById(int id);

	void insert(AbilityDTO ability);
	
	void insertAll(List<AbilityDTO> abilities);

}
