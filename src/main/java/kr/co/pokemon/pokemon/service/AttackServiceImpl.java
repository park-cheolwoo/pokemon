package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.AttackMapper;
import kr.co.pokemon.pokemon.dto.AttackDTO;

@Service
public class AttackServiceImpl implements AttackService {
	
	private final DBTables dbTable = DBTables.ATTACK;
	
	@Autowired
	private DataService dataService;

	@Autowired
	private AttackMapper attackMapper;

	@Autowired
	private TypesService typesService;

	@Override
	public List<AttackDTO> getAll(PageRequestDTO page) {
		List<AttackDTO> list = attackMapper.selectAll(page);
		list.forEach(attack -> attack.setTypes(typesService.getById(attack.getTypesId())));
		return list;
	}
	
	@Override
	public List<AttackDTO> getByTypeId(int typesId) {
		List<AttackDTO> list = attackMapper.selectByTypeId(typesId);
		list.forEach(attack -> attack.setTypes(typesService.getById(attack.getTypesId())));
		return list;
	}

	@Override
	public AttackDTO getById(int id) {
		AttackDTO attack = attackMapper.selectById(id);
		attack.setTypes(typesService.getById(attack.getTypesId()));
		return attack;
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return attackMapper.selectByIds(ids);
	}
	
	@Override
	public boolean existById(int id) {
		return attackMapper.existById(id) != 0;
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
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			attackMapper.insertAll(list);
			
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
	public void insert(AttackDTO dto) {
		attackMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
