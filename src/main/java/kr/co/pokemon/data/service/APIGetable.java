package kr.co.pokemon.data.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

public interface APIGetable<D> extends Getable<D> {

	@Transactional
	int insertDataFromAPI(List<D> list) throws Exception;
	
	List<DBTables> getDependencies();
	
	DBTables getDBTable();


}
