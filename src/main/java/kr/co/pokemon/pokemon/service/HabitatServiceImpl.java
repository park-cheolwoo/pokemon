package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.play.dao.GameStageMapper;
import kr.co.pokemon.play.dto.GameStageDTO;
import kr.co.pokemon.pokemon.dao.HabitatMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonHabitatMapper;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonHabitatDTO;

@Service
public class HabitatServiceImpl implements HabitatService {
	
	private final DBTables dbTable = DBTables.HABITAT;
	
	@Value("${poketmon.stage-count}")
	private int stageCount;
	
	@Value("${poketmon.stage-gold-increment}")
	private int stageGoldIncrement;
	
	@Value("${poketmon.stage-exp-increment}")
	private int stageExpIncrement;
	
	@Value("${poketmon.stage-level-increment}")
	private int stageLevelIncrement;

	@Autowired
	private DataService dataService;
	
	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private HabitatMapper habitatMapper;
	
	@Autowired
	private GameStageMapper gameStageMapper;
	
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
	public List<Integer> getByIds(List<Integer> ids) {
		return habitatMapper.selectByIds(ids);
	}

	@Override
	public int insertDataFromAPI(List<HabitatDTO> list) throws Exception {
		List<PokemonHabitatDTO> pokemonHabitats = new ArrayList<>();
		List<GameStageDTO> gameStages = new ArrayList<>();

		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			
			dto.getPokemonSpecies().stream().forEach(poke -> {
				int pokemonId = APIService.getIdByUrl(poke.getUrl());
				
				if (pokemonService.existById(pokemonId)) {
					pokemonHabitats.add(new PokemonHabitatDTO(pokemonId, dto.getId()));
					
				}
			});
			
			for (int i = 0; i < stageCount; i++) {
				gameStages.add(new GameStageDTO(dto.getId(), i + 1, (stageGoldIncrement * i) + (dto.getId() * 10), (stageExpIncrement * i) + (dto.getId() * 10), (dto.getId() - 1) * stageLevelIncrement + (i * 5), (dto.getId() - 1) * stageLevelIncrement + (i * 5) + 5, dto));
			}
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			habitatMapper.insertAll(list);
			if (dataService.deleteAllData(DBTables.POKEMON_HABITAT.getTableName())) {
				pokemonHabitatMapper.insertAll(pokemonHabitats);
				if (dataService.deleteAllData(DBTables.GAME_STAGE.getTableName())) {
					if (dataService.recreateSequence(DBTables.GAME_STAGE.getTableName())) {
						gameStageMapper.insertAll(gameStages);
					}
				}

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
	
	@Override
	public List<HabitatDTO> getHabitatByPokemonId(int pokemonId) {
		return pokemonHabitatMapper.selectHabitatByPokemonId(pokemonId);
	}

	@Override
	public List<PokemonDTO> getPokemonByHabitatId(int habitatId) {
		return pokemonHabitatMapper.selectPokemonByHabitatId(habitatId);
	}

}
