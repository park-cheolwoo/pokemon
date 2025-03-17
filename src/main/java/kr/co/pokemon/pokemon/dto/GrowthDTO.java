package kr.co.pokemon.pokemon.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.APIPageResultDTO;
import kr.co.pokemon.data.dto.DescriptionGroup;
import kr.co.pokemon.data.dto.DescriptionsDTO;
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
public class GrowthDTO extends EntityDTO implements DescriptionGroup {

	private String name;
	
	private List<TotalExperience> levels;
	
	private List<DescriptionsDTO> descriptions;

	@JsonProperty(value = "pokemon_species")
	private List<APIPageResultDTO> pokemonSpecies;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TotalExperience extends EntityDTO {

		private int growthId;
		private int level;
		private int experience;

	}

}
