package kr.co.pokemon.play.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.play.dto.IngameDTO;

@Service
public class IngameServiceImpl implements IngameService {

	private DBTables dbTable = DBTables.INGAME;

	@Override
	public List<IngameDTO> getAll(PageRequestDTO page) {
		return null;
	}

	@Override
	public IngameDTO getById(int id) {
		return null;
	}

	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return null;
	}

	@Override
	public void insert(IngameDTO dto) {
		
	}
	
	
}
