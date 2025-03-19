package kr.co.pokemon.plan.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.plan.dto.StageDTO;

@Mapper
public interface StageMapper {

	StageDTO getStageById(int stageId);

}
