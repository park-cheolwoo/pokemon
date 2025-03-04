package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dao.EggGroupMapper;
import kr.co.pokemon.pokemon.dto.EggGroupDTO;

@Service
public class EggGroupServiceImpl implements EggGroupService {
	
	@Autowired
	private EggGroupMapper eggGroupMapper;

	@Override
	public List<EggGroupDTO> getAll(PageDTO page) {
		return eggGroupMapper.selectAll(page);
	}

	@Override
	public EggGroupDTO getById(int id) {
		return eggGroupMapper.selectById(id);
	}

	@Override
	public void getDataFromAPI(EggGroupDTO dto) throws Exception {
		if (eggGroupMapper.existById(dto.getId()) == 0) {
			dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			eggGroupMapper.insert(dto);
		}
	}

}
