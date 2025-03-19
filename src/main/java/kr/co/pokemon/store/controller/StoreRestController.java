package kr.co.pokemon.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemService;
import kr.co.pokemon.player.dao.relationship.PlayerItemMapper;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;
import kr.co.pokemon.player.service.PlayerItemService;
import kr.co.pokemon.player.service.PlayerService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/store")
public class StoreRestController {

  @Autowired
  ItemService itemService;

  @Autowired
  PlayerItemService playerItemService;

  @Autowired
  PlayerItemMapper playerItemMapper;

  @Autowired
  PlayerService playerService;

  @Autowired
  HttpSession session;

  @ResponseBody
  @PostMapping("/view/{id}")
  public ItemDTO viewItem(int id) {
    return itemService.getById(id);
  }
 
  @ResponseBody
  @PostMapping("/buy/{id}")
  public Map<String, Object> buyItem(String playerId, int itemId, int count, int cost) {
    // 유저의 현재 가격 확인
    PlayerDTO pDTO = playerService.getById(playerId);
    Map<String, Object> map = new HashMap<String, Object>();
    if (pDTO == null || pDTO.getGameMoney() < cost) {
      System.out.println("유저 정보 없음 또는 게임머니 부족");
      map.put("result", "fail");
      return map;
    }
    // 유저의 현재 소유 아이템 확인 후 추가
    PlayerItemDTO piDTO = playerItemService.getByPlayerIdAndItemId(playerId, itemId);
    if (piDTO == null) {
      PlayerItemDTO playerItem = new PlayerItemDTO();
      playerItem.setPlayerId(playerId);
      playerItem.setItemId(itemId);
      playerItem.setCount(count);
      playerItemMapper.insert(playerItem);
    } else {
      int own_count = piDTO.getCount();
      piDTO.setCount(own_count + count);
      playerItemService.updateItem(piDTO);
    }
    // 유저의 게임 머니 삭감
    int own_money = pDTO.getGameMoney();
    pDTO.setGameMoney(own_money - cost);
    playerService.updatePlayerBySystem(pDTO);
    System.out.println("gamemoney : " + pDTO.getGameMoney());
    map.put("cost", own_money - cost);
    map.put("result", "success");
    // 비밀번호 세션 저장 방지
    pDTO.setPassword("");
    session.invalidate();
    session.setAttribute("session_id", pDTO.getId());
    session.setAttribute("session_nickname", pDTO.getNickname());
    session.setAttribute("session_tag", pDTO.getTag());
    session.setAttribute("session_lv", pDTO.getLv());
    session.setAttribute("session_gameMoney", pDTO.getGameMoney());
    session.setAttribute("session_realMoney", pDTO.getRealMoney());
    return map;
  }
  
}
