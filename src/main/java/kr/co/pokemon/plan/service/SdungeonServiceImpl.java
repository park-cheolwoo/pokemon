package kr.co.pokemon.plan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.plan.dao.SdungeonMapper;


@Service
public class SdungeonServiceImpl implements SdungeonService {


    @Autowired 
    private SdungeonMapper sdungeonMapper;

    // daily_clear_count 리셋
    @Transactional
    public void resetDailyClearCount() {
        sdungeonMapper.resetDailyClearCount();
    }

    // weekly_clear_count 리셋
    @Transactional
    public void resetWeeklyClearCount() {
        sdungeonMapper.resetWeeklyClearCount();
    }

    // 포켓몬 데이터를 리셋하고 랜덤 포켓몬 배정
    @Transactional
    public void resetPokemonData() {
        sdungeonMapper.resetPokemonData();
    }

    // 매일 00시에 daily_clear_count 리셋 (한국 시간 기준)
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void scheduleResetDailyClearCount() {
        resetDailyClearCount();
    }

    // 매주 일요일 00시에 weekly_clear_count 리셋 (한국 시간 기준)
    @Scheduled(cron = "0 0 0 ? * SUN", zone = "Asia/Seoul")
    public void scheduleResetWeeklyClearCount() {
        resetWeeklyClearCount();
    }

    // 매일 00시에 포켓몬 데이터를 리셋하고 배정 (한국 시간 기준)
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void scheduleResetPokemonData() {
        resetPokemonData();
    }
    
}