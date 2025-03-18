package kr.co.pokemon.play.dto;
import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngameEnemyDTO extends EntityDTO {
  private String playerId;
  private int pokemonId;
  private int hp;
  private int level;
}