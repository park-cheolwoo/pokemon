package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.HabitatService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService dataService;

	
	@GetMapping(value = "/evolution/trigger")
	public boolean getEvolutionTrigger() {
		return dataService.setData("/evolution-trigger", EvolutionTriggerService.class);
	}

	@GetMapping(value = "/egg-group")
	public boolean getEggGroup() {
		return dataService.setData("/egg-group", EggGroupService.class);
	}
	
	@GetMapping(value = "/habitat")
	public boolean getHabitat() {
		return dataService.setData("/pokemon-habitat", HabitatService.class);
	}
	
	@GetMapping(value = "/ability")
	public boolean getAbility() {
		return dataService.setData("/ability", AbilityService.class);
	}
	
}
