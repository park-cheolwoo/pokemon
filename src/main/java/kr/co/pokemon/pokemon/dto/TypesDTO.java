package kr.co.pokemon.pokemon.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.pokemon.data.dto.LanguageNameDTO;
import kr.co.pokemon.data.dto.NamesGroup;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypesDTO extends EntityDTO implements NamesGroup {

	private String name;
	private String originalName;
	private String image;
	
	private List<LanguageNameDTO> names;

}
