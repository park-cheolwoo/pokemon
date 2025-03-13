package kr.co.pokemon.player.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.pokemon.play.dto.PokemonOwnStat;
import kr.co.pokemon.player.dto.OwnPokemonStat;

@Mapper
public interface OwnPokemonStatMapper {

		OwnPokemonStat selectById(int id);
		
		List<PokemonOwnStat> selectStatByOwnPokemonId(int ownPokemonId);
		
		void insert(OwnPokemonStat ownPokemonStat);
	
}
