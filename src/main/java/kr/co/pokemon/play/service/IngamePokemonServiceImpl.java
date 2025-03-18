package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.play.dao.IngameEnemyMapper;
import kr.co.pokemon.play.dao.IngamePokemonMapper;
import kr.co.pokemon.play.dto.IngameEnemyDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;

@Service
public class IngamePokemonServiceImpl implements IngamePokemonService {

	private DBTables dbTable = DBTables.INGAME_POKEMON;
	
	@Autowired
	private IngamePokemonMapper ingamePokemonMapper;
	
	@Autowired
	private IngameEnemyMapper ingameEnemyMapper;
	
	@Autowired
	private DataService dataService;

	@Override
	public List<IngamePokemonDTO> getAll(PageRequestDTO page) {
		return ingamePokemonMapper.selectAll(page);
	}

	@Override
	public IngamePokemonDTO getById(int id) {
		return ingamePokemonMapper.selectById(id);
	}

	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return null;
	}

	@Override
	public void insert(IngamePokemonDTO dto) {
		dataService.deleteAllData(dbTable.getTableName(), List.of(dto.getId()));
		ingamePokemonMapper.insert(dto);
	}

	@Override
	public List<IngamePokemonDTO> getIngamePokemons(String playerId) {
		return ingamePokemonMapper.selectByPlayerId(playerId);
	}
	
	@Override
	public List<IngameEnemyDTO> getIngameEnemies(String playerId) {
		return ingameEnemyMapper.selectByPlayerId(playerId);
	}

	@Override
	public boolean saveIngamePokemons(List<IngamePokemonDTO> ownPokemons) {
		try {
			if (ownPokemons.size() < 1 || 6 < ownPokemons.size()) {
				throw new IllegalArgumentException("1개 미만 또는 6개 이상 저장할 수 없습니다.");
			}
			ingamePokemonMapper.deleteByPlayerId(ownPokemons.get(0).getPlayerId());
			ingamePokemonMapper.insertAll(ownPokemons);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean saveIngameEnemies(List<IngameEnemyDTO> enemies) {
		try {
			if (enemies.size() < 1 || 6 < enemies.size()) {
				throw new IllegalArgumentException("1개 미만 또는 6개 이상 저장할 수 없습니다.");
			}
			ingameEnemyMapper.deleteByPlayerId(enemies.get(0).getPlayerId());
			ingameEnemyMapper.insertAll(enemies);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void updateIngamePokemonHp(int hp) {
		if (hp >= 0) {
			ingamePokemonMapper.updateHp(hp);
		}
	}
	
	@Override
	public void updateIngameEnemyHp(int hp) {
		if (hp >= 0) {
			ingameEnemyMapper.updateHp(hp);
		}
	}

}
