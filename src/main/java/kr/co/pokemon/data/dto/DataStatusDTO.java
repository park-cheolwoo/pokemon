package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataStatusDTO<T> {

	private String status;
	private T data;
	private String error;
	
	public DataStatusDTO(String status, T data) {
		this.status = status;
		this.data = data;
	}
}
