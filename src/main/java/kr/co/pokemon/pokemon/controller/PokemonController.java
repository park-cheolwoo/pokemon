package kr.co.pokemon.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@Controller
@RequestMapping(value = "/play")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	@GetMapping(value = "/battle1")
	public String battle() {
		return "play/battle1";
	}
	
	/**
	 * 플레이어가 소유한 포켓몬 리스트를 반환합니다.
	 * 소유하지 않은 포켓몬은 isOwned 필드가 false로 설정됩니다.
	 * 
	 * @param playerId 플레이어 ID
	 * @return 소유 여부가 포함된 포켓몬 리스트
	 */
	@GetMapping(value = "/pokemons/player/{playerId}")
	@ResponseBody
	public ResponseEntity<List<PokemonDTO>> getPokemonListWithOwnership(@PathVariable Long playerId) {
		List<PokemonDTO> pokemonList = pokemonService.getPokemonListWithOwnership(playerId);
		return ResponseEntity.ok(pokemonList);
	}
}
