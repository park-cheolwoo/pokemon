package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.service.APIServiceImpl;
import kr.co.pokemon.pokemon.dao.CharacteristicMapper;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

	@Autowired
	private CharacteristicMapper characteristicMapper;
	
	@Override
	public List<CharacteristicDTO> getAll(PageRequestDTO page) {
		return characteristicMapper.selectAll(page);
	}

	@Override
	public CharacteristicDTO getById(int id) {
		return characteristicMapper.selectById(id);
	}

	@Override
	public int getDataFromAPI(CharacteristicDTO dto) throws Exception {
		int statId = APIServiceImpl.getIdByUrl(dto.getHighestStat().getUrl());
		dto.setStatId(statId);
		
		dto.getLanguagesDescription("ko").ifPresentOrElse(description ->
			dto.setDescription(description),
			() -> dto.setDescription("NO-TEXT")
		);
		
		characteristicMapper.insert(dto);
		return 1;
	}

}
