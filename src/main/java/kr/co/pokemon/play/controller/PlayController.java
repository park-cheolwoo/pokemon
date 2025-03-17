package kr.co.pokemon.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.service.PlayService;

@RestController
@RequestMapping(value = "/play")
public class PlayController {

	@Autowired
	HttpSession session;

	@Autowired
	PlayService playService;
	
	@GetMapping(value = "/create/pokemon/{pokemonId}")
	public CreatedPokemonDTO createPokemonById(@PathVariable int pokemonId,
		@RequestParam(defaultValue = "1") int minLevel, @RequestParam(defaultValue = "100") int maxLevel
	) {
		return playService.createPokemon(pokemonId, minLevel, maxLevel);
	}
	
	@GetMapping(value = "/create/pokemon/habitat/{habitatId}")
	public CreatedPokemonDTO createPokemonByHabitatId(@PathVariable int habitatId,
		@RequestParam(defaultValue = "1") int minLevel, @RequestParam(defaultValue = "100") int maxLevel
	) {
		return playService.createPokemonByHabitatId(habitatId, minLevel, maxLevel);
	}
}
