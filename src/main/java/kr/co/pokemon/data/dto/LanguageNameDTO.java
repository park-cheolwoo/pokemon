package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageNameDTO {
	private String name;
	private APIPageResultDTO language;
	
	@Override
	public String toString() {
		return String.format("LanguageName(language=%s, name='%s')", 
			language != null ? language.getName() : "null",
			name);
	}
}
