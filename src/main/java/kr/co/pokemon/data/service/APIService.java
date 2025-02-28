package kr.co.pokemon.data.service;

import java.sql.SQLException;

public interface APIService {

	void initTable() throws SQLException;
	
	public <S extends APIGetable> boolean setData(String uri, Class<?> dto, S service);

}
