package kr.co.pokemon.play.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameEnemyDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.dto.UpdateHpPokemonDTO;
import kr.co.pokemon.play.service.IngamePokemonService;
import kr.co.pokemon.play.service.IngameService;

@RestController
@RequestMapping(value = "/ingame")
public class IngameController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IngameService ingameService;
	
	@Autowired
	private IngamePokemonService ingamePokemonService;
	
	@GetMapping(value = "/me")
	public IngameDTO getMyIngame() {
		String session_id = (String) session.getAttribute("session_id");
		if (session_id.isBlank()) {
			return null;
		}

		return ingameService.getByPlayerId(session_id);
	}
	
	@GetMapping(value = "/me/info")
	public IngameInfoDTO getMyIngameInfo() {
		String session_id = (String) session.getAttribute("session_id");
		if (session_id.isBlank()) {
			return null;
		}

		return ingameService.getIngameInfoByPlayerId(session_id);
	}
	
	@PostMapping(value = "/status")
	public boolean setIngame(@RequestBody Boolean isIngame) {
		String session_id = (String) session.getAttribute("session_id");
		if (session_id.isBlank()) {
			return false;
		}
		
		return ingameService.updateIngameStatus(session_id, isIngame);
	}
	
	@PostMapping(value = "/stage")
	public boolean setStage(@RequestBody String stage) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			int stageId = Integer.parseInt(stage);
			
			if (session_id.isBlank()) throw new IllegalArgumentException("로그인 상태에서만 가능합니다.");

			return ingameService.updateIngameStage(session_id, stageId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping(value = "/maxStage")
	public boolean setMaxStage(@RequestBody String stage) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			int stageId = Integer.parseInt(stage);

			if (session_id.isBlank()) throw new IllegalArgumentException("로그인 상태에서만 가능합니다.");

			return ingameService.updateIngameMaxStage(session_id, stageId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping(value = "/pokemon/idx")
	public boolean setIngame(@RequestBody String idx) {		
		try {
			String session_id = (String) session.getAttribute("session_id");
			int parsedIdx = Integer.parseInt(idx);
			
			if (session_id.isBlank()) throw new IllegalArgumentException("로그인 상태에서만 가능합니다.");

			return ingameService.updateSelectionIdx(session_id, parsedIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping(value = "/pokemon")
	public boolean saveIngamePokemon(@RequestBody List<IngamePokemonDTO> ownPokemons) {
		return ingamePokemonService.saveIngamePokemons(ownPokemons);
	}
	
	@PostMapping(value = "/enemy")
	public boolean saveIngameEnemy(@RequestBody List<IngameEnemyDTO> enemies) {
		return ingamePokemonService.saveIngameEnemies(enemies);
	}
		
	@PostMapping(value = "/enemy/delete")
	public boolean deleteIngameEnemy() {
		String session_id = (String) session.getAttribute("session_id");
		if (session_id.isBlank()) {
			return false;
		}

		return ingamePokemonService.deleteEnemies(session_id);
	}

	@PostMapping(value = "/pokemon/hp")
	public boolean saveIngamePokemonHp(@RequestBody UpdateHpPokemonDTO updateHp) {
		try {
			ingamePokemonService.updateIngamePokemonHp(updateHp);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping(value = "/enemy/hp")
	public boolean saveIngameEnemyHp(@RequestBody UpdateHpPokemonDTO updateHp) {
		try {
			ingamePokemonService.updateIngameEnemyHp(updateHp);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
