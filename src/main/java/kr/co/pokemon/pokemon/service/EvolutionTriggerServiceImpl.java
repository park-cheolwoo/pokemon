package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dao.EvolutionTriggerMapper;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Service
public class EvolutionTriggerServiceImpl implements EvolutionTriggerService {

	private final DBTables dbTable = DBTables.EVOLUTION_TRIGGER;
	
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
		evolutionTriggerMapper.insert((EvolutionTriggerDTO) dto);
		
		return 1;
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(EvolutionTriggerDTO dto) {
		evolutionTriggerMapper.insert(dto);
	}
	
	@Override
	public String getDBTableName() {
		return dbTable.getTableName();
	}

}
