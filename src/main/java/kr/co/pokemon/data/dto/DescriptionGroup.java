package kr.co.pokemon.data.dto;

import java.util.List;
import java.util.Optional;

public interface DescriptionGroup {
	
	List<DescriptionsDTO> getDescriptions();
	
	default Optional<String> getLanguagesDescription(String languageName) {
		return getDescriptions().stream()
				.filter(effect -> effect.getLanguage().getName().equals(languageName))
				.findFirst().map(DescriptionsDTO::getDescription);
	}

}
