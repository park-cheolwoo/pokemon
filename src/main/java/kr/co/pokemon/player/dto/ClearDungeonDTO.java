package kr.co.pokemon.player.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClearDungeonDTO {

	String playerId;
	int gold;
	int experience;

}
