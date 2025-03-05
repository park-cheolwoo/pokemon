package kr.co.pokemon.data.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
	private ApplicationContext applicationContext;
	
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
	
	public <D, S extends APIGetable<D>> boolean setData(String uri, Class<S> serviceClass) {
		APIPageDTO apiPageDTO = getDataDTOFromAPI(uri, APIPageDTO.class);
		
		if (apiPageDTO.getCount() > 20) {
			apiPageDTO = getDataDTOFromAPI(String.format("%s?limit=%d", uri, apiPageDTO.getCount() + 1), APIPageDTO.class);
		}

		try {
			S service = applicationContext.getBean(serviceClass);
			Class<D> dto = (Class<D>) ((ParameterizedType) serviceClass.getGenericInterfaces()[0])
					.getActualTypeArguments()[0];

			while (apiPageDTO.hasNextPage()) {
				String pageUri = apiPageDTO.getCurrUrl().substring(apiBaseUrl.length());
				service.getDataFromAPI(getDataDTOFromAPI(pageUri, dto));
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
