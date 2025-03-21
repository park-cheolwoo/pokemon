package kr.co.pokemon.player.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.plan.service.SdungeonService;
import kr.co.pokemon.play.dao.IngameMapper;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dao.PlayerPokemonMapper;
import kr.co.pokemon.player.dto.ClearDungeonDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private IngameMapper ingameMapper;
    @Autowired
    private PlayerPokemonMapper playerPokemonMapper;
    @Autowired
    private SdungeonService sdungeonService;

    @Override
    public List<PlayerDTO> getAll(PageRequestDTO pDTO) {
        return playerMapper.selectAll(pDTO);
    }

    @Override
    public PlayerDTO getById(String id) {
        return playerMapper.selectById(id);
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
        boolean isPlayerInserted = playerMapper.insertPlayer(player) > 0;
        // 플레이어가 성공적으로 추가되면 해당 플레이어의 sdungeon 데이터도 생성
        if (isPlayerInserted) {
            sdungeonService.createSdungeonForPlayer(player.getId());
        }
        return isPlayerInserted;
    }

    private String generateRandomTag() {
        return "#" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9); // 랜덤 태그 생성
    }

	@Override
	public List<PlayerDTO> getByNickname(String keyword) {
		return playerMapper.getByNickname(keyword);
	}

	@Override
	public void insertIngameData(String id) {
		ingameMapper.insertIngame(id);
	}

	@Override
    public int countPlayerPokemon(String playerId) {
        return playerPokemonMapper.countPokemonByPlayerId(playerId);
    }
	
	@Override
    public void updateplayer(String sessionId, PlayerDTO playerDto) {
        PlayerDTO existingPlayer = validateAndGetPlayer(sessionId);

        if (playerDto.getLv() > 0 && playerDto.getLv() != existingPlayer.getLv()) {
            existingPlayer.setLv(playerDto.getLv());
        }
        if (playerDto.getExperience() > 0 && playerDto.getExperience() != existingPlayer.getExperience()) {
            existingPlayer.setExperience(playerDto.getExperience());
        }
        if (playerDto.getGameMoney() >= 0 && playerDto.getGameMoney() != existingPlayer.getGameMoney()) {
            existingPlayer.setGameMoney(playerDto.getGameMoney());
        }
        if (playerDto.getRealMoney() >= 0 && playerDto.getRealMoney() != existingPlayer.getRealMoney()) {
            existingPlayer.setRealMoney(playerDto.getRealMoney());
        }
        playerMapper.updateplayer(existingPlayer);
    }

    @Override
    public void updateplayerLevel(String sessionId, int level) {
        PlayerDTO player = validateAndGetPlayer(sessionId);
        player.setLv(level);
        playerMapper.updateplayer(player);
    }

    @Override
    public void updateplayerExperience(String sessionId, int experience) {
        PlayerDTO player = validateAndGetPlayer(sessionId);

        int totalExpr = player.getExperience() + experience;
        player.setExperience(totalExpr % 100);
        player.setLv((int)(totalExpr / 100) + player.getLv());
        playerMapper.updateplayer(player);
    }

    private PlayerDTO validateAndGetPlayer(String id) {
        PlayerDTO player = playerMapper.selectById(id);
        if (player == null) {
            throw new IllegalArgumentException("플레이어를 찾을 수 없습니다: " + id);
        }
        return player;
    }

    @Override
    public void increaseGoldByPlayer(String sessionId, int gold) {
    	PlayerDTO player = validateAndGetPlayer(sessionId);
    	player.setGameMoney(player.getGameMoney() + gold);
    	playerMapper.updateplayer(player);
    }

    @Override
    public void clearDungeon(ClearDungeonDTO clearDungeon) {
    	PlayerDTO player = validateAndGetPlayer(clearDungeon.getPlayerId());
    	int gold = player.getGameMoney();
    	int experience = player.getExperience();
    	int level = 0;

    	if (clearDungeon.getGold() > 0) {
    		gold += clearDungeon.getGold();
    	}
    	
    	if (clearDungeon.getExperience() > 0) {
    		int total = experience + clearDungeon.getExperience();
    		experience = total % 100;
    		level = (int)(total / 100);
    	}

    	player.setLv(level + player.getLv());
    	player.setGameMoney(gold);
    	player.setExperience(experience);
    	playerMapper.updateplayer(player);
    }
}

