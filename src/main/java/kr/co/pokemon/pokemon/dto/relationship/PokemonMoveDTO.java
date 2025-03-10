package kr.co.pokemon.pokemon.dto.relationship;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import kr.co.pokemon.data.dto.APIPageResultDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonMoveDTO {

	private int id;

	private List<Move> moves;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Move {
		
		private APIPageResultDTO move;
		
		@JsonProperty(value = "version_group_details")
		private List<VersionGroupDetail> versionGroupDetails;
		
		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class VersionGroupDetail {
			
			@JsonProperty(value = "level_learned_at")
			private int levelLearnedAt;
			
			@JsonProperty(value = "move_learn_method")
			private APIPageResultDTO moveLearnMethod;

		}
		
		public int getLearnedLevel(String methodName) {
			return versionGroupDetails.stream()
					.filter(detail -> detail.getMoveLearnMethod().getName().equals(methodName))
					.findFirst().map(detail -> detail.getLevelLearnedAt()).orElse(101);
		}
		
	}
}
