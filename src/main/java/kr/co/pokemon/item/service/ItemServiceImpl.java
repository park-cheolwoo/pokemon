package kr.co.pokemon.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.item.dao.ItemMapper;
import kr.co.pokemon.item.dto.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService {

	private final DBTables dbTable = DBTables.ITEM;
	
	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;

	@Override
	public List<ItemDTO> getAll(PageRequestDTO page) {
		return itemMapper.selectAll(page);
	}

	@Override
	public ItemDTO getById(int id) {
		return itemMapper.selectById(id);
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return itemMapper.selectByIds(ids);
	}

	@Override
	public List<ItemDTO> getByNickname(String keyword) {
		return itemMapper.selectByNickname(keyword);
	}

	@Override
	public int insertDataFromAPI(List<ItemDTO> list) throws Exception {
		List<ItemDTO> filtered = list.stream().filter(dto -> {
			int categoryId = APIService.getIdByUrl(dto.getCategory().getUrl());
			dto.setCategoryId(categoryId);
			
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			Optional.ofNullable(dto.getSprite().getDefaultSprite()).ifPresentOrElse(sprite -> dto.setImage(sprite), () -> dto.setImage("/images/no-item.png"));
			
			dto.getLanguagesEffect("en").ifPresentOrElse(description ->
			dto.setDescription(description),
			() -> dto.setDescription("NO-TEXT")
					);
			
			dto.getLanguagesText("ko").ifPresentOrElse(flavor ->
			dto.setFlavorText(flavor),
			() -> dto.setFlavorText("설명이 없습니다.")
					);
			return itemCategoryService.existById(categoryId);
		}).toList();
		
		System.out.println(filtered.size());
		
		if (filtered.size() > 0) {
			if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
				itemMapper.insertAll(filtered);				
			
			} else {
				throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}
		}

		return filtered.size();
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
	public void insert(ItemDTO dto) {
		itemMapper.insert(dto);
	}

	@Override
	public List<ItemDTO> getByCategoryId(int categoryId) {
		return itemMapper.selectByCategoryId(categoryId);
	}

	@Override
	public void UpdateItemBySystem(ItemDTO iDTO) {
		itemMapper.UpdateItemBySystem(iDTO);
	}
}
