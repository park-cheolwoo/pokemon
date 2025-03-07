package kr.co.pokemon.pokemon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.TypesDTO;

@Mapper
public interface TypesMapper {

	List<TypesDTO> selectAll(PageRequestDTO page);
	
	TypesDTO selectById(int id);
	
	TypesDTO selectByName(String originalName);
	
	int existById(int id);
	
	void insert(TypesDTO types);
	
	void insertAll(List<TypesDTO> typess);
	
}
