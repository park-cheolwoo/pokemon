package kr.co.pokemon.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SdungeonDTO {
	private String id;
	private int gameMoney;
	private int dailyClearCount;
	private int weeklyClearCount;
	private int totalCount;
	private int pokemon1Id;
	private String pokemon1Name;
	private String pokemon1Img;
	private int pokemon2Id;
	private String pokemon2Name;
	private String pokemon2Img;
	private int pokemon3Id;
	private String pokemon3Name;
	private String pokemon3Img;
}
