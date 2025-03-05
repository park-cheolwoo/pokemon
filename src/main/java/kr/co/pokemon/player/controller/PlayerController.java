package kr.co.pokemon.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String login(String id, String pw, Model model) {
		PlayerDTO playerDto = playerService.login(id,pw);
		if(playerDto !=null) {
			model.addAttribute("chkLogin","1");
			session.setAttribute("session_id", playerDto.getId());
			return "redirect:/?loginChk=1";
		}else {
			model.addAttribute("loginChk",0);
		}
		return "member/login";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		return "/member/join";
	}
	
}
