package kr.co.pokemon.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StageDTO {
    private int stageid;
    private int habitatId;
    private int money;
    private int experience;
    private int minLevel;
    private int maxLevel;

    // Getter & Setter
}