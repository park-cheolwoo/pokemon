package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dao.StatMapper;
import kr.co.pokemon.pokemon.dto.StatDTO;

@Service
public class StatServiceImpl implements StatService {
	
	private final DBTables dbTable = DBTables.STAT;

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
	public int getDataFromAPI(StatDTO dto) throws Exception {
		dto.setOriginalName(dto.getName());
		dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
		statMapper.insert(dto);

		return 1;
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
	public String getDBTableName() {
		return dbTable.getTableName();
	}

}
