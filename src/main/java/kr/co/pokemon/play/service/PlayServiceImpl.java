package kr.co.pokemon.play.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.player.service.PlayerPokemonService;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;

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
	IngameService ingameService;

	@Autowired
	PlayerPokemonService playerPokemonService;
	
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

	@Override
	public IngameDTO getIngame(int playerId) {
		return ingameService.getById(playerId);
	}

	private CreatedPokemonDTO getAllInfoCreating(PokemonDTO pokemon, int minLevel, int maxLevel) {
		pokemon.setSprites(pokemonService.getSpritesById(pokemon.getId()));
		List<PokemonOwnStat> stats = playerPokemonService.getRandomStatsByPokemonId(pokemon.getId());
		CharacteristicDTO characteristic = playerPokemonService.getRandomCharacteristicByStats(stats);

		List<PokemonOwnAttack> attacks = pokemonMoveService.getAttacksByPokemonId(pokemon.getId());
		List<PokemonOwnAbility> abilities = abilityService.getAbilitiesByPokemonId(pokemon.getId());

		int level = minLevel + random.nextInt((maxLevel - minLevel) + 1);

		return new CreatedPokemonDTO(pokemon, pokemon.getName(), random.nextBoolean(), level, characteristic, abilities, attacks, stats);
	}

	@Override
	public List<CreatedPokemonDTO> getIngamePokemons(String playerId) {
		return playerPokemonService.getByPlayerId(playerId).stream().map(playerPokemon -> {
			PokemonDTO pokemon = pokemonService.getById(playerPokemon.getPokemonId());
			if (pokemon == null) {
				return null;
			}
			pokemon.setSprites(pokemonService.getSpritesById(pokemon.getId()));

			return new CreatedPokemonDTO(
				pokemon, playerPokemon.getName(), playerPokemon.isGender(), playerPokemon.getLevel(),
				playerPokemon.getCharacteristic(), playerPokemon.getAbilities(), playerPokemon.getAttacks(),
				playerPokemon.getStats());
		}).toList();
	}
}
