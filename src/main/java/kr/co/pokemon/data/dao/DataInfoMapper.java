package kr.co.pokemon.data.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.TableInfoDTO;

@Mapper
public interface DataInfoMapper {

	TableInfoDTO getInfoByTableName(String tableName);

	int getCountByTableName(String tableName);
	
	void deleteDataByTableName(String tableName);

}
