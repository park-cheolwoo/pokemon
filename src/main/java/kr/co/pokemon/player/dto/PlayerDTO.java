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
public class PlayerDTO {

	private String id;
	private String nickname;
	private String tag="";
	private String profile = "/images/no-profile.png";
	private String password;
	private String description;
	private int lv = 1;
	private int experience = 0;
	private int gameMoney = 0;
	private int realMoney = 0;
	private int isActive = 0;
	
	private Timestamp updatedAt;
	private Timestamp createdAt;
	
	// 추가 임시 필드
    private transient String temporaryField;
}
