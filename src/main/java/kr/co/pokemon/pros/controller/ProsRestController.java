package kr.co.pokemon.pros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemService;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/admin")
@RestController
public class ProsRestController {

	@Autowired
	PokemonService pokemonService;
	@Autowired
	PlayerService playerService;
	@Autowired
	ItemService itemService;
	
	@ResponseBody
	@PostMapping(value = "/pokemon/{page}")
	public List<PokemonDTO> addPokemon(@RequestParam(defaultValue="1") int page) {
		return pokemonService.getAll(new PageRequestDTO(96,page));
	}
	
	@ResponseBody
	@PostMapping(value = "/player/{page}")
	public List<PlayerDTO> addPlayer(@RequestParam(defaultValue="1") int page) {
		return playerService.getAll(new PageRequestDTO(96,page));
	}
	
	@ResponseBody
	@PostMapping(value = "/item/{page}")
	public List<ItemDTO> addItem(@RequestParam(defaultValue="1") int page) {
		return itemService.getAll(new PageRequestDTO(96,page));
	}
	
	@ResponseBody
	@PostMapping(value = "/pokemon/search/{keyword}")
	public List<PokemonDTO> searchPokemon(String keyword){
		return pokemonService.getByName(keyword);
	}
	
	@ResponseBody
	@PostMapping(value = "/player/search/{keyword}")
	public List<PlayerDTO> searchPlayer(String keyword){
		return playerService.getByNickname(keyword);
	}
	
	@ResponseBody
	@PostMapping(value = "/player/view/{id}")
	public PlayerDTO findPlayer(String id){
		return playerService.getById(id);
	}
	
	@ResponseBody
	@PostMapping(value = "/pokemon/view/{id}")
	public PokemonDTO findPokemon(int id){
		return pokemonService.getById(id);
	}

	@ResponseBody
	@PostMapping(value = "/update/player/id/{id}")
	public String updatePlayerSystem(PlayerDTO pDTO) {
		System.out.println("pDTO : " + pDTO.getId() + pDTO.getNickname() + pDTO.getProfile() + pDTO.getGameMoney()
				+ pDTO.getRealMoney() + pDTO.getIsActive());
		System.out.println(pDTO.getId() != null);
		System.out.println(!pDTO.getId().isEmpty());
		System.out.println(pDTO.getNickname() != null);
		System.out.println(!pDTO.getNickname().equals(""));
		System.out.println(pDTO.getProfile() != null);
		System.out.println(!pDTO.getProfile().equals(""));
		System.out.println(pDTO.getGameMoney() >= 0);
		System.out.println(pDTO.getRealMoney() >= 0);
		System.out.println(pDTO.getIsActive() == 1);
				if (pDTO.getId() != null && !pDTO.getId().isEmpty() &&
				pDTO.getNickname() != null && !pDTO.getNickname().equals("") &&
				pDTO.getProfile() != null && !pDTO.getProfile().equals("") &&
				pDTO.getGameMoney() >= 0 && pDTO.getRealMoney() >= 0 &&
				pDTO.getIsActive() == 1) {
			playerService.updatePlayerBySystem(pDTO);
			return "success";
		} else {
			System.out.println("컨트롤러단 유효성 검사 실패");
			return "fail";
		}

	}
	
	
}
