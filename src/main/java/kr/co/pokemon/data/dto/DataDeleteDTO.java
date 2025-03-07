package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataDeleteDTO {

	private String tableName;
	private int start;
	private int end;
}
