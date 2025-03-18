package kr.co.pokemon.play.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.play.dto.PokemonOwnType;
import kr.co.pokemon.player.service.PlayerPokemonService;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;
import kr.co.pokemon.pokemon.service.TypesService;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PokemonService pokemonService;
	
	@Autowired
	HabitatService habitatService;
	
	@Autowired
	AbilityService abilityService;
	
	@Autowired
	PokemonMoveService pokemonMoveService;

	@Autowired
	PlayerPokemonService playerPokemonService;
	
	@Autowired
	IngamePokemonService ingamePokemonService;

	@Autowired
	TypesService typesService;
	
	Random random = new Random();

	@Override
	public CreatedPokemonDTO createPokemon(int pokemonId, int minLevel, int maxLevel) {
		PokemonDTO pokemon = pokemonService.getById(pokemonId);
		if (pokemon == null) {
			return null;
		}

		return getAllInfoCreating(pokemon, minLevel, maxLevel);
	}
	
	@Override
	public CreatedPokemonDTO createPokemonByHabitatId(int habitatId, int minLevel, int maxLevel) {
		List<PokemonDTO> pokemons = habitatService.getPokemonByHabitatId(habitatId);
		if (pokemons.isEmpty()) {
			return null;
		}

		return getAllInfoCreating(pokemons.get(random.nextInt(pokemons.size())), minLevel, maxLevel);
	}

	private CreatedPokemonDTO getAllInfoCreating(PokemonDTO pokemon, int minLevel, int maxLevel) {
		pokemon.setSprites(pokemonService.getSpritesById(pokemon.getId()));
		List<PokemonOwnStat> stats = playerPokemonService.getRandomStatsByPokemonId(pokemon.getId());
		CharacteristicDTO characteristic = playerPokemonService.getRandomCharacteristicByStats(stats);

		List<PokemonOwnAttack> attacks = pokemonMoveService.getAttacksByPokemonId(pokemon.getId());
		List<PokemonOwnAbility> abilities = abilityService.getAbilitiesByPokemonId(pokemon.getId());
		List<PokemonOwnType> types = typesService.getTypesByPokemonId(pokemon.getId());

		int level = minLevel + random.nextInt((maxLevel - minLevel) + 1);
		int hp = stats.stream().filter(stat -> stat.getId() == 1).map(PokemonOwnStat::getValue).findFirst().orElse(0);

		return new CreatedPokemonDTO(-1,
				pokemon, pokemon.getName(), random.nextBoolean(), level, hp, characteristic, abilities, attacks, stats, types
		);
	}
}
