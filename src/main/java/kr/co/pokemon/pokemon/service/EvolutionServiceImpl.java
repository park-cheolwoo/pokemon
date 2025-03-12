package kr.co.pokemon.pokemon.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.pokemon.dao.EvolutionMapper;
import kr.co.pokemon.pokemon.dto.EvolutionDTO;
import kr.co.pokemon.pokemon.dto.EvolutionDTO.EvolutionChain;
import kr.co.pokemon.pokemon.dto.relationship.EvolutionDetail;

@Service
public class EvolutionServiceImpl implements EvolutionService {
	
	private DBTables dbTable = DBTables.EVOLUTION;
	
	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private EvolutionTriggerService evolutionTriggerService;
	
	@Autowired
	private EvolutionMapper evolutionMapper;

	@Override
	public int insertDataFromAPI(List<EvolutionDTO> list) throws Exception {
		AtomicInteger count = new AtomicInteger(0);
		list.stream().forEach(dto -> {
			count.addAndGet(nextToChain(dto, dto.getChain(), null));
		});
		
		return count.get();
	}

	private int nextToChain(EvolutionDTO dto, EvolutionChain chain, Integer prevId) {
		AtomicInteger count = new AtomicInteger(0);

		if (chain != null) {
			int currId = APIService.getIdByUrl(chain.getSpecies().getUrl());
			
			if (pokemonService.existById(currId)) {
				Optional.ofNullable(chain.getEvolvesTo())
				.filter(evolvesTo -> !evolvesTo.isEmpty())
				.ifPresentOrElse(
						hasChain -> hasChain.stream().forEach(c -> {
							Integer nextId = APIService.getIdByUrl(c.getSpecies().getUrl());
							
							if (!pokemonService.existById(nextId)) nextId = null;
							
							evolutionMapper.deleteByCurrId(currId);
							evolutionMapper.insert(new EvolutionDTO(dto.getId(), prevId, currId, nextId));
							count.incrementAndGet();
							count.addAndGet(nextToChain(dto, c, currId));
						}),
						() -> {
							if (prevId != null) {
								evolutionMapper.deleteByCurrId(currId);
								evolutionMapper.insert(new EvolutionDTO(dto.getId(), prevId, currId, null));							
							}
						}
						);
				
				chain.getEvolutionDetails().stream().forEach(detail -> {
					int itemId = Optional.ofNullable(detail.getItem()).map(item -> APIService.getIdByUrl(item.getUrl())).orElse(0);
					int minLevel = Optional.ofNullable(detail.getMinLevel()).orElse(0);
					int triggerId = APIService.getIdByUrl(detail.getTrigger().getUrl());
					
					if (evolutionTriggerService.existById(triggerId)) {
						evolutionMapper.deleteDetailByCurrId(currId);
						evolutionMapper.insertDetail(new EvolutionDetail(triggerId, dto.getId(), currId, minLevel, itemId));

					}					
				});
			}
		}
		return count.get();
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
	public List<EvolutionDTO> getAll(PageRequestDTO page) {
		return evolutionMapper.selectAll(page);
	}

	@Override
	public EvolutionDTO getById(int id) {
		return evolutionMapper.selectById(id);
	}
	
	@Override
	public List<Integer> getByIds(List<Integer> ids) {
		return evolutionMapper.selectByIds(ids);
	}

	@Override
	public void insert(EvolutionDTO dto) {
		evolutionMapper.insert(dto);
	}

}
