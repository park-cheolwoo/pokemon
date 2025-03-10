package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.EggGroupMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonEggGroupMapper;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonEggGroupDTO;

@Service
public class EggGroupServiceImpl implements EggGroupService {
	
	private final DBTables dbTable = DBTables.EGG_GROUP;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private EggGroupMapper eggGroupMapper;
	
	@Autowired
	private PokemonEggGroupMapper pokemonEggGroupMapper;

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
		List<PokemonEggGroupDTO> pokemonEggGroups = new ArrayList<>();
		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			
			dto.getPokemonSpecies().stream().forEach(poke -> {
				int pokemonId = APIService.getIdByUrl(poke.getUrl());
				pokemonEggGroups.add(new PokemonEggGroupDTO(pokemonId, dto.getId()));
			});
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			eggGroupMapper.insertAll(list);
			if (dataService.deleteAllData(DBTables.EGG_GROUP_POKEMON.getTableName())) {
				pokemonEggGroupMapper.insertAll(pokemonEggGroups);
				
			} else {
				throw new IllegalArgumentException(DBTables.EGG_GROUP_POKEMON.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}

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
	public void insert(EggGroupDTO dto) {
		eggGroupMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
