package kr.co.pokemon.pros.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@RequestMapping("/admin")
@Controller
public class ProsController {

	@Autowired
	PlayerService playerService;
	@Autowired
	PokemonService pokemonService;

	@GetMapping(value = "")
	public String admin() {
		return "pros/pros_player_list";
	}

	@GetMapping(value = "/data")
	public String adminData() {
		return "pros/pros_data";
	}

	@GetMapping(value = "/pokemon")
	public String adminPokemon(@RequestParam(defaultValue = "1") int page, Model model) {
		List<PokemonDTO> list = pokemonService.getAll(new PageRequestDTO(96,page));
		model.addAttribute("list", list);
		return "pros/pros_data_list";
	}
	
	@GetMapping(value = "/player")
	public String adminPlayer(@RequestParam(defaultValue = "1") int page, Model model) {
		List<PlayerDTO> list = playerService.getAll(new PageRequestDTO(96,page));
		model.addAttribute("list", list);
		return "pros/pros_player_list";
	}

	
	
	

}