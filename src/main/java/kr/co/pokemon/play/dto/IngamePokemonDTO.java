package kr.co.pokemon.play.dto;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngamePokemonDTO extends EntityDTO {

	private String playerId;
	private int hp;
	private int slot;
	
	private String name;
	private String image;
	private int pokemonId;
	
}
