package kr.co.pokemon.pokemon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO.PokemonSprites;

@Mapper
public interface PokemonMapper {

	List<PokemonDTO> selectAll(PageRequestDTO page);
	
	PokemonDTO selectById(int id);

	List<Integer> selectByIds(List<Integer> ids);
	
	List<PokemonDTO> selectByName(String keyword);
	
	int existById(int id);
	
	void insert(PokemonDTO pokemon);
	
	void insertAll(List<PokemonDTO> pokemons);

	void updateGrowthId(Map<String, Integer> updateGrowthId);

	void updateEvolutionId(Map<String, Integer> updateEvolutionId);
	
	PokemonSprites selectSpritesById(int id);
	
	void insertSprites(PokemonSprites sprites);
	
	void insertAllSprites(List<PokemonSprites> spritess);
	
	/**
	 * 플레이어가 소유한 포켓몬 리스트를 반환합니다.
	 * 소유 여부는 쿼리 결과에 is_owned 컬럼으로 포함됩니다.
	 * 
	 * @param playerId 플레이어 ID
	 * @param page 페이지 요청 정보
	 * @return 소유 여부가 포함된 포켓몬 리스트
	 */
	List<PokemonDTO> selectAllWithOwnership(@Param("playerId") Long playerId, @Param("page") PageRequestDTO page);
}
