package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;

@Mapper
public interface EggGroupMapper {
	
	List<EggGroupDTO> selectAll(PageDTO page);
	EggGroupDTO selectById(int id);
	
	int existById(int id);

	void insert(EggGroupDTO eggGroup);

}
