package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.AttackDTO;

@Mapper
public interface AttackMapper {

	List<AttackDTO> selectAll(PageRequestDTO page);
	
	List<AttackDTO> selectByTypeId(int typesId);

	AttackDTO selectById(int id);
	
	int existById(int id);
	
	void insert(AttackDTO attack);

}
