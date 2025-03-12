package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.GrowthDTO;
import kr.co.pokemon.pokemon.dto.GrowthDTO.TotalExperience;

@Mapper
public interface GrowthMapper {

	List<GrowthDTO> selectAll(PageRequestDTO page);
	
	GrowthDTO selectById(int id);
	
	List<Integer> selectByIds(List<Integer> ids);
	
	int existById(int id);
	
	void insert(GrowthDTO growth);
	
	void insertLevel(TotalExperience level);
	
	void insertAll(List<GrowthDTO> growths);
	
	void insertAllLevel(List<TotalExperience> experiences);

}
