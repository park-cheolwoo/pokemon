package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.EvolutionDTO;
import kr.co.pokemon.pokemon.dto.relationship.EvolutionDetail;

@Mapper
public interface EvolutionMapper {

	List<EvolutionDTO> selectAll(PageRequestDTO page);
	
	EvolutionDTO selectById(int id);
	
	List<Integer> selectByIds(List<Integer> ids);

	EvolutionDTO selectByCurrId(int currId);

	int existById(int id);

	void insert(EvolutionDTO evolution);
	
	void insertAll(List<EvolutionDTO> evolutions);
	
	void deleteByCurrId(int currId);
	
	EvolutionDetail selectDetailById(int id);

	EvolutionDetail selectDetailsByEvolutionId(int evolutionId);

	EvolutionDetail selectDetailsByCurrId(int currId);

	void insertDetail(EvolutionDetail evolutionDetail);
	
	void insertAllDetail(List<EvolutionDetail> evolutionDetails);

	void deleteDetailByCurrId(int currId);

}
