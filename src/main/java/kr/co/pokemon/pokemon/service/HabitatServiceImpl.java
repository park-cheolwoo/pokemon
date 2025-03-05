package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dao.HabitatMapper;
import kr.co.pokemon.pokemon.dto.HabitatDTO;

@Service
public class HabitatServiceImpl implements HabitatService {

	@Autowired
	HabitatMapper habitatMapper;
	
	@Override
	public List<HabitatDTO> getAll(PageRequestDTO page) {
		return habitatMapper.selectAll(page);
	}

	@Override
	public HabitatDTO getById(int id) {
		return habitatMapper.selectById(id);
	}

	@Override
	public int getDataFromAPI(HabitatDTO dto) throws Exception {
		if (habitatMapper.existById(dto.getId()) == 0) {
			dto.setOriginalName(dto.getName());
			habitatMapper.insert(dto);
			
			return 1;
		}
		
		return 0;
	}

}
