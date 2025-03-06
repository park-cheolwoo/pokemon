package kr.co.pokemon.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class EvolutionTriggerDTO extends EntityDTO {

	private String name;

}
