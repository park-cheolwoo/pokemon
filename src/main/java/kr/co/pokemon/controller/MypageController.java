package kr.co.pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

    // 마이페이지 초기 화면
    @GetMapping("/mypage")
    public String mypage() {
        return "/mypage/mypage";  // mypage.jsp 반환
    }

    // 아이템 탭에 대한 콘텐츠 로드
    @GetMapping("/item")
    public String item() {
        return "/mypage/item";  // item.jsp 반환
    }

    // 도감 탭에 대한 콘텐츠 로드
    @GetMapping("/pokedex")
    public String pokedex() {
        return "/mypage/pokedex";  // pokedex.jsp 반환
    }

    // 원정대 탭에 대한 콘텐츠 로드
    @GetMapping("/expedition")
    public String expedition() {
        return "/mypage/expedition";  // expedition.jsp 반환
    }

    @GetMapping("/pokedexView")
    public String pokedexView(@RequestParam(value = "id", required = false) Long pokemonId) {
        return "/mypage/pokedexView";  // pokedexView.jsp 반환
    }

    @GetMapping("/myPokemon")
    public String myPokemon() {
        return "/mypage/myPokemon";  // pokedexView.jsp 반환
    }
}
