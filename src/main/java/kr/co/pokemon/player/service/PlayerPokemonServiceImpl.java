package kr.co.pokemon.player.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.player.dao.OwnPokemonSkillMapper;
import kr.co.pokemon.player.dao.OwnPokemonStatMapper;
import kr.co.pokemon.player.dao.PlayerPokemonMapper;
import kr.co.pokemon.player.dto.OwnPokemonSkill;
import kr.co.pokemon.player.dto.OwnPokemonStat;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.CharacteristicService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;

@Service
public class PlayerPokemonServiceImpl implements PlayerPokemonService {

	@Autowired
	private PlayerPokemonMapper playerPokemonMapper;
	
	@Autowired
	private OwnPokemonSkillMapper ownPokemonSkillMapper;
	
	@Autowired
	private OwnPokemonStatMapper ownPokemonStatMapper;
	
	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private CharacteristicService characteristicService;
	
	@Autowired
	private AbilityService abilityService;
	
	@Autowired
	private PokemonMoveService pokemonMoveService;
	
	@Override
	public PlayerPokemonDTO getById(int id) {
		return playerPokemonMapper.selectById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PlayerPokemonDTO> getByPlayerId(String playerId) {
		List<PlayerPokemonDTO> playerPokemons = playerPokemonMapper.selectByPlayerId(playerId);
		playerPokemons.stream().forEach(playerPokemon -> {
			playerPokemon.setAbilities(ownPokemonSkillMapper.selectAbilityByOwnPokemonId(playerPokemon.getId()));
			playerPokemon.setAttacks(ownPokemonSkillMapper.selectAttackByOwnPokemonId(playerPokemon.getId()));
			playerPokemon.setStats(ownPokemonStatMapper.selectStatByOwnPokemonId(playerPokemon.getId()));
			playerPokemon.setCharacteristic(characteristicService.getById(playerPokemon.getId()));
		});
		return playerPokemons;
	}

	@Override
	public int getIdByPokemonIdAndPlayerId(int pokemonId, int playerId) {
		Map<String, Integer> idMap = new HashMap<>();
		idMap.put("pokemonId", pokemonId);
		idMap.put("playerId", playerId);
		
		return playerPokemonMapper.selectIdByPokemonIdAndPlayerId(idMap).orElse(0);
	}

	@Override
	@Transactional
	public void save(PlayerPokemonDTO playerPokemon) {
		if (playerPokemon.getCharacteristicId() == 0) {
			playerPokemon.setCharacteristicId(1);
		}

		playerPokemonMapper.insert(playerPokemon);

		Random random = new Random();
		
		final OwnPokemonStat maxValueStat = new OwnPokemonStat(playerPokemon.getId(), 0, 0, 0);
		List<PokemonOwnStat> ownStats = playerPokemon.getStats();
		pokemonService.getStatsByPokemonId(playerPokemon.getPokemonId()).stream().forEach(stat -> {
			OwnPokemonStat ownStat;
			if (ownStats != null) {
				ownStat = ownStats.stream().filter(s -> s.getId() == stat.getStatId())
						.findFirst().map(s -> new OwnPokemonStat(playerPokemon.getId(), s.getId(), s.getValue() < stat.getValue()? s.getValue() : stat.getValue(), stat.getValue()))
						.orElse(new OwnPokemonStat(playerPokemon.getId(), stat.getStatId(), stat.getValue() - random.nextInt(15), stat.getValue()));
			} else {
				ownStat = new OwnPokemonStat(playerPokemon.getId(), stat.getStatId(), stat.getValue() - random.nextInt(15), stat.getValue());
			}
			
			if (ownStat.getValue() > maxValueStat.getValue()) {
				maxValueStat.setStatId(ownStat.getStatId());
				maxValueStat.setValue(ownStat.getValue());
			}
			
			ownPokemonStatMapper.insert(ownStat);
		});
		
		List<CharacteristicDTO> characteristics = characteristicService.getCharacteristicsByStatId(maxValueStat.getStatId());
		Map<String, Integer> updateCharacteristic = new HashMap<>();
		updateCharacteristic.put("id", playerPokemon.getId());
		updateCharacteristic.put("characteristicId", characteristics.get(random.nextInt(characteristics.size())).getId());

		playerPokemonMapper.updateNameByCharacteristicById(updateCharacteristic);
		
		if (playerPokemon.getAbilities() != null) {
			saveAbilities(playerPokemon.getId(), playerPokemon.getPokemonId(), playerPokemon.getAbilities().stream().map(PokemonOwnAbility::getId).toList());
		}
		
		if (playerPokemon.getAttacks() != null) {
			saveAttacks(playerPokemon.getId(), playerPokemon.getPokemonId(), playerPokemon.getAttacks().stream().map(PokemonOwnAttack::getId).toList());
		}

	}
	
	private void saveAbilities(int playerPokemonId, int pokemonId, List<Integer> abilityIds) {
		abilityIds.stream().forEach(abilityId -> {
			if (abilityService.existAbilityAndPokemonId(pokemonId, abilityId)) {
				savePokemonAbility(new OwnPokemonSkill(playerPokemonId, abilityId, 0));
			}
		});
	}
	
	private void saveAttacks(int playerPokemonId, int pokemonId, List<Integer> attackIds) {
		attackIds.stream().forEach(attackId -> {
			if (pokemonMoveService.existAttackAndPokemonId(pokemonId, attackId)) {
				savePokemonAttack(new OwnPokemonSkill(playerPokemonId, attackId, 0));
			}
		});
	}

	@Override
	public void updateName(int id, String name) {
		Map<String, String> updateNameMap = new HashMap<>();
		updateNameMap.put("id", id + "");
		updateNameMap.put("name", name);
		
		playerPokemonMapper.updateNameById(updateNameMap);
	}

	@Override
	public void updateLevel(int id, int level) {
		Map<String, Integer> updateLevelMap = new HashMap<>();
		updateLevelMap.put("id", id);
		updateLevelMap.put("level", level);

		playerPokemonMapper.updatLevelById(updateLevelMap);
	}

	@Override
	public void updateExperience(int id, int experience) {
		Map<String, Integer> updateExperienceMap = new HashMap<>();
		updateExperienceMap.put("id", id);
		updateExperienceMap.put("experience", experience);
		
		playerPokemonMapper.updatLevelById(updateExperienceMap);
	}
	
	@Override
	public void savePokemonAbility(OwnPokemonSkill skill) {
		ownPokemonSkillMapper.insertAbility(skill);
	}
	
	@Override
	public void savePokemonAttack(OwnPokemonSkill skill) {
		ownPokemonSkillMapper.insertAttack(skill);
	}
	
	@Override
	public void savePokemonStat(OwnPokemonStat stat) {
		ownPokemonStatMapper.insert(stat);
	}
	
	@Override
	public List<PokemonOwnAbility> getAbilitiesByOwnPokemonId(int ownPokemonId) {
		return ownPokemonSkillMapper.selectAbilityByOwnPokemonId(ownPokemonId);
	}
	
	@Override
	public List<PokemonOwnAttack> getAttacksByOwnPokemonId(int ownPokemonId) {
		return ownPokemonSkillMapper.selectAttackByOwnPokemonId(ownPokemonId);
	}
	
	@Override
	public List<PokemonOwnStat> getStatByOwnPokemonId(int ownPokemonId) {
		return ownPokemonStatMapper.selectStatByOwnPokemonId(ownPokemonId);
	}

}
