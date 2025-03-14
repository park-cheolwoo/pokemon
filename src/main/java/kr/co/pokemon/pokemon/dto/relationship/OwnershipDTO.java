package kr.co.pokemon.pokemon.dto.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnershipDTO {
    private Long pokemonId;
    private boolean isOwned;
}
