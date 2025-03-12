package kr.co.pokemon.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

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
	
	
	
	
	
	
	@GetMapping("/first/first")
	public String first() {
		return "/first/first";
	}	
	
	@GetMapping("/play/battle")
	public String battle() {
		return "/play/battle";
	}
	
	
}
