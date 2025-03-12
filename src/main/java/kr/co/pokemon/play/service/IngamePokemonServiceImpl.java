package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dao.IngamePokemonMapper;
import kr.co.pokemon.play.dto.CreatedPokemonDTO;
import kr.co.pokemon.play.dto.IngamePokemonDTO;

@Service
public class IngamePokemonServiceImpl implements IngamePokemonService {
	
	private DBTables dbTable = DBTables.INGAME_POKEMON;
	
	@Autowired
	private IngamePokemonMapper ingamePokemonMapper;

	@Override
	public List<IngamePokemonDTO> getAll(PageRequestDTO page) {
		return ingamePokemonMapper.selectAll(page);
	}

	@Override
	public IngamePokemonDTO getById(int id) {
		return ingamePokemonMapper.selectById(id);
	}

	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return null;
	}

	@Override
	public void insert(IngamePokemonDTO dto) {
		ingamePokemonMapper.insert(dto);
	}

	@Override
	public List<CreatedPokemonDTO> getIngamePokemons(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveIngamePokemons(List<Integer> ownPokemonIds) {
		// TODO Auto-generated method stub
		return false;
	}

}
