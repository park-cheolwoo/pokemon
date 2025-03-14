package kr.co.pokemon.player.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<FriendDTO> getPendingList(String playerId) {
    	return friendMapper.getPendingList(playerId);
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
    public boolean acceptFriend(String session_id, String playerFrom) {
        String friend = friendMapper.selectFriendByPlayerFrom(playerFrom, session_id);
        // 검색 결과는 1명이어야 의도한 대로 작동함, 여러명이어도 1명밖에 검색할 수 없음
        if (friend != null) {
            friendMapper.updateFriendAcceptByPlayerFrom(playerFrom, session_id); 
            System.out.println("친구 요청 수락 완료!");
            return true;
        }
        System.out.println("친구 요청 수락 실패: 요청이 존재하지 않거나 이미 수락됨.");
         
        return false;
    }
	@Override
	public boolean cancelFriend(String session_id, String playerFrom) {
		String friend = friendMapper.selectFriendByPlayerFrom(playerFrom, session_id);
		if(friend !=null) {
			friendMapper.cancelFriendByPlayerFrom(playerFrom, session_id);
			System.out.println("친구 요청 취소 완료!");
			return true;
		}
		System.out.println("친구 요청 취소 실패");
		return false;
	}
	
	@Override
	public boolean deleteFriend1(String session_id, String playerFrom) {
		String friend = friendMapper.selectFrinedFordelete1(playerFrom, session_id);
		if(friend !=null) {
			friendMapper.deleteFriendByPlayerFrom(playerFrom,session_id);
			System.out.println("친구 삭제 완료");
			return true;
		}
		System.out.println("친구 삭제 실패");
		return false;
	}
	
	@Override
	public boolean deleteFriend2(String session_id, String playerTo) {
		String friend = friendMapper.selectFrinedFordelete2(playerTo, session_id);
		if(friend !=null) {
			friendMapper.deleteFriendByPlayerTo(playerTo,session_id);
			System.out.println("친구 삭제 완료");
			return true;
		}
		System.out.println("친구 삭제 실패");
		return false;
	}







}
