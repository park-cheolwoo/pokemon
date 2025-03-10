package kr.co.pokemon.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    // 상점 메인페이지 오픈	
	@GetMapping(value = "/store/basicStore")
	public String basicStore() {
		return "store/basicStore";
	}
}
