package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.TypesMapper;
import kr.co.pokemon.pokemon.dao.TypesRelationshipMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonTypesMapper;
import kr.co.pokemon.pokemon.dto.TypesDTO;
import kr.co.pokemon.pokemon.dto.TypesDTO.DamageRelations;
import kr.co.pokemon.pokemon.dto.relationship.PokemonTypesDTO;
import kr.co.pokemon.pokemon.dto.relationship.TypesRelationshipDTO;

@Service
public class TypesServiceImpl implements TypesService {
	
	private final DBTables dbTable = DBTables.TYPES;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private PokemonService pokemonService;

	@Autowired
	private TypesMapper typesMapper;
	
	@Autowired
	private TypesRelationshipMapper typesRelationshipMapper;
	
	@Autowired
	private PokemonTypesMapper pokemonTypesMapper;
	
	@Override
	public List<TypesDTO> getAll(PageRequestDTO page) {
		return typesMapper.selectAll(page);
	}

	@Override
	public TypesDTO getById(int id) {
		return typesMapper.selectById(id);
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return typesMapper.selectByIds(ids);
	}

	public TypesDTO getByName(String originalName) {
		return typesMapper.selectByName(originalName);
	}

	@Override
	public int insertDataFromAPI(List<TypesDTO> list) throws Exception {
		List<TypesRelationshipDTO> relationships = new ArrayList<>();
		List<PokemonTypesDTO> pokemonRelationships = new ArrayList<>();

		list.stream().forEach(dto -> {
			dto.setOriginalName(dto.getName());
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			
			DamageRelations dr = dto.getDamageRelations();
			
			dr.getDoubleDamageTo().stream()
			.forEach(types -> {
				int toId = APIService.getIdByUrl(types.getUrl());
				TypesRelationshipDTO relationship = new TypesRelationshipDTO(dto.getId(), toId, 2);
				relationships.add(relationship);
			});
			
			dr.getHalfDamageTo().stream()
			.forEach(types -> {
				int toId = APIService.getIdByUrl(types.getUrl());
				TypesRelationshipDTO relationship = new TypesRelationshipDTO(dto.getId(), toId, 1);
				relationships.add(relationship);
			});
			
			dr.getNoDamageTo().stream()
			.forEach(types -> {
				int toId = APIService.getIdByUrl(types.getUrl());
				TypesRelationshipDTO relationship = new TypesRelationshipDTO(dto.getId(), toId, 0);
				relationships.add(relationship);
			});	
			
			dto.getPokemon().stream().forEach(poke -> {
				int pokemonId = APIService.getIdByUrl(poke.getPokemon().getUrl());
				
				if (pokemonService.existById(pokemonId)) {
					PokemonTypesDTO pokemonRelationship = new PokemonTypesDTO(pokemonId, dto.getId(), poke.getSlot());
					pokemonRelationships.add(pokemonRelationship);
					
				}
			});
		});
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			typesMapper.insertAll(list);

			if (dataService.deleteAllData(DBTables.TYPES_RELATIONSHIP.getTableName())) {
				typesRelationshipMapper.insertAll(relationships);
				
			} else {
				throw new IllegalArgumentException(DBTables.TYPES_RELATIONSHIP.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}
			
			if (dataService.deleteAllData(DBTables.POKEMON_TYPES.getTableName())) {
				if (pokemonRelationships.size() > 0) {
					pokemonTypesMapper.insertAll(pokemonRelationships);
					
				}

			} else {
				throw new IllegalArgumentException(DBTables.POKEMON_TYPES.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}
			
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
	public void insert(TypesDTO dto) {
		typesMapper.insert(dto);
	}
	
	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

}
