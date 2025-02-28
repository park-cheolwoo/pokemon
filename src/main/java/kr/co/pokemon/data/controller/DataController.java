package kr.co.pokemon.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.HabitatService;

@RestController
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private EvolutionTriggerService evolutionTriggerService;
	
	@Autowired
	private EggGroupService eggGroupService;
	
	@Autowired
	private HabitatService habitatService;
	
	@GetMapping(value = "/evolution/trigger")
	public List<EvolutionTriggerDTO> getEvolutionTrigger(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return evolutionTriggerService.getAll(page);
	}
	
	@GetMapping(value = "/egg-group")
	public List<EggGroupDTO> getEggGroup(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return eggGroupService.getAll(page);
	}
	
	@GetMapping(value = "/habitat")
	public List<HabitatDTO> getHabitat(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return habitatService.getAll(page);
	}
	
}
