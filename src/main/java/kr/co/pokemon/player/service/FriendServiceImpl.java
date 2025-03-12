package kr.co.pokemon.player.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pokemon.player.dao.FriendMapper;
import kr.co.pokemon.player.dao.PlayerMapper;
import kr.co.pokemon.player.dto.FriendDTO;
import kr.co.pokemon.player.dto.PlayerDTO;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<FriendDTO> getFriendList(String playerId) {
        return friendMapper.getFriendList(playerId);
    }

    @Override
    public boolean addFriend(String fromId, String tag) {
        PlayerDTO toPlayer = playerMapper.findByTag(tag); // 태그로 상대방 검색
        if (toPlayer == null) {
            System.out.println("DEBUG: 태그를 가진 사용자가 없습니다. 태그: " + tag);
            return false;
        }

        Long friendId = System.currentTimeMillis(); 
        friendMapper.insertFriend(friendId, fromId, toPlayer.getId()); 
        System.out.println("DEBUG: 친구 요청 성공. ID: " + friendId + ", 요청자: " + fromId + ", 대상자: " + toPlayer.getId());
        return true;
    }


    @Override
    public boolean acceptFriend(String sessionId, String playerId) {
        // 친구 요청 정보 확인
        FriendDTO friend = friendMapper.selectFriend(playerId, sessionId);
        if (friend != null && !friend.isAccept()) {
            friendMapper.updateFriendAccept(playerId, sessionId);
            System.out.println("DEBUG: 친구 요청 수락 성공. 수락자: " + sessionId + ", 요청자: " + playerId);
            return true;
        }
        System.out.println("DEBUG: 수락할 친구 요청이 없거나 이미 수락되었습니다.");
        return false;
    }
    
    @Override
    public List<FriendDTO> getPending(String playerToId) {
        List<FriendDTO> pendingRequests = friendMapper.getPending(playerToId);
        System.out.println("DEBUG: pendingRequests = " + pendingRequests); // 디버깅 로그
        return pendingRequests;
    }

}
