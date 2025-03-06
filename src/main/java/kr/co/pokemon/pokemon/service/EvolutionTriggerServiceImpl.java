package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dao.EvolutionTriggerMapper;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Service
public class EvolutionTriggerServiceImpl implements EvolutionTriggerService {

	@Autowired
	private EvolutionTriggerMapper evolutionTriggerMapper;

	@Override
	public List<EvolutionTriggerDTO> getAll(PageRequestDTO page) {
		return evolutionTriggerMapper.selectAll(page);
	}

	@Override
	public EvolutionTriggerDTO getById(int id) {
		return evolutionTriggerMapper.selectById(id);
	}

	@Override
	public int getDataFromAPI(EvolutionTriggerDTO dto) throws Exception {
		try {
			if (evolutionTriggerMapper.existById(dto.getId()) == 0) {
				evolutionTriggerMapper.insert((EvolutionTriggerDTO) dto);
				
				return 1;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
	}

}
