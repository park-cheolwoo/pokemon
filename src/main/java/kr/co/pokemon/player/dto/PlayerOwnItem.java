package kr.co.pokemon.player.dto;

import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.player.dto.relationship.PlayerItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerOwnItem extends PlayerItemDTO {

	private ItemDTO info;

	public PlayerOwnItem(PlayerItemDTO playerItemDTO, ItemDTO itemDTO) {
		super(playerItemDTO.getPlayerId(), playerItemDTO.getItemId(), playerItemDTO.getCount());
		this.setId(playerItemDTO.getId());
		this.info = itemDTO;
	}

}
