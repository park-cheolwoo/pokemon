package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageNameDTO {
	private String name;
	private LanguageDTO language;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class LanguageDTO {

		private String name;
		private String url;

	}

}
