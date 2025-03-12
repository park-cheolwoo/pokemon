package kr.co.pokemon.pokemon.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PokemonDTO extends EntityDTO {

	private String name;
	private String image;
	private String image3d;
	private int genderRate;
	private boolean genderDiff;
	private String genus;
	private int height;
	private int weight;
	private String flavorText;
	private boolean isLegendary;
	private boolean isMythical;
	private boolean isActive;
	private int evolutionId;

	private APIPageResultDTO species;

	private PokemonSprites sprites;
	
	private List<Stats> stats;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class PokemonSprites {
		
		@JsonIgnore
		private int id;

		@JsonProperty(value = "back_default")
		private String backDefault;
		@JsonProperty(value = "back_female")
		private String backFemale;
		@JsonProperty(value = "back_shiny")
		private String backShiny;
		@JsonProperty(value = "back_shiny_female")
		private String backShinyFemale;
		@JsonProperty(value = "front_default")
		private String frontDefault;
		@JsonProperty(value = "front_female")
		private String frontFemale;
		@JsonProperty(value = "front_shiny")
		private String frontShiny;
		@JsonProperty(value = "front_shiny_female")
		private String frontShinyFemale;

		private Other other;
		

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Other {
		
		private PokemonSprites home;
		
		@JsonProperty(value = "official-artwork")
		private PokemonSprites officialArtwork;
		
		private PokemonSprites showdown;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Stats {
		
		@JsonProperty(value = "base_stat")
		private int baseStat;
		private int effort;
		private APIPageResultDTO stat;

	}

}
