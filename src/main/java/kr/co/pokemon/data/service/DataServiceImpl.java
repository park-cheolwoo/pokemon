package kr.co.pokemon.data.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kr.co.pokemon.data.dao.DataInfoMapper;
import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;

@Service
public class DataServiceImpl implements DataService {

	private final static List<String> API_TABLES = Collections.unmodifiableList(
			Arrays.asList(
					"evolution_trigger", "ability", "types",
					"types_relationship", "attack", "habitat", "egg_group",
					"stat", "characteristic", "pokemon", "sprites", "evolution", 
					"growth", "total_experience", "pokemon_ability",
					"pokemon_attack", "pokemon_types", "pokemon_habitat", "pokemon_base_stat",
					"item_category", "item", "game_stage"
					)
			);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DataInfoMapper dataInfoMapper;
	
	@Override
	public <D, S extends APIGetable<D>> List<D> getAll(PageRequestDTO page, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return service.getAll(page);
	}

	@Override
	public <D, S extends APIGetable<D>> Optional<D> getById(int id, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return Optional.ofNullable(service.getById(id));
	}
	
	@Override
	public List<TableInfoDTO> getAllTableInfo() {
		List<TableInfoDTO> result = new ArrayList<>();
		API_TABLES.forEach(tableName -> {
			TableInfoDTO info = dataInfoMapper.getInfoByTableName(tableName);
			if (info != null) {
				info.setCount(dataInfoMapper.getCountByTableName(tableName));
				result.add(info);
			}
		});
		return result;
	}
	
	@Override
	public Optional<TableInfoDTO> getTableInfo(String tableName) {
		if (!API_TABLES.contains(tableName)) {
			return Optional.empty();
		}
		return Optional.ofNullable(dataInfoMapper.getInfoByTableName(tableName))
				.map(info -> {
					info.setCount(dataInfoMapper.getCountByTableName(tableName));
					return info;
				});
	}
	
	@Override
	public boolean deleteAllData(String tableName) {
		try {
			dataInfoMapper.deleteDataByTableName(tableName);
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

}
