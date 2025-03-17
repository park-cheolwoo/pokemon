package kr.co.pokemon.play.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngameInfoDTO extends IngameDTO {

	private List<CreatedPokemonDTO> myPokemons;
	private List<CreatedPokemonDTO> enemies;

	public IngameInfoDTO(IngameDTO ingameDTO, List<CreatedPokemonDTO> myPokemons, List<CreatedPokemonDTO> enemies) {
		super(ingameDTO.getPlayerId(), ingameDTO.isIngame(), ingameDTO.getStageId(), ingameDTO.getMaxStageId(), ingameDTO.getSelectionIdx(), ingameDTO.getStage());
		this.myPokemons = myPokemons;
		this.enemies = enemies;
	}
}
