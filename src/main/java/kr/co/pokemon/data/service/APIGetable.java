package kr.co.pokemon.data.service;

import java.util.List;

import kr.co.pokemon.data.model.DBTables;

public interface APIGetable<D> extends Getable<D> {

	int insertDataFromAPI(List<D> list) throws Exception;
	
	List<DBTables> getDependencies();
	
	DBTables getDBTable();

}
