package kr.co.pokemon.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonDTO {

	private int id;
	private String name;
	private String image;
	private String image3d;

	@JsonProperty(value = "gender_rate")
	private int genderRate;
	
	@JsonProperty(value = "has_gender_differences")
	private boolean genderDiff;

	private String genus;
	private int height;
	private int weight;
	private String flavorText;

}
