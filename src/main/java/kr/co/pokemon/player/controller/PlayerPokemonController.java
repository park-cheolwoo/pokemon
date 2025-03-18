package kr.co.pokemon.player.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.pokemon.data.dto.DataStatusDTO;
import kr.co.pokemon.player.dto.OwnPokemonSkill;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;
import kr.co.pokemon.player.service.PlayerPokemonService;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.service.PokemonService;

@RestController
@RequestMapping(value = "/player/pokemon")
public class PlayerPokemonController {

	@Autowired
	private HttpSession session;

	@Autowired
	private PlayerPokemonService playerPokemonService;
	
	@Autowired
	private PokemonService pokemonService;
	

	@GetMapping(value = "/me")
	public DataStatusDTO<List<PlayerPokemonDTO>> getOwnPokemon() {
		String session_id = (String) session.getAttribute("session_id");
		if (session_id == null) {
			return new DataStatusDTO<>("fail", null);
		}
		return new DataStatusDTO<List<PlayerPokemonDTO>>("success", playerPokemonService.getByPlayerId(session_id));
	}

	@PostMapping(value = "/save")
	public DataStatusDTO<Boolean> saveOwnPokemon(@RequestBody PlayerPokemonDTO playerPokemon) {
		try {
			String session_id = (String) session.getAttribute("session_id");

			if (session_id != null) {
				playerPokemon.setPlayerId(session_id);
				playerPokemonService.save(playerPokemon);
				return new DataStatusDTO<>("success", true);
			}
			throw new IllegalArgumentException(
					"로그인 정보와 다른 정보입니다. : " + session_id + ", " + playerPokemon.getPlayerId());
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}

	@PostMapping(value = "/update/name")
	public DataStatusDTO<Boolean> updateName(int id, String name) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			String own_id = playerPokemonService.getById(id).getPlayerId();

			if (session_id != null && own_id != null) {
				if (session_id.equals(own_id)) {
					playerPokemonService.updateName(id, name);

					return new DataStatusDTO<>("success", true);
				}
			}

			throw new IllegalArgumentException("로그인 정보와 다른 정보입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}

	@PostMapping(value = "/update/level")
	public DataStatusDTO<Boolean> updateLevel(int id, int level) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			String own_id = playerPokemonService.getById(id).getPlayerId();

			if (session_id != null && own_id != null) {
				if (session_id.equals(own_id)) {
					playerPokemonService.updateLevel(id, level);

					return new DataStatusDTO<>("success", true);
				}
			}

			throw new IllegalArgumentException("로그인 정보와 다른 정보입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}

	@PostMapping(value = "/update/experience")
	public DataStatusDTO<Boolean> updateExperience(int id, int experience) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			String own_id = playerPokemonService.getById(id).getPlayerId();

			if (session_id != null && own_id != null) {
				if (session_id.equals(own_id)) {
					playerPokemonService.updateExperience(id, experience);

					return new DataStatusDTO<>("success", true);
				}
			}

			throw new IllegalArgumentException("로그인 정보와 다른 정보입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}

	@PostMapping(value = "/save/ability")
	public DataStatusDTO<Boolean> savePokemonAbility(@RequestBody OwnPokemonSkill skill) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			String own_id = playerPokemonService.getById(skill.getPlayerPokemonId()).getPlayerId();

			if (session_id != null && own_id != null) {
				if (session_id.equals(own_id)) {
					playerPokemonService.savePokemonAbility(skill);

					return new DataStatusDTO<>("success", true);
				}
			}

			throw new IllegalArgumentException("로그인 정보와 다른 정보입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}

	@PostMapping(value = "/save/attack")
	public DataStatusDTO<Boolean> savePokemonAttack(@RequestBody OwnPokemonSkill skill) {
		try {
			String session_id = (String) session.getAttribute("session_id");
			String own_id = playerPokemonService.getById(skill.getPlayerPokemonId()).getPlayerId();

			if (session_id != null && own_id != null) {
				if (session_id.equals(own_id)) {
					playerPokemonService.savePokemonAttack(skill);

					return new DataStatusDTO<>("success", true);
				}
			}

			throw new IllegalArgumentException("로그인 정보와 다른 정보입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error", false, e.getMessage());
		}
	}
	
	@GetMapping(value = "/mine")
	public DataStatusDTO<List<String>> getMinePokemons(@RequestParam(required = false, defaultValue = "false") boolean use3d) {
	    String sessionId = (String) session.getAttribute("session_id");
	    if (sessionId == null) {
	        return new DataStatusDTO<>("fail", null, "세션 정보가 유효하지 않습니다.");
	    }

	    List<PlayerPokemonDTO> minePokemons = playerPokemonService.minePlayerId(sessionId);

	    List<String> images = minePokemons.stream()
	        .map(playerPokemon -> {
	            PokemonDTO pokemonDTO = pokemonService.minePokemonById(playerPokemon.getPokemonId());
	            if (pokemonDTO != null) {
	                return pokemonDTO.getImage3d() != null && !pokemonDTO.getImage3d().isEmpty()
	                        ? pokemonDTO.getImage3d() // 3D 이미지가 있으면 사용
	                        : pokemonDTO.getImage(); // 없으면 2D 이미지 사용
	            }
	            return null;
	        })
	        .filter(Objects::nonNull) // null 제거
	        .collect(Collectors.toList());

	    return new DataStatusDTO<>("success", images);
	}


	@PostMapping(value ="/select")
	public DataStatusDTO<Boolean> selectPokemon(@RequestBody Map<String, Integer> payload){
		try {
			String sessionId = (String) session.getAttribute("session_id");
			if(sessionId == null) {
				throw new IllegalArgumentException("유효하지 않은 세션입니다.");
			}
			
			int pokemonId = payload.get("pokemonId");
			playerPokemonService.saveSelectedPokemon(sessionId, pokemonId);
			return new DataStatusDTO<>("success",true);
		}catch(Exception e) {
			e.printStackTrace();
			return new DataStatusDTO<>("error",false,e.getMessage());
		}
	}

}
