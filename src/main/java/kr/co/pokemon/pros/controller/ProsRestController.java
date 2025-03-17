package kr.co.pokemon.pros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import kr.co.pokemon.pokemon.dao.EvolutionMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonTypesMapper;
import kr.co.pokemon.pokemon.dto.EvolutionDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;


@RequestMapping("/admin")
@RestController
public class ProsRestController {

	@Autowired
	PokemonService pokemonService;
	@Autowired
	PlayerService playerService;
	@Autowired
	ItemService itemService;
	@Autowired
	PokemonTypesMapper pokemonTypesMapper;
	@Autowired
	EvolutionMapper evolutionMapper;

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
	public List<PlayerDTO> searchPlayer(String keyword) {
		return playerService.getByNickname(keyword);
	}
	
	@ResponseBody
	@PostMapping(value = "/item/search/{keyword}")
	public List<ItemDTO> searchItem(String keyword){
		return itemService.getByNickname(keyword);
	}
	
	@ResponseBody
	@PostMapping(value = "/player/view/{id}")
	public PlayerDTO findPlayer(String id){
		return playerService.getById(id);
	}
	
	@ResponseBody
	@PostMapping(value = "/pokemon/view/{id}")
	public Map<String, Object> findPokemon(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", "pokemon");
		map.put("pokemon", pokemonService.getById(id));
		map.put("types", pokemonTypesMapper.selectByPokemonId(id));
		EvolutionDTO eDTO = evolutionMapper.selectByCurrId(id);
		map.put("evolution", eDTO);
		if (eDTO.getPrevId() != null) {
			map.put("prev", pokemonService.getById(eDTO.getPrevId()));
		}
		if (eDTO.getNextId() != null) {
			map.put("next", pokemonService.getById(eDTO.getNextId()));
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping(value = "/item/view/{id}")
	public ItemDTO findItem(int id) {
		return itemService.getById(id);
	}

	@ResponseBody
	@PostMapping(value = "/update/player/id/{id}")
	public String updatePlayerSystem(PlayerDTO pDTO) {
		System.out.println("pDTO : " + pDTO.getId() + pDTO.getNickname() + pDTO.getProfile() + pDTO.getGameMoney()
				+ pDTO.getRealMoney() + pDTO.getIsActive());
		if (pDTO.getId() != null && !pDTO.getId().isEmpty() &&
				pDTO.getNickname() != null && !pDTO.getNickname().equals("") &&
				pDTO.getProfile() != null && !pDTO.getProfile().equals("") &&
				pDTO.getGameMoney() >= 0 && pDTO.getRealMoney() >= 0 &&
				(pDTO.getIsActive() == 1 || pDTO.getIsActive() == 0)) {
			playerService.updatePlayerBySystem(pDTO);
			return "success";
		} else {
			System.out.println("컨트롤러단 유효성 검사 실패");
			return "fail";
		}
	}
	
	@ResponseBody
	@PostMapping(value = "/update/pokemon/id/{id}")
	public String UpdatePokemon(PokemonDTO pDTO) {
		System.out.println("isActive : " + pDTO.getIsActive());
		pokemonService.updatePokemonBySystem(pDTO);
		return "success";
	}
	
	@ResponseBody
	@PostMapping(value = "/update/item/id/{id}")
	public String UpdateItem(ItemDTO iDTO) {
		itemService.UpdateItemBySystem(iDTO);
		return "success";
	}
	
}
