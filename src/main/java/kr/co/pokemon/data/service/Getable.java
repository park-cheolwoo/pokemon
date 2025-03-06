package kr.co.pokemon.data.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageRequestDTO;

public interface Getable<D> {
	
	List<D> getAll(PageRequestDTO page);
	D getById(int id);
	
	void insert(D dto);

}
