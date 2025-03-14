package kr.co.pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.plan.dto.SdungeonDTO;
import kr.co.pokemon.plan.service.SdungeonService;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;


@Controller
public class MainController {
	
	@Autowired HttpSession session;
	@Autowired SdungeonService sdungeonService;
//	private PlayerService playerService;
	
	@GetMapping(value = "/")
	public String index() {
		String playerId = (String) session.getAttribute("session_id");
		if(playerId ==null) {
			return "redirect:/member/login";
		}
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
	public String sdungeon(Model model) {
//	    String playerId = (String) session.getAttribute("session_id");
//
//	    if (playerId == null) {
//	        return "redirect:/member/login";
//	    }
//
//	    // playerId로 SdungeonDTO 가져오기
//	    SdungeonDTO sdungeonDto = sdungeonService.getSdungeonById(playerId);
//
//	    // 모델에 추가
//	    if (sdungeonDto != null) {
//	        model.addAttribute("sdungeon", sdungeonDto);
//	        // 결과 출력 (디버깅용)
//	        System.out.println("sdungeon : " + sdungeonDto);
//	    } else {
//	        System.out.println("sdungeonDto is null for playerId: " + playerId);
//	    }

	    return "/play/sdungeon";
	}


	
	@GetMapping("/play/battle")
	public String battle() {
		return "/play/battle";
	}
	
}
