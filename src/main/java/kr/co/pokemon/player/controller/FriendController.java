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
	    if (friends != null && !friends.isEmpty()) {
	        System.out.println("친구 목록 불러오기 성공: " + friends); 
	        
	    } else {
	        System.out.println("친구 목록이 없습니다.");
	    }
	    model.addAttribute("friends", friends); 
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
	
	@GetMapping("/pending")
	@ResponseBody
	public List<Map<String, Object>> getPending(HttpSession session) {
	    String playerToId = (String) session.getAttribute("session_id");
	    System.out.println("DEBUG: playerToId = " + playerToId); 

	    List<FriendDTO> pendingRequests = friendService.getPending(playerToId);
	    System.out.println("DEBUG: 요청 데이터 = " + pendingRequests); 

	    List<Map<String, Object>> response = new ArrayList<>();
	    for (FriendDTO request : pendingRequests) {
	        PlayerDTO playerFrom = request.getPlayerFrom();

	        String nickname = (playerFrom != null && playerFrom.getNickname() != null) 
	                          ? playerFrom.getNickname() 
	                          : "알 수 없음";
	        String level = (playerFrom != null && playerFrom.getLv() != 0) 
	                       ? String.valueOf(playerFrom.getLv()) 
	                       : "알 수 없음";

	        Map<String, Object> data = new HashMap<>();
	        data.put("id", request.getId()); 
	        data.put("lv", level);           
	        data.put("nickname", nickname);  

	        response.add(data);
	    }
	    return response; // 가공된 데이터를 반환
	}

    // 친구 요청 수락 처리
    @PostMapping(value = "/accept")
    @ResponseBody
    public String acceptFriend(@RequestParam String playerId, HttpSession session) {
        String sessionId = (String) session.getAttribute("session_id");
        boolean result = friendService.acceptFriend(sessionId, playerId);

        if (result) {
            return "SUCCESS";
        } else {
            return "FAILED"; // 요청 수락 중 오류 발생
        }
    }
}
