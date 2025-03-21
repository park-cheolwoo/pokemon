package kr.co.pokemon.player.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.play.service.IngamePokemonService;
import kr.co.pokemon.player.dao.OwnPokemonSkillMapper;
import kr.co.pokemon.player.dao.OwnPokemonStatMapper;
import kr.co.pokemon.player.dao.PlayerPokemonMapper;
import kr.co.pokemon.player.dto.OwnPokemonSkill;
import kr.co.pokemon.player.dto.OwnPokemonStat;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.CharacteristicService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;
import kr.co.pokemon.pokemon.service.TypesService;

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

	@Autowired
	private TypesService typesService;
	
	@Autowired 
	private IngamePokemonService ingamePokemonService;
	

	Random random = new Random();

	@Override
	public PlayerPokemonDTO getById(int id) {
		PlayerPokemonDTO playerPokemon = playerPokemonMapper.selectById(id);
		List<PokemonOwnAttack> attacks = ownPokemonSkillMapper.selectAttackByOwnPokemonId(playerPokemon.getId());
		attacks.forEach(attack -> attack.setTypes(typesService.getById(attack.getTypesId())));

		playerPokemon.setAbilities(ownPokemonSkillMapper.selectAbilityByOwnPokemonId(playerPokemon.getId()));
		playerPokemon.setAttacks(attacks);
		playerPokemon.setStats(ownPokemonStatMapper.selectStatByOwnPokemonId(playerPokemon.getId()));
		playerPokemon.setCharacteristic(characteristicService.getById(playerPokemon.getId()));
		playerPokemon.setTypes(typesService.getTypesByPokemonId(playerPokemon.getPokemonId()));

		return playerPokemon;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PlayerPokemonDTO> getByPlayerId(String playerId) {
		List<PlayerPokemonDTO> playerPokemons = playerPokemonMapper.selectByPlayerId(playerId);
		playerPokemons.forEach(playerPokemon -> {
			List<PokemonOwnAttack> attacks = ownPokemonSkillMapper.selectAttackByOwnPokemonId(playerPokemon.getId());
			attacks.forEach(attack -> attack.setTypes(typesService.getById(attack.getTypesId())));

			playerPokemon.setAbilities(ownPokemonSkillMapper.selectAbilityByOwnPokemonId(playerPokemon.getId()));
			playerPokemon.setAttacks(attacks);
			playerPokemon.setStats(ownPokemonStatMapper.selectStatByOwnPokemonId(playerPokemon.getId()));
			playerPokemon.setCharacteristic(characteristicService.getById(playerPokemon.getId()));
			playerPokemon.setTypes(typesService.getTypesByPokemonId(playerPokemon.getPokemonId()));
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
	public int save(PlayerPokemonDTO playerPokemon) {
		if (playerPokemon.getCharacteristicId() == 0) {
			playerPokemon.setCharacteristicId(1);
		}

		playerPokemonMapper.insert(playerPokemon);

		List<PokemonOwnStat> randomPokemonStats = getRandomStatsByPokemonId(playerPokemon.getPokemonId());
		List<OwnPokemonStat> ownPokemonStats = randomPokemonStats.stream()
			.map(randomStat -> new OwnPokemonStat(playerPokemon.getId(), randomStat.getId(), randomStat.getValue(), randomStat.getTotal()))
			.toList();

		if (playerPokemon.getStats() != null) {
			playerPokemon.getStats().forEach(ownStat -> {
				OwnPokemonStat ownPokemonStat = ownPokemonStats.stream().filter(stat -> stat.getId() == ownStat.getId()).findFirst().orElse(null);
				if (ownPokemonStat != null) {
					if (ownPokemonStat.getTotal() > ownStat.getValue()) {
						ownPokemonStat.setValue(ownStat.getValue());
					}
				}
			});
		}

		CharacteristicDTO characteristic = getRandomCharacteristicByStats(randomPokemonStats);
		Map<String, Integer> updateCharacteristic = new HashMap<>();
		updateCharacteristic.put("id", playerPokemon.getId());
		updateCharacteristic.put("characteristicId", characteristic.getId());

		AtomicInteger hp = new AtomicInteger(0);
		ownPokemonStats.forEach(ownPokemonStat -> {
			ownPokemonStatMapper.insert(ownPokemonStat);
			if (ownPokemonStat.getStatId() == 1) hp.set(ownPokemonStat.getValue());
		});
		playerPokemonMapper.updateNameByCharacteristicById(updateCharacteristic);
		
		if (playerPokemon.getAbilities() != null) {
			saveAbilities(playerPokemon.getId(), playerPokemon.getPokemonId(), playerPokemon.getAbilities().stream().map(PokemonOwnAbility::getId).toList());
		}
		
		if (playerPokemon.getAttacks() != null) {
			saveAttacks(playerPokemon.getId(), playerPokemon.getPokemonId(), playerPokemon.getAttacks().stream().map(PokemonOwnAttack::getId).toList());
		} else {
			saveRandomAttack(playerPokemon.getId(), playerPokemon.getPokemonId());
		}
		
		return hp.get();
	}
	
	private void saveAbilities(int playerPokemonId, int pokemonId, List<Integer> abilityIds) {
		abilityIds.forEach(abilityId -> {
			if (abilityService.existAbilityAndPokemonId(pokemonId, abilityId)) {
				savePokemonAbility(new OwnPokemonSkill(playerPokemonId, abilityId, 0));
			}
		});
	}
	
	private void saveAttacks(int playerPokemonId, int pokemonId, List<Integer> attackIds) {
		attackIds.forEach(attackId -> {
			if (pokemonMoveService.existAttackAndPokemonId(pokemonId, attackId)) {
				savePokemonAttack(new OwnPokemonSkill(playerPokemonId, attackId, 0));
			}
		});
	}

	private void saveRandomAttack(int playerPokemonId, int pokemonId) {
		int typesId = typesService.getTypesByPokemonId(pokemonId).get(0).getId();
		List<PokemonOwnAttack> allOwnAttackByTypes = pokemonMoveService.getAttacksByPokemonIdAndTypeId(pokemonId, typesId);
		randomIntegerList(2, allOwnAttackByTypes.size())
			.forEach(i -> savePokemonAttack(new OwnPokemonSkill(playerPokemonId, allOwnAttackByTypes.get(i).getId(), i)));

		List<PokemonOwnAttack> normalAttack = pokemonMoveService.getAttacksByPokemonIdAndTypeId(pokemonId, 1);
		savePokemonAttack(new OwnPokemonSkill(playerPokemonId, normalAttack.get(random.nextInt(normalAttack.size())).getId(), 0));
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

	@Override
	public List<PokemonOwnStat> getRandomStatsByPokemonId(int pokemonId) {
		return pokemonService.getStatsByPokemonId(pokemonId).stream().map(baseStat -> {
			int minValue = Math.max((int)(baseStat.getValue() * 0.7), 1);
			int randomValue = random.nextInt(baseStat.getValue() - minValue + 1) + minValue;
			PokemonOwnStat stat = new PokemonOwnStat(randomValue, baseStat.getValue());
			stat.setId(baseStat.getStatId());

			return stat;
		}).toList();
	}

	@Override
	public CharacteristicDTO getRandomCharacteristicByStats(List<PokemonOwnStat> ownStats) {
		return ownStats.stream().max(Comparator.comparingInt(PokemonOwnStat::getValue))
			.map(maxStat -> {
				List<CharacteristicDTO> characteristics = characteristicService.getCharacteristicsByStatId(maxStat.getId());
				return characteristics.get(random.nextInt(characteristics.size()));
			}).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlayerPokemonDTO> minePlayerId(String sessionId) {
	    return playerPokemonMapper.selectByIngamePlayerId(sessionId);
	}

	@Override
	@Transactional
	public void saveSelectedPokemon(String sessionId, int pokemonId, String nickname) {
		PokemonDTO pokemon = pokemonService.minePokemonById(pokemonId);
		if(pokemon == null) {
			throw new IllegalArgumentException("존재하지 않는 포켓몬입니다.");
		}
		PlayerPokemonDTO playerPokemon = new PlayerPokemonDTO();
		playerPokemon.setPlayerId(sessionId);
		playerPokemon.setPokemonId(pokemonId);
		if (nickname != null && !nickname.trim().isEmpty()) {
	        playerPokemon.setName(nickname.trim());
	    } else {
	        playerPokemon.setName(pokemon.getName());
	    }
		playerPokemon.setGender(random.nextBoolean());
		playerPokemon.setLevel(5);
		playerPokemon.setExperience(0);
		
		int hp = save(playerPokemon);
		
		IngamePokemonDTO ingamePokemon = new IngamePokemonDTO();
		ingamePokemon.setId(playerPokemon.getId());
		ingamePokemon.setPlayerId(sessionId);
		ingamePokemon.setHp(hp);
		ingamePokemon.setSlot(0);

		ingamePokemonService.save(ingamePokemon);
	}

	private List<Integer> randomIntegerList(int count, int maxValue) {
		List<Integer> list = new ArrayList<>();

		while (list.size() < count) {
			int randomValue = random.nextInt(maxValue);
			if (!list.contains(randomValue)) {
				list.add(randomValue);
			}
		}

		return list;
	}
}
