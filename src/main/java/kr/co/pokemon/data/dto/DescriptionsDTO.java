package kr.co.pokemon.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionsDTO {

	private String description;
	private APIPageResultDTO language;

}