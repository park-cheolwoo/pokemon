package kr.co.pokemon.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class questDTO {
	private String id;
	private String day_quest1;
	private int day_quest1_clear;
	private String week_quest1;
	private int week_quest1_clear;
	private String total_quest1;
	private int total_quest1_clear;
}
