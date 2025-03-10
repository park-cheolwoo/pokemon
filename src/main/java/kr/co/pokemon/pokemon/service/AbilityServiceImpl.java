package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.AbilityMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonAbilityMapper;
import kr.co.pokemon.pokemon.dto.AbilityDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonAbilityDTO;

@Service
public class AbilityServiceImpl implements AbilityService {

	private final DBTables dbTable = DBTables.ABILITY;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private AbilityMapper abilityMapper;
	
	@Autowired
	private PokemonAbilityMapper pokemonAbilityMapper;
	
	@Override
	public List<AbilityDTO> getAll(PageRequestDTO page) {
		return abilityMapper.selectAll(page);
	}

	@Override
	public AbilityDTO getById(int id) {
		return abilityMapper.selectById(id);
	}

	@Override
	public int insertDataFromAPI(List<AbilityDTO> list) throws Exception {
		List<PokemonAbilityDTO> relationships = new ArrayList<>();

		list.stream().forEach(dto -> {
			String languageName = "ko";
			dto.getLanguagesName(languageName).ifPresent(name -> dto.setName(name));
			dto.getLanguagesEffect("en").ifPresentOrElse(effect ->
			dto.setDescription(effect),
			() -> dto.setDescription("NO-TEXT")
					);
			dto.getLanguagesFlavorText(languageName).ifPresentOrElse(flavor ->
			dto.setFlavorText(flavor),
			() -> dto.setFlavorText("NO-TEXT")
					);
			
			dto.getPokemon().stream().forEach(poke -> {
				int pokemonId = APIService.getIdByUrl(poke.getPokemon().getUrl());
				PokemonAbilityDTO relationship = new PokemonAbilityDTO(pokemonId, dto.getId(), poke.getSlot());
				relationships.add(relationship);
			});
		});
		
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			abilityMapper.insertAll(list);
			pokemonAbilityMapper.insertAll(relationships);
			
		} else {
			throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}

		return list.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public void insert(AbilityDTO dto) {
		abilityMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
