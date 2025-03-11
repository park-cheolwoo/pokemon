package kr.co.pokemon.item.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.APIPageResultDTO;
import kr.co.pokemon.data.dto.EffectEntryDTO;
import kr.co.pokemon.data.dto.EffectGroup;
import kr.co.pokemon.data.dto.EntityDTO;
import kr.co.pokemon.data.dto.FlavorTextEntryDTO;
import kr.co.pokemon.data.dto.FlavorTextGroup;
import kr.co.pokemon.data.dto.ItemSpriteDTO;
import kr.co.pokemon.data.dto.LanguageNameDTO;
import kr.co.pokemon.data.dto.NamesGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author 
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO extends EntityDTO implements NamesGroup, EffectGroup, FlavorTextGroup {

	private String name;
	private String image;
	private int categoryId;
	private int cost;
	private int realCost;
	
	@JsonProperty(value = "fling_power")
	private Integer value;

	private String description;
	private String flavorText;
	private int storeType;
	private boolean isActive;

	@JsonProperty(value = "effect_entries")
	private List<EffectEntryDTO> effectEntries;
	
	@JsonProperty(value = "flavor_text_entries")
	private List<FlavorTextEntryDTO> flavorTextEntries;
	
	private APIPageResultDTO category;
	
	@JsonProperty(value = "sprites")
	private ItemSpriteDTO sprite;
	
	@JsonProperty(value = "names")
	private List<LanguageNameDTO> names;

	@Override
	public List<EffectEntryDTO> getEffectEntries() {
		return effectEntries;
	}
}
