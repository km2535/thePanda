package com.panda.thePanda.repository.keyword_save;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordDetailBackupEntity;

@Repository
public interface KeywordDetailBackupRepository extends JpaRepository<KeywordDetailBackupEntity, String> {

}
