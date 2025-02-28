package kr.co.pokemon.data.service;

public interface APIGetable {

	 <T> void getDataFromAPI(T dto) throws Exception;

}
