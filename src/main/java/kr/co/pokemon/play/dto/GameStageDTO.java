package kr.co.pokemon.play.dto;

import kr.co.pokemon.data.dto.EntityDTO;
import kr.co.pokemon.pokemon.dto.HabitatDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameStageDTO extends EntityDTO {

	private int habitatId;
	private int stage;
	private int money;
	private int experience;
	private int minLevel;
	private int maxLevel;
	
	private HabitatDTO habitat;

}