package com.panda.thePanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  boolean existsByUserId(String userId);

  UserEntity findByUserId(String userId);
}