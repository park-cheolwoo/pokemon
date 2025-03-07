package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dao.EggGroupMapper;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;

@Service
public class EggGroupServiceImpl implements EggGroupService {
	
	private final DBTables dbTable = DBTables.EGG_GROUP;
	
	@Autowired
	private EggGroupMapper eggGroupMapper;

	@Override
	public List<EggGroupDTO> getAll(PageRequestDTO page) {
		return eggGroupMapper.selectAll(page);
	}

	@Override
	public EggGroupDTO getById(int id) {
		return eggGroupMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<EggGroupDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
		});
		eggGroupMapper.insertAll(list);
		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(EggGroupDTO dto) {
		eggGroupMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
