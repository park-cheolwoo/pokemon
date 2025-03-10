package kr.co.pokemon.data.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.co.pokemon.data.dao.DataInfoMapper;
import kr.co.pokemon.data.dto.DataDeleteDTO;
import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.data.dto.TableInfoDTO;
import kr.co.pokemon.data.model.DBTables;

@Service
public class DataServiceImpl implements DataService {
	
	private final static List<String> DB_SEQUENCES = Collections.unmodifiableList(
		Arrays.asList(
				"types_relationship_seq", "total_experience_seq",
				"pokemon_ability_seq", "pokemon_types_seq", "pokemon_attack_seq",
				"pokemon_base_stat_seq", "pokemon_habitat_seq", "egg_group_pokemon_seq"
		)
	);
	
	@Value("${poketmon.run-init-sql}")
	private boolean isRunInitSql;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DataInfoMapper dataInfoMapper;

	@Override
	public <D, S extends Getable<D>> List<D> getAll(PageRequestDTO page, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return service.getAll(page);
	}

	@Override
	public <D, S extends Getable<D>> Optional<D> getById(int id, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		return Optional.ofNullable(service.getById(id));
	}

	@Override
	public <D, S extends Getable<D>> void insert(D dto, Class<S> serviceClass) {
		S service = applicationContext.getBean(serviceClass);
		service.insert(dto);
	}
	
	@Override
	@PostConstruct
	public void initTable() throws SQLException {
		if (isRunInitSql) {
			dropAllTable();
			Connection conn = dataSource.getConnection();
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/createTable.sql"));
		}
	}

	@Override
	public List<TableInfoDTO> getAllTableInfo() {
		List<TableInfoDTO> result = new ArrayList<>();
		DBTables.getTableNames().forEach(tableName -> {
			getTableInfo(tableName).ifPresent(info -> result.add(info));
		});
		return result;
	}
	
	@Override
	public Optional<TableInfoDTO> getTableInfo(String tableName) {
		if (!DBTables.contains(tableName)) {
			return Optional.empty();
		}
		return Optional.ofNullable(dataInfoMapper.getTableInfoByName(tableName))
				.map(info -> {
					info.setCount(dataInfoMapper.getTableDataCountByName(tableName));
					return info;
				});
	}
	
	@Override
	public void dropAllTable() {
		DBTables.getTableNames().stream().forEach(tableName -> {
			try {
				if (dataInfoMapper.getTableInfoByName(tableName) != null) {
					dataInfoMapper.dropTableByName(tableName);
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
		
		DB_SEQUENCES.stream().forEach(sequenceName -> {
			try {
				if (dataInfoMapper.existSequenceByName(sequenceName) != 0) {
					dataInfoMapper.dropSequenceByName(sequenceName);
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
	}
	
	@Override
	public boolean deleteAllData(String tableName, List<Integer> ids) {
		try {
			dataInfoMapper.deleteDataByPage(new DataDeleteDTO(tableName, ids));
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteAllData(String tableName) {
		try {
			dataInfoMapper.deleteDataByName(tableName);
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}

}
