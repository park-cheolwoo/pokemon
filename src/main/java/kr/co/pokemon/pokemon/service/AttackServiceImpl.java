package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.service.APIServiceImpl;
import kr.co.pokemon.pokemon.dao.AttackMapper;
import kr.co.pokemon.pokemon.dto.AttackDTO;

@Service
public class AttackServiceImpl implements AttackService {

	@Autowired
	AttackMapper attackMapper;
	
	@Override
	public List<AttackDTO> getAll(PageRequestDTO page) {
		return attackMapper.selectAll(page);
	}
	
	@Override
	public List<AttackDTO> getByTypeId(int typesId) {
		return attackMapper.selectByTypeId(typesId);
	}

	@Override
	public AttackDTO getById(int id) {
		return attackMapper.selectById(id);
	}

	@Override
	public int getDataFromAPI(AttackDTO dto) throws Exception {
		int typesId = APIServiceImpl.getIdByUrl(dto.getType().getUrl());
		dto.setTypesId(typesId);
		
		dto.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
		dto.getLanguagesEffect("en").ifPresentOrElse(effect ->
			dto.setDescription(effect),
			() -> dto.setDescription("NO-TEXT")
		);
		
		dto.getLanguagesFlavorText("ko").ifPresentOrElse(flavor ->
			dto.setFlavorText(flavor),
			() -> dto.setFlavorText("NO-TEXT")
		);
			
		attackMapper.insert(dto);

		return 1;
	}

}
