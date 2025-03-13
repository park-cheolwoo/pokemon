package kr.co.pokemon.pros.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.co.pokemon.data.dto.PageRequestDTO;
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

	@GetMapping(value = "/pokemon/{page}")
	public String adminPokemon(PageRequestDTO pDTO, Model model) {
		pDTO.setSize(96);
		List<PokemonDTO> list = pokemonService.getAll(pDTO);
		System.out.println("list : " + list);
		System.out.println("list.size() : " + list.size());
		model.addAttribute("list", list);
		return "pros/pros_data_list";
	}

	@GetMapping("/member/admin2")
	public String adminView() {
		return "pros/pros_member_view";
	}

	@GetMapping("/member/admin3")
	public String adminView2() {
		return "pros/pros_pokemon_view";
	}

	@GetMapping("/member/admin4")
	public String adminView3() {
		return "pros/pros_data";
	}

	@GetMapping("/member/admin5")
	public String adminView4() {
		return "pros/pros_select1";
	}

}
