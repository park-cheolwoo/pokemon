package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.HabitatService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService dataService;
	
	@Autowired
	private EvolutionTriggerService evolutionTriggerService;
	
	@Autowired
	private EggGroupService eggGroupService;
	
	@Autowired
	private HabitatService habitatService;
	
	@GetMapping(value = "/evolution/trigger")
	public boolean getEvolutionTrigger() {
		return dataService.setData("/evolution-trigger", EvolutionTriggerDTO.class, evolutionTriggerService);
	}

	@GetMapping(value = "/egg-group")
	public boolean getEggGroup() {
		return dataService.setData("/egg-group", EggGroupDTO.class, eggGroupService);
	}
	
	@GetMapping(value = "/habitat")
	public boolean getHabitat() {
		return dataService.setData("/pokemon-habitat", HabitatDTO.class, habitatService);
	}
	
}
