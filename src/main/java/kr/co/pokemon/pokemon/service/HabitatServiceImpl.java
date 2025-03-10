package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.HabitatMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonHabitatMapper;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonHabitatDTO;

@Service
public class HabitatServiceImpl implements HabitatService {
	
	private final DBTables dbTable = DBTables.HABITAT;

	@Autowired
	private DataService dataService;
	
	@Autowired
	private HabitatMapper habitatMapper;
	
	@Autowired
	private PokemonHabitatMapper pokemonHabitatMapper;
	
	@Override
	public List<HabitatDTO> getAll(PageRequestDTO page) {
		return habitatMapper.selectAll(page);
	}

	@Override
	public HabitatDTO getById(int id) {
		return habitatMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<HabitatDTO> list) throws Exception {
		List<PokemonHabitatDTO> pokemonHabitats = new ArrayList<>();

		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());			

			dto.getPokemonSpecies().stream().forEach(poke -> {
				int pokemonId = APIService.getIdByUrl(poke.getUrl());
				pokemonHabitats.add(new PokemonHabitatDTO(pokemonId, dto.getId()));
			});
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			habitatMapper.insertAll(list);
			if (dataService.deleteAllData(DBTables.POKEMON_HABITAT.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
				pokemonHabitatMapper.insertAll(pokemonHabitats);

			} else {
				throw new IllegalArgumentException(DBTables.POKEMON_HABITAT.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
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
	public void insert(HabitatDTO dto) {
		habitatMapper.insert(dto);	
	}

	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
