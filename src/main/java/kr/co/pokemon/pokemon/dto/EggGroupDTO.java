package kr.co.pokemon.pokemon.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.pokemon.data.dto.LanguageNameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EggGroupDTO {

	private int id;
	private String name;
	private List<LanguageNameDTO> names;
	private Timestamp updatedAt;
	private Timestamp createdAt;
	
	public Optional<String> getLanguagesName(String languageName) {
		return names.stream()
				.filter(name -> name.getLanguage().getName().equals(languageName))
				.findFirst().map(LanguageNameDTO::getName);
	}

}
