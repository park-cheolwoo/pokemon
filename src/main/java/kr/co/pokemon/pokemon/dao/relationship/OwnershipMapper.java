package kr.co.pokemon.pokemon.dao.relationship;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import kr.co.pokemon.pokemon.dto.relationship.OwnershipDTO;

@Mapper
public interface OwnershipMapper {
    List<OwnershipDTO> getPokemonOwnership(@Param("playerId") Long playerId);
}
