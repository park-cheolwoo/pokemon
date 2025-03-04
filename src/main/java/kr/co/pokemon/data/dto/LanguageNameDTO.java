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

}
