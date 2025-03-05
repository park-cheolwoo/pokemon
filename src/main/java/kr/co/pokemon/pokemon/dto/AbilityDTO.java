package kr.co.pokemon.pokemon.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.LanguageDTO;
import kr.co.pokemon.data.dto.LanguageNameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbilityDTO {

	private int id;
	private String name;
	private String description;
	private String flavorText;
	private boolean isActive;
	private Timestamp updatedAt;
	private Timestamp createdAt;

	private List<LanguageNameDTO> names;
	
	@JsonProperty(value = "effect_entries")
	private List<EffectEntry> effectEntries;
	
	@JsonProperty(value = "flavor_text_entries")
	private List<FlavorTextEntry> flavorTextEntries;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class EffectEntry {
		private String effect;
		private LanguageDTO language;
		
		@JsonProperty(value = "short_effect")
		private String shortEffect;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FlavorTextEntry {
		@JsonProperty(value = "flavor_text")
		private String flavorText;
		private LanguageDTO language;
	}
	
	public Optional<String> getLanguagesName(String languageName) {
		return names.stream()
				.filter(name -> name.getLanguage().getName().equals(languageName))
				.findFirst().map(LanguageNameDTO::getName);
	}
	
	public Optional<String> getLanguagesEffect(String languageName) {
		return this.effectEntries.stream()
				.filter(effect -> effect.getLanguage().getName().equals(languageName))
				.findFirst().map(effectEntry -> effectEntry.getEffect() + "(" + effectEntry.getShortEffect() + ")");
	}
	
	public Optional<String> getLanguagesFlavorText(String languageName) {
		return this.flavorTextEntries.stream()
				.filter(flavor -> flavor.getLanguage().getName().equals(languageName))
				.findFirst().map(FlavorTextEntry::getFlavorText);
	}

}
