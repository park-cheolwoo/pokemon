package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dao.GameStageMapper;
import kr.co.pokemon.play.dto.GameStageDTO;
import kr.co.pokemon.pokemon.service.HabitatService;

@Service
public class GameStageServiceImpl implements GameStageService {

	private DBTables dbTable = DBTables.GAME_STAGE;
	
	@Autowired
	private GameStageMapper gameStageMapper;
	
	@Autowired
	private HabitatService habitatService;

	@Override
	public List<GameStageDTO> getAll(PageRequestDTO page) {
		List<GameStageDTO> list = gameStageMapper.selectAll(page);
		list.forEach(habitat -> habitat.setHabitat(habitatService.getById(habitat.getHabitatId())));

		return list;
	}

	@Override
	public GameStageDTO getById(int id) {
		GameStageDTO gameStage = gameStageMapper.selectById(id);
		gameStage.setHabitat(habitatService.getById(gameStage.getHabitatId()));
		
		return gameStage;
	}

	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return null;
	}

	@Override
	public void insert(GameStageDTO dto) {
		gameStageMapper.insert(dto);
	}
	
	
}