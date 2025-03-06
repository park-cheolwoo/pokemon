package kr.co.pokemon.data.dto;

import java.util.List;
import java.util.Optional;

public interface FlavorTextGroup {

	List<FlavorTextEntryDTO> getFlavorTextEntries();
	
	default Optional<String> getLanguagesFlavorText(String languageName) {
		return getFlavorTextEntries().stream()
				.filter(flavor -> flavor.getLanguage().getName().equals(languageName))
				.findFirst().map(FlavorTextEntryDTO::getFlavorText);
	}
}
