package kr.co.pokemon.player.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.pokemon.data.dto.EntityDTO;
import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.play.dto.PokemonOwnType;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerPokemonDTO extends EntityDTO {

	private String playerId;
	private int pokemonId;
	private String name;
	private boolean gender;
	private int level;
	private int experience;
	private int characteristicId;

	private CharacteristicDTO characteristic;
	private List<PokemonOwnAbility> abilities;
	private List<PokemonOwnAttack> attacks;
	private List<PokemonOwnStat> stats;
	private List<PokemonOwnType> types;

	private boolean isOwned;
}
