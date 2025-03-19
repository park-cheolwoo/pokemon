package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dao.IngameMapper;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.dto.UpdateHpPokemonDTO;
import kr.co.pokemon.play.dto.UpdateIngameDTO;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;
import kr.co.pokemon.player.service.PlayerPokemonService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

import java.util.ArrayList;

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
	@Transactional
	public boolean updateIngameStage(String playerId, int stage) {
		try {
			ingameMapper.updateStageId(new UpdateIngameDTO(playerId, stage));
			playService.saveIngameEnemyByStageId(playerId, stage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateIngameMaxStage(String playerId, int stage) {
		try {
			ingameMapper.updateMaxStageId(new UpdateIngameDTO(playerId, stage));
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

			return new CreatedPokemonDTO(ingamePokemon.getId(),
				pokemon, playerPokemon.getName(), playerPokemon.isGender(), playerPokemon.getLevel(), ingamePokemon.getHp(),
				playerPokemon.getCharacteristic(), playerPokemon.getAbilities(), playerPokemon.getAttacks(),
				playerPokemon.getStats(), playerPokemon.getTypes());
		}).toList();
	}
	
	@Override
	public List<CreatedPokemonDTO> getIngameEnemies(String playerId) {
		return ingamePokemonService.getIngameEnemies(playerId).stream().map(ingameEnemy -> {
			CreatedPokemonDTO generatedPokemon = playService.createPokemon(ingameEnemy.getPokemonId(), 0, 1);
			generatedPokemon.setId(ingameEnemy.getId());
			generatedPokemon.setLevel(ingameEnemy.getLevel());
			generatedPokemon.setHp(ingameEnemy.getHp());
			
			return generatedPokemon;
		}).toList();
	}
	
	@Override
	public void resetIngamePokemon(String playerId) {
		ingamePokemonService.getIngamePokemons(playerId).forEach(ingamePokemon -> {
			int hp = playerPokemonService.getById(ingamePokemon.getId()).getStats().stream().filter(stat -> stat.getId() == 1).findFirst().get().getValue();
			ingamePokemonService.updateIngamePokemonHp(new UpdateHpPokemonDTO(ingamePokemon.getId(), hp));
			ingamePokemonService.deleteEnemies(playerId);
		});
	}
	
	@Override
	public List<IngamePokemonDTO> getExpeditionByPlayerId(String playerId) {
		try {
			// 플레이어의 인게임 포켓몬 중 원정대에 배치된 포켓몬을 가져옴
			return ingamePokemonService.getIngamePokemons(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	@Override
	public boolean saveExpeditionList(String playerId, List<Integer> expeditionPokemonIds) {
		try {
			// 원정대 포켓몬 목록을 인게임 포켓몬 테이블에 저장
			List<IngamePokemonDTO> pokemons = new ArrayList<>();
			
			// 기존 인게임 포켓몬 정보 가져오기
			List<IngamePokemonDTO> existingPokemons = ingamePokemonService.getIngamePokemons(playerId);
			
			// 원정대에 추가할 포켓몬 ID 목록으로 포켓몬 정보 설정
			for (int i = 0; i < expeditionPokemonIds.size(); i++) {
				Integer pokemonId = expeditionPokemonIds.get(i);
				
				// 기존 포켓몬 중에서 해당 ID를 가진 포켓몬 찾기
				IngamePokemonDTO existingPokemon = existingPokemons.stream()
						.filter(p -> p.getId() == pokemonId)
						.findFirst()
						.orElse(null);
				
				if (existingPokemon != null) {
					// 기존 포켓몬이 있으면 슬롯 업데이트
					existingPokemon.setSlot(i);
					pokemons.add(existingPokemon);
				} else {
					// 새로운 포켓몬 생성
					IngamePokemonDTO pokemon = new IngamePokemonDTO();
					pokemon.setId(pokemonId);
					pokemon.setPlayerId(playerId);
					pokemon.setHp(100); // 기본 HP 설정
					pokemon.setSlot(i); // 슬롯 설정
					pokemons.add(pokemon);
				}
			}
			
			// 포켓몬 목록 저장
			return ingamePokemonService.saveIngamePokemons(pokemons);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}