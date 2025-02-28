package kr.co.pokemon.pokemon.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvolutionTriggerDTO {

	private int id;
	private String name;
	private Timestamp updatedAt;
	private Timestamp createdAt;

}
