package kr.co.pokemon.item.service;

import java.util.List;

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

	@Override
	public List<ItemDTO> getAll(PageRequestDTO page) {
		return itemMapper.selectAll(page);
	}

	@Override
	public ItemDTO getById(int id) {
		return itemMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<ItemDTO> list) throws Exception {
		int successCount = 0;
		
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			for (ItemDTO dto : list) {
				try {
					// 한국어 이름이 없으면 건너뛰기
					if (!dto.getLanguagesName("ko").isPresent()) {
						System.out.println("아이템 [" + dto.getId() + "] 한국어 이름 없음");
						continue;
					}
					
					// 한국어 설명이 없으면 건너뛰기
					if (!dto.getLanguagesDescription("ko").isPresent()) {
						System.out.println("아이템 [" + dto.getId() + "] 한국어 설명 없음");
						continue;
					}
					
					if (dto.getCategory() == null || dto.getCategory().getUrl() == null) {
						System.out.println("아이템 [" + dto.getId() + "] 아이템 타입 또는 URL이 없음");
						continue;
					}
					
					int categoryId = APIService.getIdByUrl(dto.getCategory().getUrl());
					dto.setCategoryId(categoryId);
					
					// 한국어 이름 설정
					dto.setName(dto.getLanguagesName("ko").get());
					
					// 한국어 설명 설정
					dto.setDescription(dto.getLanguagesDescription("ko").get());
					
					// 이미지 URL 설정
					String imageUrl = "";
					if (dto.getSprite() != null && dto.getSprite().getDefaultSprite() != null && !dto.getSprite().getDefaultSprite().isEmpty()) {
						imageUrl = dto.getSprite().getDefaultSprite();
					} else {
						// 이미지가 없는 경우 기본 이미지 URL 설정
						imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/unknown.png";
					}
					
					// null이 아닌 값으로 확실하게 설정
					dto.setImage(imageUrl);
					
					itemMapper.insert(dto);
					System.out.println("아이템 [" + dto.getId() + "] " + dto.getName() + " 저장 성공");
					successCount++;
				} catch (Exception e) {
					System.out.println("아이템 [" + dto.getId() + "] 처리 중 오류: " + e.getMessage());
					e.printStackTrace();
				}
			}
			return successCount;
		} else {
			throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}
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
}
