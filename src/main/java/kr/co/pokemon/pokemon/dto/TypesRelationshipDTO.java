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
public class TypesRelationshipDTO extends EntityDTO {

	private int fromId;
	private TypesSummary fromSummary;
	private int toId;
	private TypesSummary toSummary;
	private int effect;
	
	@JsonProperty(value = "damage_relations")
	private DamageRelations damageRelations;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DamageRelations {
		
		@JsonProperty(value = "double_damage_from")
		private List<APIPageResultDTO> doubleDamageFrom;
		
		@JsonProperty(value = "double_damage_to")
		private List<APIPageResultDTO> doubleDamageTo;
		
		@JsonProperty(value = "half_damage_from")
		private List<APIPageResultDTO> halfDamageFrom;
		
		@JsonProperty(value = "half_damage_to")
		private List<APIPageResultDTO> halfDamageTo;
		
		@JsonProperty(value = "no_damage_from")
		private List<APIPageResultDTO> noDamageFrom;
		
		@JsonProperty(value = "no_damage_to")
		private List<APIPageResultDTO> noDamageTo;

	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class TypesSummary {

		private int id;
		private String name;
		private String image;

	}
	
}
