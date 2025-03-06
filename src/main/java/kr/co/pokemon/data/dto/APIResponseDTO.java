package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponseDTO {

	private String status;
	private int count;
	private String comment;
	
	public APIResponseDTO(String status, int count) {
		this.status = status;
		this.count = count;
		this.comment = null;
	}

}
