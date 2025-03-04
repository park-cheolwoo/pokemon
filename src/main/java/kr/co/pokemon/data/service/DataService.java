package kr.co.pokemon.data.service;

import java.util.List;
import java.util.Optional;

import kr.co.pokemon.data.dto.PageDTO;

public interface DataService {

	<D, S extends APIGetable<D>> List<D> getAll(PageDTO page, Class<S> serviceClass);
	
	<D, S extends APIGetable<D>> Optional<D> getById(int id, Class<S> serviceClass);

}
