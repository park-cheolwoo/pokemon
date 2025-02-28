package kr.co.pokemon.player.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDTO {

	private String id;
	private String nickname;
	private String tag;
	private String profile;
	private String password;
	private String description;
	private int lv;
	private int experience;
	private int gameMoney;
	private int realMoney;
	
	private Timestamp updatedAt;
	private Timestamp createdAt;
	
}
