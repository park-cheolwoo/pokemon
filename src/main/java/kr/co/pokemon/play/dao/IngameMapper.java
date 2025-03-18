package kr.co.pokemon.play.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.UpdateIngameDTO;

@Mapper
public interface IngameMapper {

	int insertIngame(String id);
	
	void updateSelectionIdx(UpdateIngameDTO updateDTO);
	
	void updateIngameStatus(UpdateIngameDTO updateDTO);
	
	void updateStageId(UpdateIngameDTO updateDTO);

	void updateMaxStageId(UpdateIngameDTO updateDTO);
	
	IngameDTO selectById(String id);
	
	List<IngameDTO> selectAll(PageRequestDTO page);

}