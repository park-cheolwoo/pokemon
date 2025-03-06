package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.APIResponseDTO;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.AttackService;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.TypesRelationshipService;
import kr.co.pokemon.pokemon.service.TypesService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService apiService;

	
	@GetMapping(value = "/evolution/trigger")
	public APIResponseDTO getEvolutionTrigger() {
		return apiService.setData("/evolution-trigger", EvolutionTriggerService.class);
	}

	@GetMapping(value = "/egg-group")
	public APIResponseDTO getEggGroup() {
		return apiService.setData("/egg-group", EggGroupService.class);
	}
	
	@GetMapping(value = "/habitat")
	public APIResponseDTO getHabitat() {
		return apiService.setData("/pokemon-habitat", HabitatService.class);
	}
	
	@GetMapping(value = "/ability")
	public APIResponseDTO getAbility() {
		return apiService.setData("/ability", AbilityService.class);
	}
	
	@GetMapping(value = "/type")
	public APIResponseDTO getType() {
		return apiService.setData("/type", TypesService.class);
	}
	
	@GetMapping(value = "/type/relationship")
	public APIResponseDTO getTypeRelationship() {
		return apiService.setData("/type", TypesRelationshipService.class);
	}

	@GetMapping(value = "/attack")
	public APIResponseDTO getAttack() {
		return apiService.setData("/move", AttackService.class);
	}
	
}
