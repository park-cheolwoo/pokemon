package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageDTO;
import kr.co.pokemon.pokemon.dao.AbilityMapper;
import kr.co.pokemon.pokemon.dto.AbilityDTO;

@Service
public class AbilityServiceImpl implements AbilityService {

	@Autowired
	private AbilityMapper abilityMapper;
	
	@Override
	public List<AbilityDTO> getAll(PageDTO page) {
		return abilityMapper.selectAll(page);
	}

	@Override
	public AbilityDTO getById(int id) {
		return abilityMapper.selectById(id);
	}

	@Override
	public void getDataFromAPI(AbilityDTO dto) throws Exception {
		if (abilityMapper.existById(dto.getId()) == 0) {
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

			abilityMapper.insert(dto);
		}
		
	}

}
