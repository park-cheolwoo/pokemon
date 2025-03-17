package kr.co.pokemon.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemService;

@Controller
public class StoreController {

	@Autowired ItemService itemService;
	
    // 상점 메인페이지 오픈	
	@GetMapping(value = "/store/basicStore")
	public String basicStore(@RequestParam(defaultValue = "1") int page, Model model) {
		List<ItemDTO> list = itemService.getAll(new PageRequestDTO(200, page));
		List<Integer> categoryList = Arrays.asList(1, 10, 11, 26, 27, 28, 29, 30, 33, 34, 37);
		List<ItemDTO> filteredList = new ArrayList<ItemDTO>();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(categoryList.contains(list.get(i).getCategoryId()));
			if (categoryList.contains(list.get(i).getCategoryId())) {
				filteredList.add(list.get(i)); // Add matching items to the new list
			}
		}
		model.addAttribute("list", filteredList);
		return "store/basicStore";
	}
	
	

}
