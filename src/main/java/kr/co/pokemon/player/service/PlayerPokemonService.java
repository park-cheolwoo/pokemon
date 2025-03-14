package kr.co.pokemon.player.service;

import java.util.List;

import kr.co.pokemon.play.dto.PokemonOwnAbility;
import kr.co.pokemon.play.dto.PokemonOwnAttack;
import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.player.dto.OwnPokemonSkill;
import kr.co.pokemon.player.dto.OwnPokemonStat;
import kr.co.pokemon.player.dto.PlayerPokemonDTO;

public interface PlayerPokemonService {

	PlayerPokemonDTO getById(int id);

	List<PlayerPokemonDTO> getByPlayerId(String playerId);

	int getIdByPokemonIdAndPlayerId(int pokemonId, int playerId);

	void save(PlayerPokemonDTO playerPokemon);
	
	void updateName(int id, String name);
	
	void updateLevel(int id, int level);
	
	void updateExperience(int id, int experience);
	
	void savePokemonAbility(OwnPokemonSkill skill);

	void savePokemonAttack(OwnPokemonSkill skill);
	
	void savePokemonStat(OwnPokemonStat stat);
	
	List<PokemonOwnAbility> getAbilitiesByOwnPokemonId(int ownPokemonId);
	
	List<PokemonOwnAttack> getAttacksByOwnPokemonId(int ownPokemonId);
	
	List<PokemonOwnStat> getStatByOwnPokemonId(int ownPokemonId);
	
}
