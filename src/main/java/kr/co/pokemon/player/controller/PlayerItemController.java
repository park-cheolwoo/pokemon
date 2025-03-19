package kr.co.pokemon.player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.player.dto.PlayerOwnItem;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;
import kr.co.pokemon.player.service.PlayerItemService;

@RestController
@RequestMapping("/player/items")
public class PlayerItemController {

    @Autowired
    private PlayerItemService playerItemService;

    @GetMapping
    public ResponseEntity<List<PlayerItemDTO>> getAllPlayerItems(PageRequestDTO page) {
        return new ResponseEntity<>(playerItemService.getAll(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerItemDTO> getPlayerItemById(@PathVariable int id) {
        return new ResponseEntity<>(playerItemService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerItemDTO>> getPlayerItemsByPlayerId(@PathVariable String playerId) {
        return new ResponseEntity<>(playerItemService.getByPlayerId(playerId), HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<PlayerItemDTO>> getPlayerItemsByItemId(@PathVariable int itemId) {
        return new ResponseEntity<>(playerItemService.getByItemId(itemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addPlayerItem(@RequestBody PlayerItemDTO playerItem) {
        playerItemService.addItem(playerItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> addPlayerItems(@RequestBody List<PlayerItemDTO> playerItems) {
        playerItemService.addItems(playerItems);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updatePlayerItem(@RequestBody PlayerItemDTO playerItem) {
        playerItemService.updateItem(playerItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePlayerItem(@PathVariable int id) {
        playerItemService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my-items")
    public ResponseEntity<List<PlayerItemDTO>> getMyItems(HttpServletRequest request) {
        String playerId = (String) request.getSession().getAttribute("session_id"); // 세션에서 플레이어 ID 가져오기
        List<PlayerItemDTO> items = playerItemService.getByPlayerId(playerId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/my-items/info")
    public ResponseEntity<List<PlayerOwnItem>> getItemsInfo(HttpServletRequest request) {
        String playerId = (String) request.getSession().getAttribute("session_id");
        if (playerId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return new ResponseEntity<>(playerItemService.getItemsInfoByPlayerId(playerId), HttpStatus.OK);
    }
}