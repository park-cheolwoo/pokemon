package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;

@Mapper
public interface EggGroupMapper {
	
	List<EggGroupDTO> selectAll(PageRequestDTO page);
	EggGroupDTO selectById(int id);
	
	int existById(int id);

	void insert(EggGroupDTO eggGroup);

	void insertAll(List<EggGroupDTO> eggGroups);

}
