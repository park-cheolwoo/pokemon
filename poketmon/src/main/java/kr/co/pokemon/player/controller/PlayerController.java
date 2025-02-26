package kr.co.pokemon.player.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class PlayerController {

	@GetMapping(value = "/login")
	public String login() {
		return "member/login";
	}

}
