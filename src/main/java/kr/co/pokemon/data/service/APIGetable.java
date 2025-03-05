package kr.co.pokemon.data.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageDTO;

public interface APIGetable<D> {

	List<D> getAll(PageDTO page);
	D getById(int id);

	void getDataFromAPI(D dto) throws Exception;

}
