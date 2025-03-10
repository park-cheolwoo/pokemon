package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.CharacteristicMapper;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

	private final DBTables dbTable = DBTables.CHARACTERISTIC;
	
	@Autowired
	private DataService dataService;
	
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
	public int insertDataFromAPI(List<CharacteristicDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			int statId = APIService.getIdByUrl(dto.getHighestStat().getUrl());
			dto.setStatId(statId);
			
			dto.getLanguagesDescription("ko").ifPresentOrElse(description ->
			dto.setDescription(description),
			() -> dto.setDescription("NO-TEXT")
					);			
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			characteristicMapper.insertAll(list);
			
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
	public void insert(CharacteristicDTO dto) {
		characteristicMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
