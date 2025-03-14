package kr.co.pokemon.pokemon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.PokemonMapper;
import kr.co.pokemon.pokemon.dao.relationship.PokemonStatMapper;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.PokemonDTO.PokemonSprites;
import kr.co.pokemon.pokemon.dto.PokemonSpecDTO;
import kr.co.pokemon.pokemon.dto.relationship.PokemonStatDTO;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	private DBTables dbTable = DBTables.POKEMON;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private APIService apiService;
	
	@Autowired
	private StatService statService;
	
	@Autowired
	private PokemonMapper pokemonMapper;
	
	@Autowired
	private PokemonStatMapper pokemonStatMapper;

	@Override
	@Transactional
	public int insertDataFromAPI(List<PokemonDTO> list) throws Exception {
		List<PokemonStatDTO> pokemonStats = new ArrayList<>();
		
		list.stream().forEach(dto -> {
			PokemonSpecDTO pokemonSpecDTO = apiService.getDataDTOFromAPI(dto.getSpecies().getUrl(), PokemonSpecDTO.class);
			
			String image = dto.getSprites().getOther().getOfficialArtwork().getFrontDefault();
			String image3d = dto.getSprites().getOther().getHome().getFrontDefault();
			
			dto.setImage(image != null? image : "/images/no-pokemon.png");
			dto.setImage3d(image3d != null? image3d : "/images/no-pokemon.png");

			dto.setGenderRate(pokemonSpecDTO.getGenderRate());
			dto.setGenderDiff(pokemonSpecDTO.isGenderDiff());
			dto.setLegendary(pokemonSpecDTO.isLegendary());
			dto.setMythical(pokemonSpecDTO.isMythical());
			
			PokemonSprites sprites = dto.getSprites();
			
			sprites.setId(dto.getId());
			spriteSetting(sprites);

			pokemonSpecDTO.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			pokemonSpecDTO.getLanguagesGenus("ko").ifPresent(genus -> dto.setGenus(genus));
			pokemonSpecDTO.getLanguagesFlavorText("ko").ifPresentOrElse(
				flavor -> dto.setFlavorText(flavor),
				() -> dto.setFlavorText("설명이 없습니다.")
			);
			
			dto.getStats().stream().forEach(stat -> {
				int statId = APIService.getIdByUrl(stat.getStat().getUrl());
				
				if (statService.existById(statId)) {
					pokemonStats.add(new PokemonStatDTO(dto.getId(), statId, stat.getBaseStat()));
					
				}
			});
		});

		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
			pokemonMapper.insertAll(list);
			if (dataService.deleteAllData(DBTables.SPRITES.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
				pokemonMapper.insertAllSprites(list.stream().map(dto -> dto.getSprites()).toList());
				
				if (pokemonStats.size() > 0) {
					pokemonStatMapper.insertAll(pokemonStats);
					
				}
				
			} else {
				throw new IllegalArgumentException(DBTables.SPRITES.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
			}

		} else {
			throw new IllegalArgumentException(dbTable.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}

		return list.size();
	}
	
	private void spriteSetting(PokemonSprites pokemonSprites) {
		final String noImage = "/images/no-sprite.png";
		PokemonSprites showDown = pokemonSprites.getOther().getShowdown();
		
		pokemonSprites.setBackDefault(showDown.getBackDefault() != null ? showDown.getBackDefault() : noImage);
		pokemonSprites.setBackFemale(showDown.getBackFemale() != null ? showDown.getBackFemale() : noImage);
		pokemonSprites.setBackShiny(showDown.getBackShiny() != null ? showDown.getBackShiny() : noImage);
		pokemonSprites.setBackShinyFemale(showDown.getBackShinyFemale() != null ? showDown.getBackShinyFemale() : noImage);
		
		pokemonSprites.setFrontDefault(showDown.getFrontDefault() != null ? showDown.getFrontDefault() : noImage);
		pokemonSprites.setFrontFemale(showDown.getFrontFemale() != null ? showDown.getFrontFemale() : noImage);
		pokemonSprites.setFrontShiny(showDown.getFrontShiny() != null ? showDown.getFrontShiny() : noImage);
		pokemonSprites.setFrontShinyFemale(showDown.getFrontShinyFemale() != null ? showDown.getFrontShinyFemale() : noImage);
		
	}

	@Override
	public List<DBTables> getDependencies() {
		return dbTable.getDependencies();
	}

	@Override
	public DBTables getDBTable() {
		return dbTable;
	}
	@Override
	public List<PokemonDTO> getAll(PageRequestDTO page) {
		return pokemonMapper.selectAll(page);
	}

	@Override
	public PokemonDTO getById(int id) {
		return pokemonMapper.selectById(id);
	}
		
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return pokemonMapper.selectByIds(ids);
	}

	@Override
	public List<PokemonDTO> getByName(String keyword) {
		return pokemonMapper.selectByName(keyword);
	}
	
	
	@Override
	public void insert(PokemonDTO dto) {
		pokemonMapper.insert(dto);
	}
	
	@Override
	public List<PokemonStatDTO> getStatsByPokemonId(int id) {
		return pokemonStatMapper.selectByPokemonId(id);
	}
	
	@Override
	public boolean existById(int id) {
		return pokemonMapper.existById(id) != 0;
	}
	
	@Override
	public PokemonSprites getSpritesById(int id) {
		return pokemonMapper.selectSpritesById(id);
	}
	
	@Override
	public void setEvolutionId(int id, int evolutionId) {
		Map<String, Integer> updateEvolution = new HashMap<>();
		
		updateEvolution.put("id", id);
		updateEvolution.put("evolutionId", evolutionId);
		
		pokemonMapper.updateEvolutionId(updateEvolution);
	}

	@Override
	public List<PokemonDTO> getPokemonListWithOwnership(Long playerId) {
		return pokemonMapper.selectAllWithOwnership(playerId, new PageRequestDTO());
	}
}
