package com.panda.thePanda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panda.thePanda.entity.users.UserTrackerEntity;

public interface UserTrackerRepository extends JpaRepository<UserTrackerEntity, String> {

  List<UserTrackerEntity> findAllByUserId(String userId);

}
