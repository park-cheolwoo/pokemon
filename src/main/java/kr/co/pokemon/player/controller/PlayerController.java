package kr.co.pokemon.player.controller;

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
	
	@Autowired PlayerService playerService;
	@Autowired HttpSession session;
	

	@GetMapping(value = "/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping(value = "/login")
    public String login(@RequestParam String id, @RequestParam String password, Model model, HttpSession session) {
        PlayerDTO playerDto = playerService.login(id, password);
        if (playerDto != null) {
            model.addAttribute("loginChk", "1");
            session.setAttribute("session_id", playerDto.getId());
            session.setAttribute("session_nickname", playerDto.getNickname());
            session.setAttribute("session_tag", playerDto.getTag());
            session.setAttribute("session_lv", playerDto.getLv());
            session.setAttribute("session_gameMoney", playerDto.getGameMoney());
            session.setAttribute("session_realMoney", playerDto.getRealMoney());
            return "redirect:/?loginChk=1";
        } else {
            model.addAttribute("loginChk", "0");
            return "member/login";
        }
	}
	@GetMapping(value = "/profile")
    public String profile(HttpSession session, Model model) {
        PlayerDTO player = (PlayerDTO) session.getAttribute("player");
        model.addAttribute("player", player);
        model.addAttribute("session_id", session.getAttribute("session_id"));
        model.addAttribute("session_nickname", session.getAttribute("session_nickname"));
        model.addAttribute("session_tag", session.getAttribute("session_tag"));
        model.addAttribute("session_lv", session.getAttribute("session_lv"));
        model.addAttribute("session_gameMoney", session.getAttribute("session_gameMoney"));
        model.addAttribute("session_realMoney", session.getAttribute("session_realMoney"));
        return "/index";
    }
	
	@GetMapping(value = "/join")
	public String join() {
		return "/member/join";
	}
	
	@PostMapping(value = "/join")
	public String join(PlayerDTO playerDto, Model model) {
		boolean addjoin = playerService.createPlayer(playerDto);
		if(addjoin) {			
			return "member/login";
		}else {
			model.addAttribute("error","회원가입에 실패했습니다. 다시 시도해 주세요.");
			return "member/join";
		}
	}
	
	@GetMapping(value="/checkId")
	@ResponseBody
	public String join(@RequestParam String id) {
		boolean Able = playerService.isIdAble(id);
		return Able ? "Able" : "unAble";
	}
	
	
}
