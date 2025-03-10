package kr.co.pokemon.data.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.data.dto.DataDeleteDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;

@Mapper
public interface DataInfoMapper {

	TableInfoDTO getTableInfoByName(String tableName);
	
	int existSequenceByName(String sequenceName);

	int getTableDataCountByName(String tableName);
	
	void dropTableByName(String tableName);
	
	void dropSequenceByName(String sequenceName);
	
	void deleteDataByName(String tableName);

	void deleteDataByPage(DataDeleteDTO table);

}
