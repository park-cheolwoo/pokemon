package kr.co.pokemon.pokemon.dto.relationship;

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
public class EvolutionDetail extends EntityDTO {

	private int triggerId;
	private int evolutionId;
	private int currId;
	private int minLevel;
	private int useItem;

}
