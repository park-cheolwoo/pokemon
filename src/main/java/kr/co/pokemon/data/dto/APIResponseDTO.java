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
	private String name;
	private int count;
	private int part;
	private int totalPart;
	private String comment;

	public APIResponseDTO(String status, String name, int count, int part, int totalPart) {
		this.status = status;
		this.name = name;
		this.count = count;
		this.part = part;
		this.totalPart = totalPart;
	}

}
