package kr.co.pokemon.pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/play")
public class PokemonController {

	@GetMapping(value = "/battle1")
	public String battle() {
		return "play/battle1";
	}
}
