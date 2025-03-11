package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.StatService;
import kr.co.pokemon.pokemon.service.TypesService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService apiService;

	
	@GetMapping(value = "/evolution/trigger")
	public APIResponseDTO getEvolutionTrigger() {
		return apiService.setData(EvolutionTriggerService.class);
	}

	@GetMapping(value = "/egg-group")
	public APIResponseDTO getEggGroup() {
		return apiService.setData(EggGroupService.class);
	}
	
	@GetMapping(value = "/habitat")
	public APIResponseDTO getHabitat() {
		return apiService.setData(HabitatService.class);
	}
	
	@GetMapping(value = "/ability")
	public APIResponseDTO getAbility() {
		return apiService.setData(AbilityService.class);
	}
	
	@GetMapping(value = "/type")
	public APIResponseDTO getType() {
		return apiService.setData(TypesService.class);
	}
	
	@GetMapping(value = "/attack")
	public APIResponseDTO getAttack() {
		return apiService.setData(AttackService.class);
	}
	
	@GetMapping(value = "/stat")
	public APIResponseDTO getStat() {
		return apiService.setData(StatService.class);
	}
		
	@GetMapping(value = "/characteristic")
	public APIResponseDTO getCharacteristic() {
		return apiService.setData(CharacteristicService.class);
	}

	@GetMapping(value = "/item")
	public APIResponseDTO getItem() {
		return apiService.setData(ItemService.class);
	}

	@GetMapping(value = "/item-category")
	public APIResponseDTO getItemCategory() {
		return apiService.setData(ItemCategoryService.class);
	}
	
	@GetMapping(value = "/evolution/{part}")
	public APIResponseDTO getEvolution(@PathVariable int part) {
		return apiService.setData(EvolutionService.class, part);
	}
	
}
