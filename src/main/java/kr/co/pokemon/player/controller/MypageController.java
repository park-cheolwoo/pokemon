package kr.co.pokemon.player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {
	
	@GetMapping("/mypage")
	public String mypage() {
		return "/mypage/mypage";
	}

	@GetMapping("/item")
	public String item() {
		return "/mypage/item";
	}
	
}
