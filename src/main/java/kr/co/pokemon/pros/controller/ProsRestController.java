package kr.co.pokemon.pros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@RequestMapping("/admin")
@RestController
public class ProsRestController {

	@Autowired PokemonService pokemonService;
	
	@ResponseBody
	@PostMapping(value="/pokemon/{page}")
	public List<PokemonDTO> addPokemon(PageRequestDTO pDTO) {
		System.out.println("----- 서비스단 ------");
		pDTO.setSize(96);
		System.out.println("page : "+pDTO.getPage());
		System.out.println("size : "+pDTO.getSize());
		List<PokemonDTO> list = pokemonService.getAll(pDTO);
		return list;
	}
	
	@ResponseBody
	@PostMapping(value="/pokemon/search/{keyword}")
	public List<PokemonDTO> searchPokemon(String keyword) {
		System.out.println("name : "+keyword);
		List<PokemonDTO> list = pokemonService.getByName(keyword);
		List<Evolution>
		return list;
	}
	
	
	
	
}
