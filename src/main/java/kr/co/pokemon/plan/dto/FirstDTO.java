package kr.co.pokemon.plan.dto;

import java.util.List;

import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.pokemon.dto.CharacteristicDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirstDTO {
	private String id;   // 플레이어 아이디
	private String pokemon_id; // 받아온 포켓몬 id(이상해씨 1, 파이리 4, 꼬북이 7)
	private String pokemon_name; // 받아온 포켓몬 이름(새로 지은거 혹은 기존이름)
}
