package kr.co.pokemon.pokemon.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.EffectEntryDTO;
import kr.co.pokemon.data.dto.EffectGroup;
import kr.co.pokemon.data.dto.FlavorTextEntryDTO;
import kr.co.pokemon.data.dto.FlavorTextGroup;
import kr.co.pokemon.data.dto.LanguageNameDTO;
import kr.co.pokemon.data.dto.NamesGroup;
import kr.co.pokemon.data.dto.APIPageResultDTO;
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
public class AttackDTO extends EntityDTO implements EffectGroup, FlavorTextGroup, NamesGroup {

	private int id;
	private int typesId;
	private String name;
	private String description;
	private String flavorText;
	
	@JsonProperty(value = "power")
	private int damage;

	private boolean isActive;

	private List<LanguageNameDTO> names;

	@JsonProperty(value = "effect_entries")
	private List<EffectEntryDTO> effectEntries;
	
	@JsonProperty(value = "flavor_text_entries")
	private List<FlavorTextEntryDTO> flavorTextEntries;
	
	private APIPageResultDTO type;

}
