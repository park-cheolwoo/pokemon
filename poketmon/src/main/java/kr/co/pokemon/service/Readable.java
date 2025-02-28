package kr.co.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.dto.PageDTO;

public interface Readable<D> {

	List<D> getAll(PageDTO page);
	D getById(int id);

}
