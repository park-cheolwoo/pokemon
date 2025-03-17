package kr.co.pokemon.play.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.GameStageDTO;

@Mapper
public interface GameStageMapper {
	
	List<GameStageDTO> selectAll(PageRequestDTO page);
	
	GameStageDTO selectById(int id);
	
	void insert(GameStageDTO gameStage);
	
	void insertAll(List<GameStageDTO> list);

}
