package kr.co.pokemon.player.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FriendDTO {

	private Long id;
	private PlayerDTO playerFrom;
	private PlayerDTO playerTo;
	
	private boolean isAccept;
	
	private Timestamp updatedAt;
	private Timestamp createdAt;
	
}
