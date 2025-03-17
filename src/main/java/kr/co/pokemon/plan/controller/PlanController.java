package kr.co.pokemon.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

	@GetMapping("/first/first")
	public String first() {
		return "/first/first";
	}	
	
}
