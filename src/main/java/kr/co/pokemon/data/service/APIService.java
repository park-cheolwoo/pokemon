package kr.co.pokemon.data.service;

import kr.co.pokemon.data.dto.APIResponseDTO;

public interface APIService {
	
	<D, S extends APIGetable<D>> APIResponseDTO setData(Class<S> service, int part);

	<D, S extends APIGetable<D>> APIResponseDTO setData(Class<S> service);

	<T> T getDataDTOFromAPI(String uri, Class<T> dto);
	
	public static int getIdByUrl(String url) {
		String[] separatedUrl = url.split("/");
		String extractId = separatedUrl[separatedUrl.length - 1];

		return Integer.parseInt(extractId);
	}

}
