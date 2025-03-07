package kr.co.pokemon.pokemon.dto;

import java.util.List;

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
	private int evolutionId;

	private APIPageResultDTO species;

	private PokemonSprites sprites;
	
	private List<Stats> stats;
	
	private List<Types> types;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class PokemonSprites {

		private String back_default;
		private String back_female;
		private String back_shiny;
		private String back_shiny_female;
		private String front_default;
		private String front_female;
		private String front_shiny;
		private String front_shiny_female;

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
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Types {
		
		private int slot;
		private APIPageResultDTO type;
		
	}

}
