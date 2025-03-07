package kr.co.pokemon.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dao.PokemonMapper;
import kr.co.pokemon.pokemon.dto.PokemonDTO;
import kr.co.pokemon.pokemon.dto.PokemonSpecDTO;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	private DBTables dbTable = DBTables.POKEMON;
	
	@Autowired
	private APIService apiService;
	
	@Autowired
	private PokemonMapper pokemonMapper;

	@Override
	public int insertDataFromAPI(List<PokemonDTO> list) throws Exception {
		list.stream().forEach(dto -> {
			PokemonSpecDTO pokemonSpecDTO = apiService.getDataDTOFromAPI(dto.getSpecies().getUrl(), PokemonSpecDTO.class);
			
			String image = dto.getSprites().getOther().getOfficialArtwork().getFront_default();
			String image3d = dto.getSprites().getOther().getHome().getFront_default();
			
			dto.setImage(image != null? image : "/images/no-pokemon.png");
			dto.setImage3d(image3d != null? image3d : "/images/no-pokemon.png");

			dto.setGenderRate(pokemonSpecDTO.getGenderRate());
			dto.setGenderDiff(pokemonSpecDTO.isGenderDiff());
			dto.setLegendary(pokemonSpecDTO.isLegendary());
			dto.setMythical(pokemonSpecDTO.isMythical());
			
			pokemonSpecDTO.getLanguagesName("ko").ifPresent(name -> dto.setName(name));
			pokemonSpecDTO.getLanguagesGenus("ko").ifPresent(genus -> dto.setGenus(genus));
			pokemonSpecDTO.getLanguagesFlavorText("ko").ifPresentOrElse(
				flavor -> dto.setFlavorText(flavor),
				() -> dto.setFlavorText("설명이 없습니다.")
			);

			System.out.println("포켓몬 데이터 " + dto.getName() + " 준비");
		});
		pokemonMapper.insertAll(list);

		return list.size();
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
	public void insert(PokemonDTO dto) {
		pokemonMapper.insert(dto);
	}

}
