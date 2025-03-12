package kr.co.pokemon.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSpriteDTO {
    @JsonProperty("default")
    private String defaultSprite;
}
