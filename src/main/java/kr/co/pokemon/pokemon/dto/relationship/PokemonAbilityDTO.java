package kr.co.pokemon.pokemon.dto.relationship;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonAbilityDTO extends EntityDTO {

	private int pokemonId;
	private int abilityId;
	private int slot;
	
}
