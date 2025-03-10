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

	private int baby_id;
	private int child_trigger_id;
	private EvolutionTriggerDTO child_trigger;
	private int child_id;
	private int adult_trigger_id;
	private EvolutionTriggerDTO adult_trigger;
	private int adult_id;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class EvolutionChain {
		
		@JsonProperty(value = "evolution_details")
		private List<EvolutionDetail> evolutionDetails;
		
		@JsonProperty(value = "evolution_to")
		private List<EvolutionChain> evolutionTo;
		
		@JsonProperty(value = "is_baby")
		private boolean isBaby;
		
		private APIPageResultDTO species;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class EvolutionDetail {
			
			private APIPageResultDTO trigger;

		}
	}

}
