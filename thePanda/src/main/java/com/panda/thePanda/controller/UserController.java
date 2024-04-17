package com.panda.thePanda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panda.thePanda.dto.UserFilterDTO;
import com.panda.thePanda.dto.UserFindDTO;
import com.panda.thePanda.entity.UserEntity;
import com.panda.thePanda.entity.users.UserTrackerEntity;
import com.panda.thePanda.repository.UserRepository;
import com.panda.thePanda.service.tracker.TrackerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/panda-v1")
public class UserController {

  private final UserRepository repository;
  private final TrackerService trackerService;

  @Operation(summary = "유저의 정보를 가져온다.", description = "유저의 정보를 리턴")
  @PostMapping("/get-user")
  public UserFilterDTO getUserCertificate(
      @RequestBody UserFindDTO user) {
    UserFilterDTO userFilterResult = new UserFilterDTO();
    // 유저 정보 중 민감한 정보를 걸러준다.
    UserEntity userInfo = repository.findByUserId(user.getUserId());
    userFilterResult.setEmail(userInfo.getEmail());
    userFilterResult.setUserId(userInfo.getUserId());
    userFilterResult.setRole(userInfo.getRole());
    userFilterResult.setUsername(userInfo.getUsername());
    userFilterResult.setImage(userInfo.getImage());

    return userFilterResult;
  }

  @Operation(summary = "유저의 추적 상품 정보를 가져온다.", description = "유저의 추적 상품 정보를 리턴")
  @GetMapping("/get-track")
  public List<UserTrackerEntity> getUserTrackerInfo(
      @RequestParam String userId) {
    return trackerService.getAllTrackInfo(userId);
  }

  @Operation(summary = "유저의 추적 상품 정보를 저장한다.", description = "유저의 추적 상품 정보를 저장")
  @PostMapping("/save-track")
  public void saveUSerTrackInfo(
      @RequestBody UserTrackerEntity userTrackerEntity) {
    trackerService.saveTrackInfo(userTrackerEntity);
  }

  @Operation(summary = "유저의 추적 상품 정보를 삭제한다.", description = "유저의 추적 상품 정보를 삭제")
  @DeleteMapping("/delete-track")
  public void deleteUserTrackerInfo(
      @RequestBody String id) {
    trackerService.deleteTrackInfo(id);
  }

}
