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
public class OwnPokemonSkill extends EntityDTO {

	private int playerPokemonId;
	private int skillId;
	private int slot;

}
