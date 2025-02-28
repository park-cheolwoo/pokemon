package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Mapper
public interface EvolutionTriggerMapper {

	List<EvolutionTriggerDTO> selectAll(PageDTO page);
	
	EvolutionTriggerDTO selectById(int id);

	int existById(int id);
	
	void insert(EvolutionTriggerDTO evolutionTrigger);

}
