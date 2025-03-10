package kr.co.pokemon.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.item.dao.ItemCategoryMapper;
import kr.co.pokemon.item.dto.ItemCategoryDTO;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	private final DBTables dbTable = DBTables.ITEM_CATEGORY;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	ItemCategoryMapper itemCategoryMapper;
	
	@Override
	public int insertDataFromAPI(List<ItemCategoryDTO> list) throws Exception {
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			itemCategoryMapper.insertAll(list);
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
	public DBTables getDBTable() {
		return dbTable;
	}

	@Override
	public List<ItemCategoryDTO> getAll(PageRequestDTO page) {
		return itemCategoryMapper.selectAll(page);
	}

	@Override
	public ItemCategoryDTO getById(int id) {
		return itemCategoryMapper.selectById(id);
	}

	@Override
	public void insert(ItemCategoryDTO dto) {
		itemCategoryMapper.insert(dto);
	}

}
