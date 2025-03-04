package kr.co.pokemon.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpritesDTO {

	private int id;
	private String backDefault;
	private String backFemale;
	private String backShiny;
	private String backShinyFemale;
	private String frontDefault;
	private String frontFemale;
	private String frontShiny;
	private String frontShinyFemale;

}
