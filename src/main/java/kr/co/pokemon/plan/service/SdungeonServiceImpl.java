package kr.co.pokemon.plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.plan.dao.SdungeonMapper;
import kr.co.pokemon.plan.dto.SdungeonDTO;


@Service
public class SdungeonServiceImpl implements SdungeonService {

//
//    @Autowired 
//    private SdungeonMapper sdungeonMapper;
//
//    // daily_clear_count 리셋
//    @Transactional
//    public void resetDailyClearCount() {
//        sdungeonMapper.resetDailyClearCount();
//    }
//
//    // weekly_clear_count 리셋
//    @Transactional
//    public void resetWeeklyClearCount() {
//        sdungeonMapper.resetWeeklyClearCount();
//    }
//
//    // 포켓몬 데이터를 리셋하고 랜덤 포켓몬 배정
//    @Transactional
//    public void resetPokemonData() {
//        sdungeonMapper.resetPokemonData();
//    }
//
//    // 매일 00시에 daily_clear_count 리셋 (한국 시간 기준)
//    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
//    public void scheduleResetDailyClearCount() {
//        resetDailyClearCount();
//    }
//
//    // 매주 일요일 00시에 weekly_clear_count 리셋 (한국 시간 기준)
//    @Scheduled(cron = "0 0 0 ? * SUN", zone = "Asia/Seoul")
//    public void scheduleResetWeeklyClearCount() {
//        resetWeeklyClearCount();
//    }
//
//    // 매일 00시에 포켓몬 데이터를 리셋하고 배정 (한국 시간 기준)
//    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
//    public void scheduleResetPokemonData() {
//        resetPokemonData();
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public SdungeonDTO getSdungeonById(String id) {
//        System.out.println("조회할 playerId: " + id); // playerId가 잘 넘어오는지 확인
//        SdungeonDTO sdungeon = sdungeonMapper.findById(id);
//        
//        if (sdungeon == null) {
//            System.out.println("sdungeon 데이터가 없습니다.");
//        } else {
//            System.out.println("가져온 Sdungeon 데이터: " + sdungeon); // sdungeon 객체 출력
//        }
//
//        return sdungeon;
//    }


//	@Override
//	public void createSdungeonForPlayer(String id) {
//	    // 새로 생성된 player에 대해 sdungeon 데이터를 초기화
//	    SdungeonDTO sdungeonDto = SdungeonDTO.builder()
//	            .id(id)
//	            .dailyClearCount(0)
//	            .weeklyClearCount(0)
//	            .totalCount(0)
//	            .gameMoney(0)
//	            .pokemon1Id(1)  // 예시로 1번 포켓몬을 할당
//	            .pokemon1Name("이상해씨")  // 예시 포켓몬 이름
//	            .pokemon1Img("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")  // 예시 이미지
//	            .pokemon2Id(2)  // 예시로 2번 포켓몬을 할당
//	            .pokemon2Name("이상해풀")  // 예시 포켓몬 이름
//	            .pokemon2Img("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png")  // 예시 이미지
//	            .pokemon3Id(3)  // 예시로 3번 포켓몬을 할당
//	            .pokemon3Name("이상해꽃")  // 예시 포켓몬 이름
//	            .pokemon3Img("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png")  // 예시 이미지
//	            .build();
//
//	    // SDUNGEON 테이블에 데이터 삽입
//	    sdungeonMapper.insertSdungeon(sdungeonDto);
//	}

    
}