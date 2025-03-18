package kr.co.pokemon.player.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pokemon.data.dto.PageRequestDTO;
import kr.co.pokemon.play.dao.IngameMapper;
import kr.co.pokemon.play.dto.IngameDTO;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dao.PlayerPokemonMapper;
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
        return playerMapper.insertPlayer(player) > 0; 
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

        player.setExperience(player.getExperience() + experience);
        if (player.getExperience() >= 100) {
            player.setExperience(player.getExperience() - 100); 
            player.setLv(player.getLv() + 1); 
        }

        playerMapper.updateplayer(player);
    }

    private PlayerDTO validateAndGetPlayer(String id) {
        PlayerDTO player = playerMapper.selectById(id);
        if (player == null) {
            throw new IllegalArgumentException("플레이어를 찾을 수 없습니다: " + id);
        }
        return player;
    }

}

