package kr.co.pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.player.service.PlayerService;


@Controller
public class MainController {
	
//	@Autowired
//	private PlayerService playerService;
	
	@GetMapping(value = "/")
	public String index(HttpSession session, HttpServletResponse response) {
		String playerId = (String) session.getAttribute("session_id");
		if(playerId ==null) {
			return "redirect:/member/login";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
//		int pokemonCount = playerService.countPlayerPokemons(playerId);
//		if(pokemonCount ==0) {
//			return "redirect:/first";
//		}
		return "index";
	}
	@GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/member/login";
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
	
	@GetMapping("/first/first")
	public String first() {
		return "/first/first";
	}	
	
	@GetMapping("/play/battle")
	public String battle() {
		return "/play/battle";
	}
}
