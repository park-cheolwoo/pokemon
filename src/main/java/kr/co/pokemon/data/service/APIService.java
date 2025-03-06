package kr.co.pokemon.data.service;

import java.sql.SQLException;

import kr.co.pokemon.data.dto.APIResponseDTO;

public interface APIService {

	void initTable() throws SQLException;
	
	public <D, S extends APIGetable<D>> APIResponseDTO setData(String uri, Class<S> service);

}
