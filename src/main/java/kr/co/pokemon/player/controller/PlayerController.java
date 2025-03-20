package kr.co.pokemon.player.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.data.dto.DataStatusDTO;
import kr.co.pokemon.player.dto.ClearDungeonDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;

@Controller
@RequestMapping(value = "/member")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // 로그인 페이지 표시
    @GetMapping(value = "/login")
    public String login(HttpSession session) {
        session.removeAttribute("isIdValidated"); 
        return "member/login";
    }
    
    // 로그인 처리
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        Map<String, String> response = new HashMap<>();

        PlayerDTO playerDTO = playerService.login(id, password); 
        if (playerDTO != null) {
        	setSessionAttributes(session, playerDTO);
            response.put("loginChk", "1"); 
        } else {
            response.put("loginChk", "0"); 
        }

        return response; 
    }


    // 회원가입 페이지
    @GetMapping(value = "/join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(PlayerDTO playerDto, Model model, HttpSession session) {
        if (playerService.insertPlayer(playerDto)) {
        	//회원가입 성공 시 ingame 데이터 추가
        	playerService.insertIngameData(playerDto.getId());
            session.removeAttribute("isIdValidated"); 
            return "redirect:/member/login"; 
        } else {
            model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해 주세요."); 
            return "member/join"; 
        }
    }

    // 아이디 중복 확인
    @GetMapping(value = "/checkId")
    @ResponseBody
    public String checkId(@RequestParam String id) {
        return playerService.isIdAble(id) ? "Able" : "unAble";
    }
    
    
    private void setSessionAttributes(HttpSession session, PlayerDTO playerDto) {
        session.setAttribute("session_id", playerDto.getId());
        session.setAttribute("session_nickname", playerDto.getNickname());
        session.setAttribute("session_tag", playerDto.getTag());
        session.setAttribute("session_lv", playerDto.getLv());
        session.setAttribute("session_experience", playerDto.getExperience());
        session.setAttribute("session_gameMoney", playerDto.getGameMoney());
        session.setAttribute("session_realMoney", playerDto.getRealMoney());
    }
    
    @PostMapping(value = "/update/player")
    public DataStatusDTO<Boolean> updatePlayer(@RequestBody PlayerDTO playerDto, HttpSession session) {
        try {
            String session_id = (String) session.getAttribute("session_id");

            if (session_id != null) {
                playerService.updateplayer(session_id, playerDto);
                return new DataStatusDTO<>("success", true, "플레이어 정보 업데이트 성공");
            }

            throw new IllegalArgumentException("유효하지 않은 세션입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataStatusDTO<>("error", false, e.getMessage());
        }
    }
    
    @PostMapping(value = "/update/prlevel")
    public DataStatusDTO<Boolean> updatePlayerLevel(@RequestParam int level, HttpSession session) {
        try {
            String session_id = (String) session.getAttribute("session_id");
            if (session_id != null) {
                // 플레이어 레벨 업데이트
                playerService.updateplayerLevel(session_id, level);
                return new DataStatusDTO<>("success", true);
            }
            throw new IllegalArgumentException("유효하지 않은 세션입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataStatusDTO<>("error", false, e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/update/prexperience")
    public DataStatusDTO<Boolean> updatePlayerExperience(@RequestParam String playerId, @RequestParam int experience, HttpSession session) {
        try {
            String session_id = (String) session.getAttribute("session_id");
            if (session_id != null) {
                playerService.updateplayerExperience(playerId != null? playerId : session_id, experience);
                return new DataStatusDTO<>("success", true);
            }

            throw new IllegalArgumentException("유효하지 않은 세션입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataStatusDTO<>("error", false, e.getMessage());
        }
    }
    
    @ResponseBody
    @PostMapping(value = "/update/prgold")
    public DataStatusDTO<Boolean> updatePlayerGold(@RequestParam String playerId, @RequestParam int gold,
            HttpSession session) {
        try {
            String session_id = (String) session.getAttribute("session_id");
            if (session_id != null) {
                playerService.increaseGoldByPlayer(playerId != null ? playerId : session_id, gold);
                return new DataStatusDTO<>("success", true);
            }

            throw new IllegalArgumentException("유효하지 않은 세션입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return new DataStatusDTO<>("error", false, e.getMessage());
        }

    }
    
    @ResponseBody
    @PostMapping(value = "/clearDungeon")
    public DataStatusDTO<Boolean> updateClearDungeon(@RequestBody ClearDungeonDTO clearDungeon, HttpSession session) {
    	try {
    		String session_id = (String) session.getAttribute("session_id");
    		if (session_id != null) {
    			playerService.clearDungeon(clearDungeon);
    			return new DataStatusDTO<>("success", true);
    		}
    		
    		throw new IllegalArgumentException("유효하지 않은 세션입니다.");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return new DataStatusDTO<>("error", false, e.getMessage());
    	}
    	
    }
    
    @ResponseBody
    @GetMapping("/sessionData")
    public Map<String, Object> getSessionData(HttpSession session) {
        String sessionId = (String) session.getAttribute("session_id");
        if (sessionId == null) {
            throw new IllegalArgumentException("유효하지 않은 세션입니다.");
        }
        PlayerDTO player = playerService.getById(sessionId);
        setSessionAttributes(session, player);
        
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("session_id", player.getId());
        sessionData.put("session_nickname", player.getNickname());
        sessionData.put("session_tag", player.getTag());
        sessionData.put("session_lv", player.getLv());
        sessionData.put("session_experience", player.getExperience());
        sessionData.put("session_gameMoney", player.getGameMoney());
        sessionData.put("session_realMoney", player.getRealMoney());
        
        return sessionData;
    }
}