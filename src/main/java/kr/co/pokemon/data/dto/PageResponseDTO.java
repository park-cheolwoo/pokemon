package kr.co.pokemon.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageResponseDTO<T> {

	private int status;
	private int count;
	private List<T> data;

}
