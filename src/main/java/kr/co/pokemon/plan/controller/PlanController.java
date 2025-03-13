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

    @Autowired SdungeonService sdungeonService;

    @Autowired HttpSession session;

    @Autowired PlayerMapper playerMapper;  // PlayerMapper 주입

    @GetMapping("/play/sdungeon")
    public String sdungeon(Model model) {
        
        // 세션에서 ID 가져오기
        String sessionId = (String) session.getAttribute("session_id");
        if(sessionId == null) {
            return "redirect:/";  // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // PlayerDTO 조회
        PlayerDTO player = playerMapper.chooseById(sessionId);  // sessionId로 PlayerDTO 조회

        // PlayerDTO에서 gameMoney 가져오기
        if (player != null) {
            int gameMoney = player.getGameMoney();  // 게임 머니 가져오기
            model.addAttribute("gameMoney", gameMoney);  // 모델에 gameMoney 추가
        }

        return "/play/sdungeon";  // 게임 화면으로 반환
    }
}

