package kr.co.pokemon.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.plan.service.SdungeonService;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dto.PlayerDTO;

@Controller
public class PlanController {

	@GetMapping("/first/first")
	public String first() {
		return "/first/first";
	}	
}

