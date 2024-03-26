package com.panda.thePanda.repository.keyword_save;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panda.thePanda.entity.keyword_save.KeywordSaveEntity;

@Repository
public interface KeywordSaveRepository extends JpaRepository<KeywordSaveEntity, String> {

}
