package kr.co.pokemon.play.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateIngameDTO {

	private String playerId;
	private int value;

}