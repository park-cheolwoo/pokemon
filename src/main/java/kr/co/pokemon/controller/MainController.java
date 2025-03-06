package kr.co.pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
//	@GetMapping(value = "/")
//	public String index(int pno, Model model) {
//		PlayerDTO playerDto = new PlayerDTO(null, null, null, null, null, null, 0, 0, 0, 0, null, null);
//		model.addAttribute("pdto",playerDto);
//		return "index";
//	}
	@GetMapping(value = "/friend/flist")
	public String flist() {
		return "/friend/flist";
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
}
