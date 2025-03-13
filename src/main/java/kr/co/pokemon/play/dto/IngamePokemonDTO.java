package kr.co.pokemon.play.dto;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngamePokemonDTO extends EntityDTO {

	private int playerId;
	private int hp;
	private CreatedPokemonDTO pokemon;
	private int slot;
	
}
