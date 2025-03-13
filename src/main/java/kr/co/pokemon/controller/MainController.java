package kr.co.pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.player.service.PlayerService;


@Controller
public class MainController {
	
//	@Autowired
//	private PlayerService playerService;
	
	@GetMapping(value = "/")
	public String index(HttpSession session) {
		String playerId = (String) session.getAttribute("session_id");
		if(playerId ==null) {
			return "redirect:/member/login";
		}
//		int pokemonCount = playerService.countPlayerPokemons(playerId);
//		if(pokemonCount ==0) {
//			return "redirect:/first";
//		}
		return "index";
	}
	
	
	@GetMapping("/play/plist")
	public String plist() {
		return "/play/plist";
	}	
	
	@GetMapping("/play/quest")
	public String quest() {
		return "/play/quest";
	}
	
	@GetMapping("/play/dungeon")
	public String dungeon() {
		return "/play/dungeon";
	}

	@GetMapping("/play/sdungeon")
	public String sdungeon() {
		return "/play/sdungeon";
	}

	
	@GetMapping("/play/battle")
	public String battle() {
		return "/play/battle";
	}
	
}
