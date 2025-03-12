package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.EvolutionTriggerMapper;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;

@Service
public class EvolutionTriggerServiceImpl implements EvolutionTriggerService {

	private final DBTables dbTable = DBTables.EVOLUTION_TRIGGER;
	
	@Autowired
	private DataService dataService;
	
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
	public List<Integer> getByIds(List<Integer> ids) {
		return evolutionTriggerMapper.selectByIds(ids);
	}

	@Override
	public boolean existById(int id) {
		return evolutionTriggerMapper.existById(id) != 0;
	}
	
	@Override
	public int insertDataFromAPI(List<EvolutionTriggerDTO> list) throws Exception {
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			evolutionTriggerMapper.insertAll(list);
			
		} else {
			throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}

		return list.size();
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
	public DBTables getDBTable() {
		return dbTable;
	}

}
