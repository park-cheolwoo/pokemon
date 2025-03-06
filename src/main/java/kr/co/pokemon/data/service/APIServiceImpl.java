package kr.co.pokemon.data.service;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pokemon.data.dto.APIPageDTO;
import kr.co.pokemon.data.dto.APIResponseDTO;

@Service
public class APIServiceImpl implements APIService {
	
	@Value("${poketmon.api-base-url}")
	private String apiBaseUrl;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DataService dataService;
	
	@Override
	public <D, S extends APIGetable<D>> APIResponseDTO setData(String uri, Class<S> serviceClass) {
		int dataCount = 0;
		
		try {
			S service = applicationContext.getBean(serviceClass);
			service.getDependencies().stream().forEach(dbTable -> {
				dataService.getTableInfo(dbTable.getTableName()).ifPresentOrElse(
					info -> {
						if (info.getCount() == 0) throw new IllegalArgumentException(info.getName() + " 테이블 데이터가 필요합니다.");
					},
					() -> new IllegalArgumentException("필요한 의존 테이블이 존재하지 않습니다. : " + dbTable.getTableName()));
			});
			
			APIPageDTO apiPageDTO = getDataDTOFromAPI(uri, APIPageDTO.class);
			
			if (apiPageDTO.getCount() > 20) {
				apiPageDTO = getDataDTOFromAPI(String.format("%s?limit=%d", uri, apiPageDTO.getCount() + 1), APIPageDTO.class);
			}

			Class<D> dto = (Class<D>) ((ParameterizedType) serviceClass.getGenericInterfaces()[0])
					.getActualTypeArguments()[0];

			if (dataService.deleteAllData(service.getDBTableName())) {
				while (apiPageDTO.hasNextPage()) {
					String pageUri = apiPageDTO.getCurrUrl().substring(apiBaseUrl.length());
					dataCount += service.getDataFromAPI(getDataDTOFromAPI(pageUri, dto));
					apiPageDTO.nextPage();
				}
			} else {
				throw new IllegalAccessError(service.getDBTableName() + " 테이블 데이터 삭제에 실패하였습니다.");
			}
		} catch (Exception e) {
			return new APIResponseDTO("fail", dataCount, e.getMessage());
		}
		return new APIResponseDTO("success", dataCount);
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
