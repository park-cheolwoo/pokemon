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
            return "redirect:/?loginChk=1";
        } else {
            model.addAttribute("loginChk", "0");
            return "member/login";
        }
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
