package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponseDTO {

	private String status;
	private int count;

}
