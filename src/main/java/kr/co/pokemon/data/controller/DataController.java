package kr.co.pokemon.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dto.AbilityDTO;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.HabitatService;

@RestController
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private DataService dataService;

	
	@GetMapping(value = "/evolution/trigger")
	public List<EvolutionTriggerDTO> getEvolutionTrigger(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, EvolutionTriggerService.class);
	}
	
	@GetMapping(value = "/evolution/trigger/{id}")
	public ResponseEntity<EvolutionTriggerDTO> getEvolutionTrigger(@PathVariable int id) {
		return dataService.getById(id, EvolutionTriggerService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/egg-group")
	public List<EggGroupDTO> getEggGroup(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, EggGroupService.class);
	}
	
	@GetMapping(value = "/egg-group/{id}")
	public ResponseEntity<EggGroupDTO> getEggGroup(@PathVariable int id) {
		return dataService.getById(id, EggGroupService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/habitat")
	public List<HabitatDTO> getHabitat(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, HabitatService.class);
	}
	
	@GetMapping(value = "/habitat/{id}")
	public ResponseEntity<HabitatDTO> getHabitat(@PathVariable int id) {
		return dataService.getById(id, HabitatService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/ability")
	public List<AbilityDTO> getAbility(PageDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, AbilityService.class);
	}
	
	@GetMapping(value = "/ability/{id}")
	public ResponseEntity<AbilityDTO> getAbility(@PathVariable int id) {
		return dataService.getById(id, AbilityService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
