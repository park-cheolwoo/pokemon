package kr.co.pokemon.pokemon.service;

import java.util.List;

import kr.co.pokemon.data.service.APIGetable;
import kr.co.pokemon.pokemon.dto.PokemonDTO;

public interface PokemonService extends APIGetable<PokemonDTO> {
    
    /**
     * 포켓몬 리스트를 소유 여부와 함께 가져옵니다.
     * 
     * @param playerId 플레이어 ID
     * @return 소유 여부가 포함된 포켓몬 리스트
     */
    List<PokemonDTO> getPokemonListWithOwnership(Long playerId);
}
