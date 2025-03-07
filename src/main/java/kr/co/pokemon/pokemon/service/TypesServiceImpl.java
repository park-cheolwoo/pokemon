package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.pokemon.dao.TypesMapper;
import kr.co.pokemon.pokemon.dto.TypesDTO;

@Service
public class TypesServiceImpl implements TypesService {
	
	private final DBTables dbTable = DBTables.TYPES;

	@Autowired
	private TypesMapper typesMapper;
	
	@Override
	public List<TypesDTO> getAll(PageRequestDTO page) {
		return typesMapper.selectAll(page);
	}

	@Override
	public TypesDTO getById(int id) {
		return typesMapper.selectById(id);
	}

	public TypesDTO getByName(String originalName) {
		return typesMapper.selectByName(originalName);
	}

	@Override
	public int insertDataFromAPI(List<TypesDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			
		});
		typesMapper.insertAll(list);

		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(TypesDTO dto) {
		typesMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
