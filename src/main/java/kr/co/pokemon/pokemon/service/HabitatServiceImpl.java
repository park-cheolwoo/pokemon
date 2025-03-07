package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dao.HabitatMapper;
import kr.co.pokemon.pokemon.dto.HabitatDTO;

@Service
public class HabitatServiceImpl implements HabitatService {
	
	private final DBTables dbTable = DBTables.HABITAT;

	@Autowired
	HabitatMapper habitatMapper;
	
	@Override
	public List<HabitatDTO> getAll(PageRequestDTO page) {
		return habitatMapper.selectAll(page);
	}

	@Override
	public HabitatDTO getById(int id) {
		return habitatMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<HabitatDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());			
		});
		habitatMapper.insertAll(list);
		
		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(HabitatDTO dto) {
		habitatMapper.insert(dto);	
	}

	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
