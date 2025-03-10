package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.GrowthMapper;
import kr.co.pokemon.pokemon.dto.GrowthDTO;

@Service
public class GrowthServiceImpl implements GrowthService {
	
	private DBTables dbTable = DBTables.GROWTH;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private GrowthMapper growthMapper;

	@Override
	public int insertDataFromAPI(List<GrowthDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			dto.getLanguagesDescription("en").ifPresent(name -> dto.setName(name));
			
			dto.getLevels().stream().forEach(level -> level.setGrowthId(dto.getId()));
		});

		insert(list);
		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

	@Override
	public List<GrowthDTO> getAll(PageRequestDTO page) {
		return growthMapper.selectAll(page);
	}

	@Override
	public GrowthDTO getById(int id) {
		return growthMapper.selectById(id);
	}

	public void insert(List<GrowthDTO> list) {
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			growthMapper.insertAll(list);
			if (dataService.deleteAllData(DBTables.TOTAL_EXPERIENCE.getTableName())) {
				list.stream().forEach(dto -> {
					growthMapper.insertAllLevel(dto.getLevels());
				});
				
			} else {
				throw new IllegalArgumentException(DBTables.TOTAL_EXPERIENCE.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}
			
		} else {
			throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}
	}

	@Override
	public void insert(GrowthDTO dto) {
		growthMapper.insert(dto);
		growthMapper.insertAllLevel(dto.getLevels());
	}

}
