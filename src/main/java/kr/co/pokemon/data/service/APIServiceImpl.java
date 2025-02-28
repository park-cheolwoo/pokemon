package kr.co.pokemon.data.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import kr.co.pokemon.data.dto.APIPageDTO;

@Service
public class APIServiceImpl implements APIService {
	
	@Value("${poketmon.run-init-sql}")
	private boolean isRunInitSql;
	
	@Value("${poketmon.api-base-url}")
	private String apiBaseUrl;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@PostConstruct
	public void initTable() throws SQLException {
		if (isRunInitSql) {
			Connection conn = dataSource.getConnection();
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/createTable.sql"));
		}
	}
	
	public <S extends APIGetable> boolean setData(String uri, Class<?> dto, S service) {
		APIPageDTO apiPageDTO = getDataDTOFromAPI(uri, APIPageDTO.class);

		try {
			while (apiPageDTO.getCurrPage() < apiPageDTO.getCount()) {
				service.getDataFromAPI(getDataDTOFromAPI(String.format("%s/%d", uri, apiPageDTO.getCurrPage() + 1), dto));
				apiPageDTO.nextPage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private <T> T getDataDTOFromAPI(String uri, Class<T> dto) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(getDataFromAPI(uri), dto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getDataFromAPI(String uri) {
		return restTemplate.getForEntity(apiBaseUrl + uri, String.class).getBody();
	}

}
