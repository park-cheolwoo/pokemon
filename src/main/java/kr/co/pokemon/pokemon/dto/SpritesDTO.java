package kr.co.pokemon.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpritesDTO extends EntityDTO {

	private String backDefault;
	private String backFemale;
	private String backShiny;
	private String backShinyFemale;
	private String frontDefault;
	private String frontFemale;
	private String frontShiny;
	private String frontShinyFemale;

}
