package com.panda.thePanda.service.tracker;

import java.util.List;
import org.springframework.stereotype.Service;

import com.panda.thePanda.entity.users.UserTrackerEntity;
import com.panda.thePanda.repository.UserTrackerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackerService {
  private final UserTrackerRepository trackerRepository;

  // 추적정보 가져오기
  public List<UserTrackerEntity> getAllTrackInfo(String userId) {
    return trackerRepository.findAllByUserId(userId);
  }

  // 추적 정보 저장하기
  public void saveTrackInfo(UserTrackerEntity trackEntity) {
    trackerRepository.save(trackEntity);
  }

  // 추적 정보 삭제하기
  public void deleteTrackInfo(String id) {
    trackerRepository.deleteById(id);
  }

}
