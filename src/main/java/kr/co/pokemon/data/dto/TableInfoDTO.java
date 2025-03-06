package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableInfoDTO extends TimeDTO {

	private String name;
	private int count;

}
