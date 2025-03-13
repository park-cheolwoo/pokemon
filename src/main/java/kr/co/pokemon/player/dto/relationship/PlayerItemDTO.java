package kr.co.pokemon.player.dto.relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerItemDTO extends EntityDTO{
	
    private String playerId;
    private int itemId;
    private int count;
    
}
