package kr.co.pokemon.play.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.pokemon.pokemon.dto.TypesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonOwnType extends TypesDTO {

	private int slot;

	private List<DamageEffect> damageTo;
	private List<DamageEffect> damageFrom;

	@Getter
	@Setter
	@AllArgsConstructor
	public static class DamageEffect {
		private int type;
		private int damage;
	}

}