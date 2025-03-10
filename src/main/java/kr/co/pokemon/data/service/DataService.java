package kr.co.pokemon.data.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;

public interface DataService {
	
	void initTable() throws SQLException;

	<D, S extends Getable<D>> List<D> getAll(PageRequestDTO page, Class<S> serviceClass);
	
	<D, S extends Getable<D>> Optional<D> getById(int id, Class<S> serviceClass);
	
	<D, S extends Getable<D>> void insert(D dto, Class<S> serviceClass);

	public List<TableInfoDTO> getAllTableInfo();

	public Optional<TableInfoDTO> getTableInfo(String tableName);
	
	public void dropAllTable();
	
	public boolean deleteAllData(String tableName, List<Integer> ids);

	public boolean deleteAllData(String tableName);

}
