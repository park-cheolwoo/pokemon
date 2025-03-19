package kr.co.pokemon.plan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.pokemon.plan.dto.StageDTO;
import kr.co.pokemon.plan.service.StageService;

@RestController
@RequestMapping("/stages")
public class StageController {
	
	
    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/{stageId}")
    public ResponseEntity<StageDTO> getStage(@PathVariable int stageId) {
        StageDTO stage = stageService.getStageDetails(stageId);
        if (stage != null) {
            return ResponseEntity.ok(stage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}