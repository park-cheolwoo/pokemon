package kr.co.pokemon.data.service;

import java.util.List;
import java.util.Optional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;

public interface DataService {

	<D, S extends APIGetable<D>> List<D> getAll(PageRequestDTO page, Class<S> serviceClass);
	
	<D, S extends APIGetable<D>> Optional<D> getById(int id, Class<S> serviceClass);

	public List<TableInfoDTO> getAllTableInfo();

	public Optional<TableInfoDTO> getTableInfo(String tableName);
	
	public boolean deleteAllData(String tableName);

}
