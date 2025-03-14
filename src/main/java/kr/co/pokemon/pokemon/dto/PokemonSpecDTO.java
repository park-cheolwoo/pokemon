package kr.co.pokemon.pokemon.dto;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.APIPageResultDTO;
import kr.co.pokemon.data.dto.FlavorTextEntryDTO;
import kr.co.pokemon.data.dto.FlavorTextGroup;
import kr.co.pokemon.data.dto.LanguageNameDTO;
import kr.co.pokemon.data.dto.NamesGroup;
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
public class PokemonSpecDTO implements NamesGroup, FlavorTextGroup {

	@JsonProperty(value = "gender_rate")
	private int genderRate;
	
	@JsonProperty(value = "has_gender_differences")
	private boolean genderDiff;

	@JsonProperty(value = "is_legendary")
	private boolean isLegendary;

	@JsonProperty(value = "is_mythical")
	private boolean isMythical;
	
	private List<LanguageNameDTO> names;
	
	@JsonProperty(value = "flavor_text_entries")
	private List<FlavorTextEntryDTO> flavorTextEntries;

	private List<Genus> genera;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Genus {
		
		private String genus;
		private APIPageResultDTO language;

	}
	
	public Optional<String> getLanguagesGenus(String languageName) {
		return genera.stream()
				.filter(genus -> genus.getLanguage().getName().equals(languageName))
				.findFirst().map(Genus::getGenus);
	}

}
