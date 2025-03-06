package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dao.CharacteristicMapper;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

	private final DBTables dbTable = DBTables.CHARACTERISTIC;
	
	@Autowired
	private CharacteristicMapper characteristicMapper;
	
	@Override
	public List<CharacteristicDTO> getAll(PageRequestDTO page) {
		return characteristicMapper.selectAll(page);
	}

	@Override
	public CharacteristicDTO getById(int id) {
		return characteristicMapper.selectById(id);
	}

	@Override
	public int getDataFromAPI(CharacteristicDTO dto) throws Exception {
		int statId = APIService.getIdByUrl(dto.getHighestStat().getUrl());
		dto.setStatId(statId);
		
		dto.getLanguagesDescription("ko").ifPresentOrElse(description ->
			dto.setDescription(description),
			() -> dto.setDescription("NO-TEXT")
		);
		characteristicMapper.insert(dto);
		
		return 1;
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(CharacteristicDTO dto) {
		characteristicMapper.insert(dto);
	}
	
	@Override
	public String getDBTableName() {
		return dbTable.getTableName();
	}

}
