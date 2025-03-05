package kr.co.pokemon.data.dto;

import java.util.List;

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
	private List<PageResult> results;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PageResult {
		private String name;
		private String url;
	}

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
