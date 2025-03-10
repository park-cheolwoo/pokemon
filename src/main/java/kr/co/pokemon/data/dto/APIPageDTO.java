package kr.co.pokemon.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIPageDTO {

	@JsonIgnore
	private int currPage = 0;
	
	@JsonIgnore
	private int count = 0;
	
	@JsonProperty(value = "count")
	private int totalCount;
	private List<APIPageResultDTO> results;

	public String getCurrUrl() {
		return this.results.get(currPage).getUrl();
	}

	public void nextPage() {
		if (this.currPage < this.count) {
			this.currPage += 1;			
		}
	}

	public boolean hasNextPage() {
		return this.currPage < this.count;
	}

}
