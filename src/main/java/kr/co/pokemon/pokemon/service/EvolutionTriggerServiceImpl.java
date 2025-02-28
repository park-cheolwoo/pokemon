package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dao.EvolutionTriggerMapper;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Service
public class EvolutionTriggerServiceImpl implements EvolutionTriggerService {

	@Autowired
	private EvolutionTriggerMapper evolutionTriggerMapper;
	
	@Override
	public <T> void getDataFromAPI(T dto) throws Exception {
		EvolutionTriggerDTO castedDTO = (EvolutionTriggerDTO) dto;
		if (evolutionTriggerMapper.existById(castedDTO.getId()) == 0) {
			evolutionTriggerMapper.insert((EvolutionTriggerDTO) dto);
		}
	}

	@Override
	public List<EvolutionTriggerDTO> getAll(PageDTO page) {
		return evolutionTriggerMapper.selectAll(page);
	}

	@Override
	public EvolutionTriggerDTO getById(int id) {
		return evolutionTriggerMapper.selectById(id);
	}

}
