package kr.co.pokemon.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.item.dto.ItemCategoryDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemCategoryService;
import kr.co.pokemon.item.service.ItemService;
import kr.co.pokemon.pokemon.dto.AbilityDTO;
import kr.co.pokemon.pokemon.dto.AttackDTO;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;
import kr.co.pokemon.pokemon.dto.EvolutionDTO;
import kr.co.pokemon.pokemon.dto.EvolutionTriggerDTO;
import kr.co.pokemon.pokemon.dto.GrowthDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.StatDTO;
import kr.co.pokemon.pokemon.dto.TypesDTO;
import kr.co.pokemon.pokemon.service.AbilityService;
import kr.co.pokemon.pokemon.service.AttackService;
import kr.co.pokemon.pokemon.service.CharacteristicService;
import kr.co.pokemon.pokemon.service.EggGroupService;
import kr.co.pokemon.pokemon.service.EvolutionService;
import kr.co.pokemon.pokemon.service.EvolutionTriggerService;
import kr.co.pokemon.pokemon.service.GrowthService;
import kr.co.pokemon.pokemon.service.HabitatService;
import kr.co.pokemon.pokemon.service.PokemonService;
import kr.co.pokemon.pokemon.service.StatService;
import kr.co.pokemon.pokemon.service.TypesService;

@RestController
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private DataService dataService;
	
	@GetMapping(value = "/info")
	public List<TableInfoDTO> getAllTableInfo() {
		return dataService.getAllTableInfo();
	}
	
	@GetMapping(value = "/info/{tableName}")
	public ResponseEntity<TableInfoDTO> getTableInfo(@PathVariable String tableName) {
		tableName = tableName.replaceAll("-", "_");
		return dataService.getTableInfo(tableName)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/evolution/trigger")
	public List<EvolutionTriggerDTO> getEvolutionTrigger(PageRequestDTO page) {
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
	public List<EggGroupDTO> getEggGroup(PageRequestDTO page) {
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
	public List<HabitatDTO> getHabitat(PageRequestDTO page) {
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
	public List<AbilityDTO> getAbility(PageRequestDTO page) {
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
	
	@GetMapping(value = "/type")
	public List<TypesDTO> getTypes(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, TypesService.class);
	}
	
	@GetMapping(value = "/type/{id}")
	public ResponseEntity<TypesDTO> getTypes(@PathVariable int id) {
		return dataService.getById(id, TypesService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/attack")
	public List<AttackDTO> getAttack(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, AttackService.class);
	}

	@GetMapping(value = "/attack/{id}")
	public ResponseEntity<AttackDTO> getAttack(@PathVariable int id) {
		return dataService.getById(id, AttackService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/stat")
	public List<StatDTO> getStat(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, StatService.class);
	}
	
	@GetMapping(value = "/stat/{id}")
	public ResponseEntity<StatDTO> getStat(@PathVariable int id) {
		return dataService.getById(id, StatService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/characteristic")
	public List<CharacteristicDTO> getCharacteristic(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, CharacteristicService.class);
	}
	
	@GetMapping(value = "/characteristic/{id}")
	public ResponseEntity<CharacteristicDTO> getCharacteristic(@PathVariable int id) {
		return dataService.getById(id, CharacteristicService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/item/category")
	public List<ItemCategoryDTO> getItemCategory(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, ItemCategoryService.class);
	}
	
	@GetMapping(value = "/item/category/{id}")
	public ResponseEntity<ItemCategoryDTO> getItemCategory(@PathVariable int id) {
		return dataService.getById(id, ItemCategoryService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/item")
	public List<ItemDTO> getItem(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, ItemService.class);
	}
	
	@GetMapping(value = "/item/{id}")
	public ResponseEntity<ItemDTO> getItem(@PathVariable int id) {
		return dataService.getById(id, ItemService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/pokemon")
	public List<PokemonDTO> getPokemon(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, PokemonService.class);
	}
	
	@GetMapping(value = "/pokemon/{id}")
	public ResponseEntity<PokemonDTO> getPokemon(@PathVariable int id) {
		return dataService.getById(id, PokemonService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/growth")
	public List<GrowthDTO> getGrowth(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, GrowthService.class);
	}
	
	@GetMapping(value = "/growth/{id}")
	public ResponseEntity<GrowthDTO> getGrowth(@PathVariable int id) {
		return dataService.getById(id, GrowthService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/evolution")
	public List<EvolutionDTO> getEvolution(PageRequestDTO page) {
		if (page.getPage() < 0) page.setPage(0);
		if (page.getSize() <= 0) page.setSize(10);
		return dataService.getAll(page, EvolutionService.class);
	}
	
	@GetMapping(value = "/evolution/{id}")
	public ResponseEntity<EvolutionDTO> getEvolution(@PathVariable int id) {
		return dataService.getById(id, EvolutionService.class)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
