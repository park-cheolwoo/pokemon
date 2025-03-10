package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dao.relationship.PokemonAttackMapper;
import kr.co.pokemon.pokemon.dto.relationship.PokemonAttackDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonMoveDTO;

@Service
public class PokemonMoveServiceImpl implements PokemonMoveService {
	
	private DBTables dbTable = DBTables.POKEMON_ATTACK;
	
	@Autowired
	private PokemonAttackMapper pokemonAttackMapper;

	@Override
	public int insertDataFromAPI(List<PokemonMoveDTO> list) throws Exception {
		List<PokemonAttackDTO> pokemonAttackDTOs = new ArrayList<>();
		
		list.stream().forEach(pokemon -> {
			pokemon.getMoves().stream().forEach(move -> {
				int attackId = APIService.getIdByUrl(move.getMove().getUrl());
				PokemonAttackDTO pokemonAttackDTO = new PokemonAttackDTO(pokemon.getId(), attackId, move.getLearnedLevel("level-up"));
				pokemonAttackDTOs.add(pokemonAttackDTO);
			});
		});

		int count = 0;
		int size = 1000;
		while (pokemonAttackDTOs.size() > count * size) {
			pokemonAttackMapper.insertAll(pokemonAttackDTOs.stream().skip(count * size).limit(size).toList());
			count++;
		}
		
		return pokemonAttackDTOs.size();
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public DBTables getDBTable() {
		return dbTable;
	}

	@Override
	public List<PokemonMoveDTO> getAll(PageRequestDTO page) {
		return null;
	}

	@Override
	public PokemonMoveDTO getById(int id) {
		return null;
	}

	@Override
	public void insert(PokemonMoveDTO dto) {
		
	}

}
