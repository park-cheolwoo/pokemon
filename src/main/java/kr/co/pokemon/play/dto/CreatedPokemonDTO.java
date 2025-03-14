package kr.co.pokemon.play.dto;

import java.util.List;

import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreatedPokemonDTO {

	private PokemonDTO pokemon;
	private String name;
	private List<PokemonStatDTO> stats;
	private CharacteristicDTO characteristic;
	private List<PokemonOwnAttack> attacks;
	private List<PokemonOwnAbility> abilities;

}
