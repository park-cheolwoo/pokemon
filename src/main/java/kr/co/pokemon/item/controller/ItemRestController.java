package kr.co.pokemon.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.item.dto.ItemDTO;
import kr.co.pokemon.item.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

    @Autowired
    private ItemService itemService;
    
    /**
     * 카테고리 ID에 해당하는 아이템 목록을 반환합니다.
     * 
     * @param categoryId 아이템 카테고리 ID
     * @return 해당 카테고리의 아이템 목록
     */
    @GetMapping("/category/{categoryId}")
    public List<ItemDTO> getItemsByCategory(@PathVariable int categoryId) {
        return itemService.getByCategoryId(categoryId);
    }
}
