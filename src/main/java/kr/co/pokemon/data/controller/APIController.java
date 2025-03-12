package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.APIResponseDTO;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.item.service.ItemCategoryService;
import kr.co.pokemon.item.service.ItemService;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.AttackService;
import kr.co.pokemon.pokemon.service.CharacteristicService;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.GrowthService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.PokemonMoveService;
import kr.co.pokemon.pokemon.service.PokemonService;
import kr.co.pokemon.pokemon.service.StatService;
import kr.co.pokemon.pokemon.service.TypesService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService apiService;

	
	@GetMapping(value = "/evolution/trigger/{part}")
	public APIResponseDTO getEvolutionTrigger(@PathVariable int part) {
		return apiService.setData(EvolutionTriggerService.class, part);
	}

	@GetMapping(value = "/egg-group/{part}")
	public APIResponseDTO getEggGroup(@PathVariable int part) {
		return apiService.setData(EggGroupService.class, part);
	}
	
	@GetMapping(value = "/habitat/{part}")
	public APIResponseDTO getHabitat(@PathVariable int part) {
		return apiService.setData(HabitatService.class, part);
	}
	
	@GetMapping(value = "/ability/{part}")
	public APIResponseDTO getAbility(@PathVariable int part) {
		return apiService.setData(AbilityService.class, part);
	}
	
	@GetMapping(value = "/type/{part}")
	public APIResponseDTO getType(@PathVariable int part) {
		return apiService.setData(TypesService.class, part);
	}

	@GetMapping(value = "/attack/{part}")
	public APIResponseDTO getAttack(@PathVariable int part) {
		return apiService.setData(AttackService.class, part);
	}
	
	@GetMapping(value = "/stat/{part}")
	public APIResponseDTO getStat(@PathVariable int part) {
		return apiService.setData(StatService.class, part);
	}
		
	@GetMapping(value = "/characteristic/{part}")
	public APIResponseDTO getCharacteristic(@PathVariable int part) {
		return apiService.setData(CharacteristicService.class, part);
	}
	
	@GetMapping(value = "/growth/{part}")
	public APIResponseDTO getGrowth(@PathVariable int part) {
		return apiService.setData(GrowthService.class, part);
	}
	
	@GetMapping(value = "/pokemon/{part}")
	public APIResponseDTO getPokemon(@PathVariable int part) {
		return apiService.setData(PokemonService.class, part);
	}

	@GetMapping(value = "/pokemon/attack/{part}")
	public APIResponseDTO getPokemonAttack(@PathVariable int part) {
		return apiService.setData(PokemonMoveService.class, part);
	}

	@GetMapping(value = "/item/{part}")
	public APIResponseDTO getItem(@PathVariable int part) {
		return apiService.setData(ItemService.class, part);
	}
	
	@GetMapping(value = "/item/category/{part}")
	public APIResponseDTO getItemCategory(@PathVariable int part) {
		return apiService.setData(ItemCategoryService.class, part);
	}
	
	@GetMapping(value = "/evolution/{part}")
	public APIResponseDTO getEvolution(@PathVariable int part) {
		return apiService.setData(EvolutionService.class, part);
	}
	
}
