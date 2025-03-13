package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.pokemon.dao.relationship.PokemonAttackMapper;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonMoveDTO;

@Service
public class PokemonMoveServiceImpl implements PokemonMoveService {
	
	private DBTables dbTable = DBTables.POKEMON_ATTACK;
	
	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private AttackService attackService;
	
	@Autowired
	private PokemonAttackMapper pokemonAttackMapper;

	@Override
	public int insertDataFromAPI(List<PokemonMoveDTO> list) throws Exception {
		
		List<PokemonMoveDTO> pokemonMoves = new ArrayList<>();

		list.stream().forEach(pokemon -> {
			if (pokemonService.existById(pokemon.getId())) {
				pokemon.getMoves().stream().forEach(move -> {
					int attackId = APIService.getIdByUrl(move.getMove().getUrl());
					
					if (attackService.existById(attackId)) {
						pokemonMoves.add(new PokemonMoveDTO(pokemon.getId(), attackId, move.getLearnedLevel("level-up")));
						
					}
				});
				
			}
		});

		int count = 0;
		int size = 1000;
		while (pokemonMoves.size() > count * size) {
			pokemonAttackMapper.insertAll(pokemonMoves.stream().skip(count * size).limit(size).toList());
			count++;
		}

		return pokemonMoves.size();
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
		return pokemonAttackMapper.selectAll(page);
	}

	@Override
	public PokemonMoveDTO getById(int id) {
		return pokemonAttackMapper.selectById(id);
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return pokemonAttackMapper.selectByIds(ids);
	}

	@Override
	public void insert(PokemonMoveDTO dto) {
		pokemonAttackMapper.insert(dto);
	}

	@Override
	public List<PokemonOwnAttack> getAttacksByPokemonId(int pokemonId) {
		return pokemonAttackMapper.selectAttackByPokemonId(pokemonId);
	}
	
	@Override
	public List<PokemonDTO> getPokemonByAttackId(int attackId) {
		return pokemonAttackMapper.selectPokemonByAttackId(attackId);
	}
	
	@Override
	public boolean existAttackAndPokemonId(int pokemonId, int attackId) {
		Map<String, Integer> existMap = new HashMap<>();
		existMap.put("pokemonId", pokemonId);
		existMap.put("attackId", attackId);

		return pokemonAttackMapper.existAttackAndPokemonId(existMap) != 0;
	}
	
}
