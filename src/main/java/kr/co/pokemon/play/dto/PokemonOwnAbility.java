package kr.co.pokemon.play.dto;

import kr.co.pokemon.pokemon.dto.AbilityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonOwnAbility extends AbilityDTO {

	private int slot;

}