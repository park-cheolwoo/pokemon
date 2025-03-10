package kr.co.pokemon.pokemon.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.LanguageNameDTO;
import kr.co.pokemon.data.dto.NamesGroup;
import kr.co.pokemon.data.dto.APIPageResultDTO;
import kr.co.pokemon.data.dto.EntityDTO;
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
public class EggGroupDTO extends EntityDTO implements NamesGroup {

	private String name;
	private String originalName;

	private List<LanguageNameDTO> names;
	
	@JsonProperty(value = "pokemon_species")
	private List<APIPageResultDTO> pokemonSpecies;

}
