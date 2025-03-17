package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dao.IngameMapper;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.dto.UpdateIngameDTO;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;
import kr.co.pokemon.player.service.PlayerPokemonService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@Service
public class IngameServiceImpl implements IngameService {

	private DBTables dbTable = DBTables.INGAME;

	@Autowired
	private IngameMapper ingameMapper;
	
	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private GameStageService gameStageService;

	@Autowired
	private PlayerPokemonService playerPokemonService;
	
	@Autowired
	private IngamePokemonService ingamePokemonService;
	
	@Autowired
	private PlayService playService;
	
	@Override
	public List<IngameDTO> getAll(PageRequestDTO page) {
		return ingameMapper.selectAll(page);
	}

	@Override
	public IngameDTO getById(int id) {
		return null;
	}
	
	@Override
	public IngameDTO getByPlayerId(String playerId) {
		IngameDTO ingame = ingameMapper.selectById(playerId);
		ingame.setStage(gameStageService.getById(ingame.getStageId()));

		return ingame;
	}

	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return null;
	}

	@Override
	public void insert(IngameDTO dto) {
		ingameMapper.insertIngame(dto.getPlayerId());
	}
	
	@Override
	public boolean updateSelectionIdx(String playerId, int idx) {
		try {
			ingameMapper.updateSelectionIdx(new UpdateIngameDTO(playerId, idx));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateIngameStatus(String playerId, boolean isInGame) {
		try {
			ingameMapper.updateIngameStatus(new UpdateIngameDTO(playerId, isInGame? 1 : 0));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public IngameInfoDTO getIngameInfoByPlayerId(String playerId) {
		return new IngameInfoDTO(getByPlayerId(playerId), getIngamePokemons(playerId), getIngameEnemies(playerId));
	}
	
	@Override
	public List<CreatedPokemonDTO> getIngamePokemons(String playerId) {
		return ingamePokemonService.getIngamePokemons(playerId).stream().map(ingamePokemon -> {
			PlayerPokemonDTO playerPokemon = playerPokemonService.getById(ingamePokemon.getId());
			PokemonDTO pokemon = pokemonService.getById(playerPokemon.getPokemonId());
			if (pokemon == null) {
				return null;
			}
			pokemon.setSprites(pokemonService.getSpritesById(pokemon.getId()));

			return new CreatedPokemonDTO(
				pokemon, playerPokemon.getName(), playerPokemon.isGender(), playerPokemon.getLevel(), ingamePokemon.getHp(),
				playerPokemon.getCharacteristic(), playerPokemon.getAbilities(), playerPokemon.getAttacks(),
				playerPokemon.getStats(), playerPokemon.getTypes());
		}).toList();
	}
	
	@Override
	public List<CreatedPokemonDTO> getIngameEnemies(String playerId) {
		return ingamePokemonService.getIngameEnemies(playerId).stream().map(ingameEnemy -> {
			CreatedPokemonDTO generatedPokemon = playService.createPokemon(ingameEnemy.getPokemonId(), 0, 1);
			generatedPokemon.setLevel(ingameEnemy.getLevel());
			generatedPokemon.setHp(ingameEnemy.getHp());
			
			return generatedPokemon;
		}).toList();
	}
	
}
