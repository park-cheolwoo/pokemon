package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;

@Mapper
public interface HabitatMapper {

	List<HabitatDTO> selectAll(PageDTO pate);
	
	HabitatDTO selectById(int id);
	
	int existById(int id);
	
	void insert(HabitatDTO habitat);

}
