package kr.co.pokemon.play.service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.CharacteristicService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PokemonService pokemonService;
	
	@Autowired
	CharacteristicService characteristicService;
	
	@Autowired
	HabitatService habitatService;
	
	@Autowired
	AbilityService abilityService;
	
	@Autowired
	PokemonMoveService pokemonMoveService;

	@Autowired
	IngameService ingameService;
	
	Random random = new Random();

	@Override
	public CreatedPokemonDTO createPokemon(int pokemonId) {
		PokemonDTO pokemon = pokemonService.getById(pokemonId);
		if (pokemon == null) {
			return null;
		}

		pokemon.setSprites(pokemonService.getSpritesById(pokemonId));
		List<PokemonStatDTO> stats = getRandomStat(pokemon);

		return new CreatedPokemonDTO(pokemon, pokemon.getName(), stats, getCharacteristic(stats), pokemonMoveService.getAttacksByPokemonId(pokemonId), abilityService.getAbilitiesByPokemonId(pokemonId));
	}
	
	@Override
	public CreatedPokemonDTO createPokemonByHabitatId(int habitatId) {
		List<PokemonDTO> pokemons = habitatService.getPokemonByHabitatId(habitatId);
		if (pokemons.size() == 0) {
			return null;
		}
		
		PokemonDTO pokemon = pokemons.get(random.nextInt(pokemons.size()));
		pokemon.setSprites(pokemonService.getSpritesById(pokemon.getId()));
		
		List<PokemonStatDTO> stats = getRandomStat(pokemon);

		return new CreatedPokemonDTO(pokemon, pokemon.getName(), stats, getCharacteristic(stats), pokemonMoveService.getAttacksByPokemonId(pokemon.getId()), abilityService.getAbilitiesByPokemonId(pokemon.getId()));
	}
	
	private List<PokemonStatDTO> getRandomStat(PokemonDTO pokemon) {
		return pokemonService.getStatsByPokemonId(pokemon.getId()).stream().map(stat -> {
			stat.setValue(stat.getValue() - random.nextInt(20));
			return stat;
		}).toList();
	}
	
	private CharacteristicDTO getCharacteristic(List<PokemonStatDTO> stats) {
		return stats.stream()
				.max(Comparator.comparingInt((PokemonStatDTO::getValue)))
				.map(pokemonStat -> {
					List<CharacteristicDTO> characteristics = characteristicService.getCharacteristicsByStatId(pokemonStat.getStatId());
					
					return characteristics.get(random.nextInt(characteristics.size()));
				})
		.get();
	}

	@Override
	public IngameDTO getIngame(int playerId) {
		return ingameService.getById(playerId);
	}
	
}
