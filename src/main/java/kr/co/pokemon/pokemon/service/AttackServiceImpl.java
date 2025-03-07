package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dao.AttackMapper;
import kr.co.pokemon.pokemon.dto.AttackDTO;

@Service
public class AttackServiceImpl implements AttackService {
	
	private final DBTables dbTable = DBTables.ATTACK;

	@Autowired
	AttackMapper attackMapper;
	
	@Override
	public List<AttackDTO> getAll(PageRequestDTO page) {
		return attackMapper.selectAll(page);
	}
	
	@Override
	public List<AttackDTO> getByTypeId(int typesId) {
		return attackMapper.selectByTypeId(typesId);
	}

	@Override
	public AttackDTO getById(int id) {
		return attackMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<AttackDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			int typesId = APIService.getIdByUrl(dto.getType().getUrl());
			dto.setTypesId(typesId);
			
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			dto.getLanguagesEffect("en").ifPresentOrElse(effect ->
				dto.setDescription(effect),
				() -> dto.setDescription("NO-TEXT")
			);
			
			dto.getLanguagesFlavorText("ko").ifPresentOrElse(flavor ->
				dto.setFlavorText(flavor),
				() -> dto.setFlavorText("NO-TEXT")
			);
			
		});
		attackMapper.insertAll(list);

		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(AttackDTO dto) {
		attackMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
