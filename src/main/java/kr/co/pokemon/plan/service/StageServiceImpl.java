package kr.co.pokemon.plan.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.pokemon.plan.dao.StageMapper;
import kr.co.pokemon.plan.dto.StageDTO;

@Service
public class StageServiceImpl implements StageService {
    
    private final StageMapper stageMapper;

    @Autowired
    public StageServiceImpl(StageMapper stageMapper) {
        this.stageMapper = stageMapper;
    }

    @Override
    public StageDTO getStageDetails(int stageId) {
        StageDTO stage = stageMapper.getStageById(stageId);
        
        if (stage == null) {
            return null;
        }

        // habitatId 계산 (1~20 -> 1, 21~40 -> 2, ..., 161~180 -> 9)
        int habitatId = ((stageId - 1) / 20) + 1;

        // money 계산 (1, 21, 41...일 때 50, 그 이후는 +20씩 증가)
        int money = 50 + ((stageId - 1) % 20) * 20;

        // experience (모든 스테이지 공통 5)
        int experience = 5;

        // minLevel & maxLevel (5레벨 단위)
        int minLevel = ((stageId - 1) % 20) * 5 + 1;
        int maxLevel = minLevel + 4;

        // 계산된 값 설정
        stage.setHabitatId(habitatId);
        stage.setMoney(money);
        stage.setExperience(experience);
        stage.setMinLevel(minLevel);
        stage.setMaxLevel(maxLevel);

        return stage;
    }
}
