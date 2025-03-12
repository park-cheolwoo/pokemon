package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.StatMapper;
import kr.co.pokemon.pokemon.dto.StatDTO;

@Service
public class StatServiceImpl implements StatService {
	
	private final DBTables dbTable = DBTables.STAT;

	@Autowired
	private DataService dataService;
	
	@Autowired
	private StatMapper statMapper;
	
	@Override
	public List<StatDTO> getAll(PageRequestDTO page) {
		return statMapper.selectAll(page);
	}

	@Override
	public StatDTO getById(int id) {
		return statMapper.selectById(id);
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return statMapper.selectByIds(ids);
	}
	
	@Override
	public boolean existById(int id) {
		return statMapper.existById(id) != 0;
	}

	@Override
	public int insertDataFromAPI(List<StatDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));			
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			statMapper.insertAll(list);
			
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
	public void insert(StatDTO dto) {
		statMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
