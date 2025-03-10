package kr.co.pokemon.pros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProsController {

	
	@GetMapping("/member/admin")
	public String admin() {
		return "pros/pros_select";
	}
	
//	테스트용 임시 //
	
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
	
	

//	테스트용 임시 //	
	
}
