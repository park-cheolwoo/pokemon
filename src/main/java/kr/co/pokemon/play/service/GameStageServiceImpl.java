package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dao.GameStageMapper;
import kr.co.pokemon.play.dto.GameStageDTO;

@Service
public class GameStageServiceImpl implements GameStageService {

	private DBTables dbTable = DBTables.GAME_STAGE;
	
	@Autowired
	private GameStageMapper gameStageMapper;

	@Override
	public List<GameStageDTO> getAll(PageRequestDTO page) {
		return gameStageMapper.selectAll(page);
	}

	@Override
	public GameStageDTO getById(int id) {
		return gameStageMapper.selectById(id);
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
