package kr.co.pokemon.item.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.pokemon.data.dto.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // PokeAPI에서 쓰지 않는 컬럼 제외
@JsonInclude(JsonInclude.Include.NON_NULL)  // Json으로 넘길 때 NULL값은 표시되지 않음
public class ItemCategoryDTO extends EntityDTO {
	
	private int id;
	private String name;
	private String image;
	
}
