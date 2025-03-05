package kr.co.pokemon.data.service;

import java.sql.SQLException;

public interface APIService {

	void initTable() throws SQLException;
	
	public <D, S extends APIGetable<D>> boolean setData(String uri, Class<S> service);

}
