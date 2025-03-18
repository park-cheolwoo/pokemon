package kr.co.pokemon.play.dto;

import kr.co.pokemon.pokemon.dto.AttackDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PokemonOwnAttack extends AttackDTO {

	private int slot;

}