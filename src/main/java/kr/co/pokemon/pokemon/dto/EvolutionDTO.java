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
public class EvolutionDTO extends EntityDTO {

	private Integer evolutionId;
	private Integer prevId;
	private Integer currId;
	private Integer nextId;

	public EvolutionDTO(Integer evolutionId, Integer prevId, Integer currId, Integer nextId) {
		this.evolutionId = evolutionId;
		this.prevId = prevId;
		this.currId = currId;
		this.nextId = nextId;
	}

	private EvolutionChain chain;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class EvolutionChain {
		
		@JsonProperty(value = "evolution_details")
		private List<EvolutionDetail> evolutionDetails;
		
		@JsonProperty(value = "evolves_to")
		private List<EvolutionChain> evolvesTo;
		
		@JsonProperty(value = "is_baby")
		private boolean isBaby;
		
		private APIPageResultDTO species;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class EvolutionDetail {
			
			@JsonProperty(value = "min_level")
			private int minLevel;
			
			private APIPageResultDTO item;

			private APIPageResultDTO trigger;

		}
	}

}
