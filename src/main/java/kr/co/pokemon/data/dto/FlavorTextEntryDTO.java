package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntryDTO {
	@JsonProperty(value = "text")
	private String flavorText;
	private APIPageResultDTO language;
	
	@Override
	public String toString() {
		return String.format("FlavorText(language=%s, text='%s')", 
			language != null ? language.getName() : "null",
			flavorText);
	}
}