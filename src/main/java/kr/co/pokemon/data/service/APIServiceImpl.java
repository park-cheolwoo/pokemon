package kr.co.pokemon.data.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pokemon.data.dto.APIPageDTO;
import kr.co.pokemon.data.dto.APIResponseDTO;
import kr.co.pokemon.data.model.DBTables;

@Service
public class APIServiceImpl implements APIService {
	
	private final int PART_COUNT = 100;
	
	@Value("${poketmon.api-base-url}")
	private String apiBaseUrl;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DataService dataService;

	@Override
	public <D, S extends APIGetable<D>> APIResponseDTO setData(Class<S> serviceClass, int part) {
		S service = applicationContext.getBean(serviceClass);
		DBTables serviceDBTable = service.getDBTable();
		int dataCount = 0;
		APIPageDTO apiPageDTO = getAPIPageDTO(serviceDBTable.getUri(), part);
		
		try {
			service.getDependencies().stream().forEach(dbTable -> {
				dataService.getTableInfo(dbTable.getTableName()).orElseThrow(
					() -> new IllegalArgumentException("필요한 의존 테이블이 존재하지 않습니다. : " + dbTable.getTableName())
				);
			});

			Class<D> dto = (Class<D>) ((ParameterizedType) serviceClass.getGenericInterfaces()[0])
					.getActualTypeArguments()[0];
			
			if (apiPageDTO.getCount() == 0 || (part > 1 && apiPageDTO.getTotalCount() < PART_COUNT)) {
				throw new IllegalArgumentException("데이터가 존재하지 않습니다.");
			}
			
			List<D> list = new ArrayList<>();
			while (apiPageDTO.hasNextPage()) {
				String pageUri = apiPageDTO.getCurrUrl().substring(apiBaseUrl.length());
				list.add(getDataDTOFromAPI(pageUri, dto));
				System.out.println("URL: " + pageUri + " ( " + (apiPageDTO.getCurrPage() + 1) + " / " + apiPageDTO.getCount() + " )");
				apiPageDTO.nextPage();
			}
			dataCount = service.insertDataFromAPI(list);
		} catch (Exception e) {
			return new APIResponseDTO("fail", serviceDBTable.getTableName(), dataCount, part, (apiPageDTO.getTotalCount() / PART_COUNT) + 1, e.getMessage());
		}
		return new APIResponseDTO("success", serviceDBTable.getTableName(), dataCount, part, (apiPageDTO.getTotalCount() / PART_COUNT) + 1);
	}
	
	@Override
	public <D, S extends APIGetable<D>> APIResponseDTO setData(Class<S> serviceClass) {
		return setData(serviceClass, 1);
	}

	@Override
	public <T> T getDataDTOFromAPI(String uri, Class<T> dto) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(getDataFromAPI(uri), dto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getDataFromAPI(String uri) {
		if (!uri.contains(apiBaseUrl)) {
			uri = apiBaseUrl + uri;
		}
		return restTemplate.getForEntity(uri, String.class).getBody();
	}
	
	private APIPageDTO getAPIPageDTO(String uri, int part) {
		APIPageDTO apiPageDTO = getDataDTOFromAPI(uri + "?limit=" + PART_COUNT, APIPageDTO.class);

		if (apiPageDTO.getTotalCount() > PART_COUNT) {
			apiPageDTO = getDataDTOFromAPI(String.format("%s?offset=%d&limit=%d", uri, (part - 1) * PART_COUNT, PART_COUNT), APIPageDTO.class);
		}
		apiPageDTO.setCount(apiPageDTO.getResults().size());

		return apiPageDTO;
	}

}
