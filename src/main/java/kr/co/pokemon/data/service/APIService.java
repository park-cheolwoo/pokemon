package kr.co.pokemon.data.service;

import kr.co.pokemon.data.dto.APIResponseDTO;

public interface APIService {
	
	public <D, S extends APIGetable<D>> APIResponseDTO setData(String uri, Class<S> service);
	
	public static int getIdByUrl(String url) {
		String[] separatedUrl = url.split("/");
		String extractId = separatedUrl[separatedUrl.length - 1];

		return Integer.parseInt(extractId);
	}

}
