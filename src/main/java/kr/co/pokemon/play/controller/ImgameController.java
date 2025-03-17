package kr.co.pokemon.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.service.IngameService;

@RestController
@RequestMapping(value = "/ingame")
public class ImgameController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IngameService ingameService;
	
	@GetMapping(value = "/me")
	public IngameDTO getMyIngame() {
		String session_id = (String) session.getAttribute("session_id");

		return ingameService.getByPlayerId(session_id);
	}
	
	@GetMapping(value = "/me/info")
	public IngameInfoDTO getMyIngameInfo() {
		String session_id = (String) session.getAttribute("session_id");

		return ingameService.getIngameInfoByPlayerId(session_id);
	}
	
	@PostMapping(value = "/status")
	public boolean setIngame(@RequestBody Boolean isIngame) {
		String session_id = (String) session.getAttribute("session_id");
		
		return ingameService.updateIngameStatus(session_id, isIngame);
	}
	
	@PostMapping(value = "/pokemon/idx")
	public boolean setIngame(@RequestBody int idx) {
		String session_id = (String) session.getAttribute("session_id");
		
		return ingameService.updateSelectionIdx(session_id, idx);
	}

}
