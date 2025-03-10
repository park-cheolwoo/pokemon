package kr.co.pokemon.item.dto;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.APIPageResultDTO;
import kr.co.pokemon.data.dto.EffectEntryDTO;
import kr.co.pokemon.data.dto.EffectGroup;
import kr.co.pokemon.data.dto.FlavorTextEntryDTO;
import kr.co.pokemon.data.dto.ItemSpriteDTO;
import kr.co.pokemon.data.dto.LanguageNameDTO;
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
public class ItemDTO implements EffectGroup {
	
	private int id;
	private String name;
	private String image;
	private int cost;
	private int realCost;
	private String storeType;  
	private boolean isActive;
	
	@JsonProperty(value = "effect_entries")
	private List<EffectEntryDTO> effectEntries;
	
	@JsonProperty(value = "flavor_text_entries")
	private List<FlavorTextEntryDTO> flavorTextEntries;
	
	private APIPageResultDTO category;
	private int categoryId;
	private String description;
	
	@JsonProperty(value = "sprites")
	private ItemSpriteDTO sprite;
	
	@JsonProperty(value = "names")
	private List<LanguageNameDTO> names;
	
	public Optional<String> getLanguagesName(String languageName) {
		return names.stream()
				.filter(name -> name.getLanguage().getName().equals(languageName))
				.findFirst()
				.map(LanguageNameDTO::getName);
	}
	
	public Optional<String> getLanguagesDescription(String languageName) {
		return flavorTextEntries.stream()
				.filter(entry -> entry.getLanguage().getName().equals(languageName))
				.findFirst()
				.map(FlavorTextEntryDTO::getFlavorText);
	}

	@Override
	public List<EffectEntryDTO> getEffectEntries() {
		return effectEntries;
	}
}
