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

}
