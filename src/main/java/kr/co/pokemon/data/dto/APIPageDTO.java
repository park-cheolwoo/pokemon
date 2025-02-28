package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIPageDTO {

	private int currPage = 0;
	private int count;
	private String next;

	public int nextPage() {
		this.currPage += 1;
		return this.currPage;
	}

}
