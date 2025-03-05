package kr.co.pokemon.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageDTO;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public <D, S extends APIGetable<D>> List<D> getAll(PageDTO page, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return service.getAll(page);
	}

	@Override
	public <D, S extends APIGetable<D>> Optional<D> getById(int id, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return Optional.ofNullable(service.getById(id));
	}

}
