package kr.co.pokemon.data.dto;

import java.util.List;
import java.util.Optional;

public interface NamesGroup {

	List<LanguageNameDTO> getNames();
	
	default Optional<String> getLanguagesName(String languageName) {
		return getNames().stream()
				.filter(name -> name.getLanguage().getName().equals(languageName))
				.findFirst().map(LanguageNameDTO::getName);
	}
}
