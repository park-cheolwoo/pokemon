package kr.co.pokemon.play.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.play.dto.IngameInfoDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;
import kr.co.pokemon.play.service.IngameService;

@RestController
@RequestMapping("/ingame")
public class IngameController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IngameService ingameService;
	
	@GetMapping(value = "/info")
	public IngameInfoDTO getIngameInfo() {
		String session_id = (String) session.getAttribute("session_id");
		
		return ingameService.getIngameInfoByPlayerId(session_id);
	}
	
	@GetMapping(value = "/player")
	public IngameDTO getIngame() {
		String session_id = (String) session.getAttribute("session_id");
		
		return ingameService.getByPlayerId(session_id);
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

	@GetMapping("/expedition")
	public ResponseEntity<?> getExpeditionList() {
		String playerId = (String) session.getAttribute("session_id");
		if (playerId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("status", "error", "message", "로그인이 필요합니다."));
		}
		
		try {
			List<IngamePokemonDTO> expeditionList = ingameService.getExpeditionByPlayerId(playerId);
			return ResponseEntity.ok(Map.of("status", "success", "data", expeditionList));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("status", "error", "message", "원정대 목록 조회 중 오류가 발생했습니다."));
		}
	}

	@PostMapping("/expedition/save")
	public ResponseEntity<?> saveExpeditionList(@RequestBody List<Integer> expeditionPokemonIds) {
		String playerId = (String) session.getAttribute("session_id");
		if (playerId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("status", "error", "message", "로그인이 필요합니다."));
		}
		
		try {
			boolean result = ingameService.saveExpeditionList(playerId, expeditionPokemonIds);
			if (result) {
				return ResponseEntity.ok(Map.of("status", "success", "message", "원정대 목록이 저장되었습니다."));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(Map.of("status", "error", "message", "원정대 목록 저장에 실패했습니다."));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("status", "error", "message", "원정대 목록 저장 중 오류가 발생했습니다."));
		}
	}
}