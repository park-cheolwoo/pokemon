package kr.co.pokemon.pros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.player.dto.PlayerDTO;
import kr.co.pokemon.player.service.PlayerService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@RequestMapping("/admin")
@RestController
public class ProsRestController {

	@Autowired
	PokemonService pokemonService;
	@Autowired
	PlayerService playerService;
	
	@ResponseBody
	@PostMapping(value = "/pokemon/{page}")
	public List<PokemonDTO> addPokemon(PageRequestDTO pDTO) {
		pDTO.setSize(96);
		return pokemonService.getAll(pDTO);
	}
	
	@ResponseBody
	@PostMapping(value = "/player/{page}")
	public List<PokemonDTO> addPlayer(PageRequestDTO pDTO) {
		pDTO.setSize(96);
		return pokemonService.getAll(pDTO);
	}
	

	@ResponseBody
	@PostMapping(value = "/pokemon/search/{keyword}")
	public List<PokemonDTO> searchPokemon(String keyword){
		return pokemonService.getByName(keyword);
	}
	
	@ResponseBody
	@PostMapping(value = "/player/search/{keyword}")
	public List<PlayerDTO> searchPlayer(String keyword){
		List<PlayerDTO> list = playerService.getByNickname(keyword);
		return list;
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

	
	
}
