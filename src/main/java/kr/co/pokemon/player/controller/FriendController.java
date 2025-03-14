package kr.co.pokemon.player.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.FriendService;


@Controller
@RequestMapping(value = "/friend")
public class FriendController {
	
	@Autowired FriendService friendService;

	@GetMapping(value = "/flist")
	public String flist(Model model, HttpSession session) {
	    String session_id = (String) session.getAttribute("session_id"); 
	    List<FriendDTO> friends = friendService.getFriendList(session_id);
	    List<FriendDTO> pendings = friendService.getPendingList(session_id);
	    model.addAttribute("friends", friends);
	    model.addAttribute("pendings", pendings);
	    return "/friend/flist"; 
	}

	//친구 요청 
	@PostMapping(value = "/add")
	@ResponseBody
	public String addFriend(@RequestParam String tag, HttpSession session) {
	    String sessionId = (String) session.getAttribute("session_id"); 
	    boolean result = friendService.addFriend(sessionId, tag);
	    if (result) {
	        return "SUCCESS";
	    } else {
	        return "NOT_FOUND"; 
	    }
	}
	
    // 친구 요청 수락 처리
	@PostMapping(value = "/accept")
	@ResponseBody
	public String acceptFriend(@RequestParam("playerFrom") String playerFrom, HttpSession session) {
	    String session_id = (String) session.getAttribute("session_id");
	    boolean success = friendService.acceptFriend(session_id, playerFrom);
	    if (success) {
	        System.out.println("친구 요청 수락 성공: " + playerFrom);
	        return "success";
	    } else {
	        System.out.println("친구 요청 수락 실패: " + playerFrom);
	        return "fail";
	    }
	}
	 // 친구 요청 취소 처리
		@PostMapping(value = "/cancel")
		@ResponseBody
		public String Friendcancel(@RequestParam("playerFrom") String playerFrom, HttpSession session) {
		    String session_id = (String) session.getAttribute("session_id");
		    boolean success = friendService.cancelFriend(session_id, playerFrom);
		    if (success) {
		        return "success";
		    } else {
		        return "fail";
		    }
		}
		
		@PostMapping(value="/delete1")
		@ResponseBody
		public String deleteFriend1(@RequestParam("playerFrom") String playerFrom, HttpSession session) {
			String session_id = (String)session.getAttribute("session_id");
			boolean success = friendService.deleteFriend1(session_id, playerFrom);
			if(success) {
				return "success";
			}else {
				return "fail";
			}
		}
		@PostMapping(value="/delete2")
		@ResponseBody
		public String deleteFriend2(@RequestParam("playerTo") String playerTo, HttpSession session) {
			String session_id = (String)session.getAttribute("session_id");
			boolean success = friendService.deleteFriend2(session_id, playerTo);
			if(success) {
				return "success";
			}else {
				return "fail";
			}
		}
		
}
