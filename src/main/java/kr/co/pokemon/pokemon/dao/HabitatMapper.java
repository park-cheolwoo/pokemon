package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;

@Mapper
public interface HabitatMapper {

	List<HabitatDTO> selectAll(PageRequestDTO pate);
	
	HabitatDTO selectById(int id);
	
	List<Integer> selectByIds(List<Integer> ids);
	
	int existById(int id);
	
	void insert(HabitatDTO habitat);
	
	void insertAll(List<HabitatDTO> habitats);

}
