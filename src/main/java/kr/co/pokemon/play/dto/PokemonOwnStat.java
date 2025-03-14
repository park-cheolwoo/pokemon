package kr.co.pokemon.play.dto;

import kr.co.pokemon.pokemon.dto.StatDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonOwnStat extends StatDTO {

	private int value;
	private int total;

}
