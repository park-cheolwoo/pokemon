package kr.co.pokemon.data.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TimeDTO {

	private Timestamp updatedAt;
	private Timestamp createdAt;

}
