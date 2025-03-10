package kr.co.pokemon.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataDeleteDTO {

	private String tableName;
	private List<Integer> ids;
}
