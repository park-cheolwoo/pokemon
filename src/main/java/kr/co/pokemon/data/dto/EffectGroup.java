package kr.co.pokemon.data.dto;

import java.util.List;
import java.util.Optional;

public interface EffectGroup {
	
	List<EffectEntryDTO> getEffectEntries();
	
	default Optional<String> getLanguagesEffect(String languageName) {
		return getEffectEntries().stream()
				.filter(effect -> effect.getLanguage().getName().equals(languageName))
				.findFirst()
				.map(EffectEntryDTO::getEffect);
	}
}
