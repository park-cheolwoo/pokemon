package kr.co.pokemon.player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;


@Controller
@RequestMapping(value = "/friend")
public class FriendController {
	
	@Autowired PlayerService playerService;

	@GetMapping(value = "/flist")
	public String flist(Model model) {
		List<FriendDTO> friends = playerService.getFriendList("currentPlayerId");
		PlayerDTO randomPlayer = playerService.getRandomPlayer();
		
		model.addAttribute("friends",friends);
		model.addAttribute("randomPlayer", randomPlayer);
		return "/friend/flist";
	}
	
	@GetMapping(value = "/{playerId}")
	public String getFriendList(@PathVariable String playerId, Model model) {
		List<FriendDTO> friends = playerService.getFriendList(playerId);
		model.addAttribute("friends", friends);
		return "/friend/flist";
	}
	
	@PostMapping(value="/request")
	public ResponseEntity<String> sendFriendRequest(@RequestParam String fromId, @RequestParam String toId){
		boolean result = playerService.sendFriendRequest(fromId, toId);
		if(result) {
			return ResponseEntity.ok("친구 요청이 전송되었습니다.");
		}else {
			return ResponseEntity.badRequest().body("친구 요청을 보내는데 실패했습니다.");
		}
	}
	
	@PostMapping(value = "/accept")
	public ResponseEntity<String> acceptFriendRequest(@RequestParam String requestId) {
		boolean result = playerService.acceptFriendRequest(requestId);
		if(result) {
			return ResponseEntity.ok("친구 요청을 수락했습니다.");
		}else {
			return ResponseEntity.badRequest().body("친구 요청 수락에 실패했습니다.");
		}
	}
	
	
}
