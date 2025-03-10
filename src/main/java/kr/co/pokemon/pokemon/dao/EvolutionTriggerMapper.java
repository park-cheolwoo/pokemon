package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Mapper
public interface EvolutionTriggerMapper {

	List<EvolutionTriggerDTO> selectAll(PageRequestDTO page);
	
	EvolutionTriggerDTO selectById(int id);

	int existById(int id);
	
	void insert(EvolutionTriggerDTO evolutionTrigger);
	
	void insertAll(List<EvolutionTriggerDTO> evolutionTriggers);

}
