package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.StatDTO;

@Mapper
public interface StatMapper {

	List<StatDTO> selectAll(PageRequestDTO page);
	
	StatDTO selectById(int id);
	
	int existById(int id);

	void insert(StatDTO stat);
	
	void insertAll(List<StatDTO> stats);

}
