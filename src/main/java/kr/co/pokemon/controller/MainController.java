package kr.co.pokemon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.plan.dto.SdungeonDTO;
import kr.co.pokemon.plan.service.SdungeonService;


@Controller
public class MainController {
	
	@Autowired HttpSession session;
	@Autowired SdungeonService sdungeonService;
//	private PlayerService playerService;
	
	@GetMapping(value = "/")
	public String index(HttpSession session, HttpServletResponse response) {
		String playerId = (String) session.getAttribute("session_id");
		if(playerId ==null) {
			return "redirect:/member/login";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
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
	public String plist(HttpSession session, Model model) {
	    String playerId = (String) session.getAttribute("session_id");

	    if (playerId == null) {
	        return "redirect:/member/login";
	    }

	    // playerId로 SdungeonDTO 가져오기
	    SdungeonDTO sdungeonDto = sdungeonService.getSdungeonById(playerId);

	    // SdungeonDTO의 dailyClearCount가 1 이상일 때
	    if (sdungeonDto != null && sdungeonDto.getDailyClearCount() >= 1) {
	        // 알림 메시지 전달
	        model.addAttribute("message", "오늘 플레이횟수를 모두 완료했습니다.");
	    }

	    return "/play/plist";  // JSP 페이지로 이동
	}

	
	@GetMapping("/play/quest")
	public String quest(Model model) {
		String playerId = (String) session.getAttribute("session_id");
		SdungeonDTO sdungeonDTO = sdungeonService.getSdungeonInfo(playerId);
		 model.addAttribute("sdungeon", sdungeonDTO);
		return "/play/quest";
	}
	
	@GetMapping("/play/dungeon")
	public String dungeon() {
		return "/play/dungeon";
	}

	@GetMapping("/play/sdungeon")
	public String sdungeon(Model model) {
	    String playerId = (String) session.getAttribute("session_id");

	    if (playerId == null) {
	        return "redirect:/member/login";
	    }

	    // playerId로 SdungeonDTO 가져오기
	    SdungeonDTO sdungeonDto = sdungeonService.getSdungeonById(playerId);
	    
	    if (sdungeonDto != null && sdungeonDto.getDailyClearCount() >= 1) {
	    	return "redirect:/";
	    }
	    // 모델에 추가
	    if (sdungeonDto != null) {
	        model.addAttribute("sdungeon", sdungeonDto);
	        // 결과 출력 (디버깅용)
	        System.out.println("sdungeon : " + sdungeonDto);
	    } else {
	        System.out.println("sdungeonDto is null for playerId: " + playerId);
	    }

	    return "/play/sdungeon";
	}

	@PostMapping("/updateSdungeonCount")
	public ResponseEntity<Map<String, Object>> updateSdungeonCount(HttpSession session, @RequestBody Map<String, Integer> requestBody) {
	    String playerId = (String) session.getAttribute("session_id");

	    if (playerId == null) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }

	    // 클라이언트에서 전송한 score 값
	    int score = requestBody.get("score");

	    // playerId로 SdungeonDTO 가져오기
	    SdungeonDTO sdungeonDto = sdungeonService.getSdungeonById(playerId);

	    if (sdungeonDto != null) {
	        // score에 따라 계산된 gameMoney (score * 500 + 500)
	        int calculatedGameMoney = score * 500 + 500;

	        // gameMoney 업데이트
	        sdungeonDto.setGameMoney(calculatedGameMoney);

	        // 카운트 증가
	        sdungeonDto.setDailyClearCount(sdungeonDto.getDailyClearCount() + 1);
	        sdungeonDto.setWeeklyClearCount(sdungeonDto.getWeeklyClearCount() + 1);
	        sdungeonDto.setTotalCount(sdungeonDto.getTotalCount() + 1);

	        // 서비스 메서드를 호출하여 데이터베이스에 반영
	        sdungeonService.updateSdungeonCount(sdungeonDto);

	        // 게임 머니 값을 반환
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("gameMoney", sdungeonDto.getGameMoney());  // 최신 gameMoney 값

	        return ResponseEntity.ok(response);
	    } else {
	        System.out.println("SdungeonDTO not found for playerId: " + playerId);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	
	

	
	@GetMapping("/play/battle")
	public String battle() {
		return "/play/battle";
	}
	
}
