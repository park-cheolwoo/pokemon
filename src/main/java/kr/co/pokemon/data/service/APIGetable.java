package kr.co.pokemon.data.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageRequestDTO;

public interface APIGetable<D> {

	List<D> getAll(PageRequestDTO page);
	D getById(int id);

	int getDataFromAPI(D dto) throws Exception;

}
