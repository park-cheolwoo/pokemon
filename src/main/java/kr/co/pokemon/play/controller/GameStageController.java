package kr.co.pokemon.play.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dto.GameStageDTO;
import kr.co.pokemon.play.service.GameStageService;

@RestController
@RequestMapping("/game-stage")
public class GameStageController {

	@Autowired
	private GameStageService gameStageService;
	
	@GetMapping(value = "/all")
	public List<GameStageDTO> getAll(PageRequestDTO page) {
		if (page.getPage() <= 0) page.setPage(1);
		if (page.getSize() <= 0) page.setSize(10);

		return gameStageService.getAll(page);
	}
}
