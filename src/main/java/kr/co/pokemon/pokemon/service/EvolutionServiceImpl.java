package kr.co.pokemon.pokemon.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.model.DBTables;
import kr.co.pokemon.data.service.APIService;
import kr.co.pokemon.data.service.DataService;
import kr.co.pokemon.pokemon.dao.EvolutionMapper;
import kr.co.pokemon.pokemon.dto.EvolutionDTO;
import kr.co.pokemon.pokemon.dto.EvolutionDTO.EvolutionChain;
import kr.co.pokemon.pokemon.dto.relationship.EvolutionDetail;

@Service
public class EvolutionServiceImpl implements EvolutionService {
	
	private DBTables dbTable = DBTables.EVOLUTION;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private EvolutionMapper evolutionMapper;

	@Override
	public int insertDataFromAPI(List<EvolutionDTO> list) throws Exception {
		AtomicInteger count = new AtomicInteger(0);
		list.stream().forEach(dto -> {
			count.addAndGet(nextToChain(dto, dto.getChain(), null));
		});
		
		if (dataService.deleteAllData(dbTable.getTableName(), list.stream().map(dto -> dto.getId()).toList())) {
		} else {
			throw new IllegalArgumentException(DBTables.EGG_GROUP_POKEMON.getTableName() + " 의 데이터 삭제에 실패하였습니다.");
		}
		
		return count.get();
	}

	private int nextToChain(EvolutionDTO dto, EvolutionChain chain, Integer prevId) {
		AtomicInteger count = new AtomicInteger(0);

		if (chain != null) {
			int currId = APIService.getIdByUrl(chain.getSpecies().getUrl());
			
			Optional.ofNullable(chain.getEvolvesTo())
				.filter(evolvesTo -> !evolvesTo.isEmpty())
				.ifPresentOrElse(
					hasChain -> hasChain.stream().forEach(c -> {
						int nextId = APIService.getIdByUrl(c.getSpecies().getUrl());

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

				evolutionMapper.deleteDetailByCurrId(currId);
				evolutionMapper.insertDetail(new EvolutionDetail(triggerId, dto.getId(), currId, minLevel, itemId));
			});
			
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
	public void insert(EvolutionDTO dto) {
		evolutionMapper.insert(dto);
	}

}
