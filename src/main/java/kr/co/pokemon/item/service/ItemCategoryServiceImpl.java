package kr.co.pokemon.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.item.dao.ItemCategoryMapper;
import kr.co.pokemon.item.dto.ItemCategoryDTO;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	private final DBTables dbTable = DBTables.ITEM_CATEGORY;
	
	@Autowired
	ItemCategoryMapper itemCategoryMapper;
	
	@Override
	public int getDataFromAPI(ItemCategoryDTO dto) throws Exception {
		itemCategoryMapper.insert((ItemCategoryDTO) dto);
		return 1;
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public String getDBTableName() {
		return dbTable.getTableName();
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
