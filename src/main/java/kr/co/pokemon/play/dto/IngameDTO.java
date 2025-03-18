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
public class IngameDTO extends EntityDTO {
    private String playerId;
    private boolean isIngame;
    private int stageId;
    private int maxStageId;
    private int selectionIdx;
    private GameStageDTO stage;
}
