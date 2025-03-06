package kr.co.pokemon.pokemon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.TypesRelationshipDTO;

@Mapper
public interface TypesRelationshipMapper {

	List<TypesRelationshipDTO> selectAll(PageRequestDTO page);
	
	List<TypesRelationshipDTO> selectByTypeId(int id);

	TypesRelationshipDTO selectByTypeIdAndTypeId(Map<String, Integer> types);

	TypesRelationshipDTO selectById(int id);

	void insert(TypesRelationshipDTO typeRelationship);
	
}
