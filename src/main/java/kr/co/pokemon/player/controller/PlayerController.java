package kr.co.pokemon.player.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
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
        session.setAttribute("session_gameMoney", playerDto.getGameMoney());
        session.setAttribute("session_realMoney", playerDto.getRealMoney());
    }
}