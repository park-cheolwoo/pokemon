package kr.co.pokemon.player.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.player.dto.PlayerPokemonDTO;

@Mapper
public interface PlayerPokemonMapper {
	
	PlayerPokemonDTO selectById(int id);

	List<PlayerPokemonDTO> selectByPlayerId(String playerId);
	
	Optional<Integer> selectIdByPokemonIdAndPlayerId(Map<String, Integer> idMap);
	
	void insert(PlayerPokemonDTO playerPokemon);
	
	void updateNameByCharacteristicById(Map<String, Integer> updateCharacteristicMap);

	void updateNameById(Map<String, String> updateNameMap);
	
	void updatLevelById(Map<String, Integer> updateLevelMap);

	void updateExperienceById(Map<String, Integer> updateExperienceMap);

	int countPokemonByPlayerId(String playerId);

	List<PlayerPokemonDTO> selectByIngamePlayerId(String sessionId);

}
