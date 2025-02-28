package kr.co.pokemon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	private APIService dataService;
	
	@Autowired
	private EvolutionTriggerService evolutionService;
	
	@GetMapping(value = "/evolution/trigger")
	public boolean getEvolutionTrigger() {
		return dataService.setData("/evolution-trigger", EvolutionTriggerDTO.class, evolutionService);
	}
	
}
