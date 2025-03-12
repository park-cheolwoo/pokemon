package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EffectEntryDTO {
	private String effect;
	private APIPageResultDTO language;
	
	@JsonProperty(value = "short_effect")
	private String shortEffect;
	
	@Override
	public String toString() {
		return String.format("EffectEntry(language=%s, effect='%s', shortEffect='%s')", 
			language != null ? language.getName() : "null",
			effect,
			shortEffect);
	}
}