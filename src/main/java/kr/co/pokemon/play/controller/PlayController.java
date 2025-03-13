package kr.co.pokemon.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.service.PlayService;

@RestController
@RequestMapping(value = "/play")
public class PlayController {
	
	@Autowired
	PlayService playService;
	
	@GetMapping(value = "/create/pokemon/{pokemonId}")
	public CreatedPokemonDTO createPokemonById(@PathVariable int pokemonId) {
		return playService.createPokemon(pokemonId);
	}
	
	@GetMapping(value = "/create/pokemon/habitat/{habitatId}")
	public CreatedPokemonDTO createPokemonByHabitatId(@PathVariable int habitatId) {
		return playService.createPokemonByHabitatId(habitatId);
	}

}
