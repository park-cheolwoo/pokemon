package kr.co.pokemon.player.dto;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnPokemonStat extends EntityDTO {
	
	private int playerPokemonId;
	private int statId;
	private int value;
	private int total;

}
