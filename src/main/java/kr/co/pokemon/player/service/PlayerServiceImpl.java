package kr.co.pokemon.player.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.plan.service.SdungeonService;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dto.PlayerDTO;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired private SdungeonService sdungeonService;
	
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<PlayerDTO> getAll(PageRequestDTO pDTO) {
        return playerMapper.selectAll(pDTO);
    }

//    @Override
//    public PlayerDTO findById(int id) {
//        Map<String, Object> map = new HashMap<>();
//        PlayerDTO playerDto = playerMapper.selectById(id);
//        map.put("playerDto", playerDto);
//        return playerDto;
//    }

    @Override
    public PlayerDTO getById(String id) {
        return playerMapper.selectById(id);
    }

    @Override
    public PlayerDTO updatePlayer(int id,PlayerDTO player) {
        throw new UnsupportedOperationException("아직 구현되지 않았습니다.");
    }

    @Override
    public void updatePlayerBySystem(PlayerDTO player) {
       playerMapper.updatePlayerBySystem(player);
    }

    @Override
    public void deletePlayer(int id) {
        throw new UnsupportedOperationException("아직 구현되지 않았습니다.");
    }

    @Override
    public PlayerDTO login(String id, String password) {
        return playerMapper.selectLogin(id, password);
    }

    @Override
    public boolean isIdAble(String id) {
        PlayerDTO playerdto = playerMapper.chooseById(id);
        return playerdto == null; 
    }

    public boolean insertPlayer(PlayerDTO player) {
        player.setTag(generateRandomTag());
        return playerMapper.insertPlayer(player) > 0;
    }
    
//    public boolean insertPlayer(PlayerDTO player) {
//        player.setTag(generateRandomTag()); 
//        boolean isPlayerInserted = playerMapper.insertPlayer(player) > 0; 
//        if (isPlayerInserted) {
//        	sdungeonService.createSdungeonForPlayer(player.getId());
//        }
//        return isPlayerInserted;
//    }

    private String generateRandomTag() {
        return "#" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9); // 랜덤 태그 생성
    }

	@Override
	public List<PlayerDTO> getByNickname(String keyword) {
		return playerMapper.getByNickname(keyword);
	}

	


}

