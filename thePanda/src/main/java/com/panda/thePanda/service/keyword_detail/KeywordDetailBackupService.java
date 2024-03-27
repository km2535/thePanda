package com.panda.thePanda.service.keyword_detail;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.thePanda.entity.keyword_save.KeywordDetailBackupEntity;
import com.panda.thePanda.entity.keyword_save.KeywordDetailEntity;
import com.panda.thePanda.repository.keyword_save.KeywordDetailBackupRepository;
import com.panda.thePanda.repository.keyword_save.KeywordDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordDetailBackupService {

  private final KeywordDetailRepository detailRepository;
  private final KeywordDetailBackupRepository backupRepository;

  @Transactional
  public void backup_data(Integer _categoryId) {
    // keyword_result의 모든 데이터를 가져온다.
    List<KeywordDetailEntity> result = detailRepository.findByCategoryId(_categoryId);

    for (KeywordDetailEntity origin : result) {
      // 기존 백업 데이터가 존재하는지 확인
      Optional<KeywordDetailBackupEntity> existingBackup = backupRepository.findById(origin.getId());

      // 존재하면 업데이트, 아니면 새로 저장
      if (existingBackup.isPresent()) {
        KeywordDetailBackupEntity target = existingBackup.get();
        // 기존 백업 데이터 업데이트
        if (target.getUpdate_date().equals(origin.getUpdate_date()))
          break;
        updateBackupEntity(target, origin);

      } else {
        KeywordDetailBackupEntity target = new KeywordDetailBackupEntity();
        // 새로운 백업 데이터 저장
        copyEntityProperties(target, origin);
      }
    }
  }

  private void updateBackupEntity(KeywordDetailBackupEntity target, KeywordDetailEntity origin) {
    // 기존 백업 데이터 업데이트
    System.out.println("업데이트 실행");
    target.setIs_new("false");
    Integer diff_cnt = origin.getTotal_qc_cnt() - target.getTotal_qc_cnt();
    target.setDiff_total_qc_cnt(diff_cnt);
    backupRepository.updateDataByKeyword(
        origin.getId(),
        origin.getKeyword(),
        origin.getTotal_product_count(),
        origin.getTop_product_link(),
        origin.getCategory1(),
        origin.getCategory2(),
        origin.getCategory3(),
        origin.getCategory4(),
        origin.getBrand(),
        origin.getProduct_name(),
        origin.getIs_season(),
        origin.getMonthly_pc_qc_cnt(),
        origin.getMonthly_mobile_qc_cnt(),
        origin.getTotal_qc_cnt(),
        origin.getMonthly_ave_pc_cnt(),
        origin.getMonthly_ave_mobile_cnt(),
        origin.getComp_idx(),
        origin.getCheck_top(),
        origin.getAd_cost(),
        origin.getLprice(),
        origin.getHprice(),
        origin.getCategory_id(),
        origin.getUpdate_date(),
        target.getCreate_date(),
        target.getIs_new(),
        target.getDiff_total_qc_cnt());
  }

  private void copyEntityProperties(KeywordDetailBackupEntity target, KeywordDetailEntity origin) {
    target.setId(origin.getId());
    target.setKeyword(origin.getKeyword());
    target.setTotal_product_count(origin.getTotal_product_count());
    target.setTop_product_link(origin.getTop_product_link());
    target.setCategory1(origin.getCategory1());
    target.setCategory2(origin.getCategory2());
    target.setCategory3(origin.getCategory3());
    target.setCategory4(origin.getCategory4());
    target.setBrand(origin.getBrand());
    target.setProduct_name(origin.getProduct_name());
    target.setIs_season(origin.getIs_season());
    target.setMonthly_pc_qc_cnt(origin.getMonthly_pc_qc_cnt());
    target.setMonthly_mobile_qc_cnt(origin.getMonthly_mobile_qc_cnt());
    target.setTotal_qc_cnt(origin.getTotal_qc_cnt());
    target.setMonthly_ave_pc_cnt(origin.getMonthly_ave_pc_cnt());
    target.setMonthly_ave_mobile_cnt(origin.getMonthly_ave_mobile_cnt());
    target.setComp_idx(origin.getComp_idx());
    target.setCheck_top(origin.getCheck_top());
    target.setAd_cost(origin.getAd_cost());
    target.setLprice(origin.getLprice());
    target.setHprice(origin.getHprice());
    target.setCategory_id(origin.getCategory_id());
    target.setUpdate_date(origin.getUpdate_date());
    target.setCreate_date(origin.getCreate_date());
    target.setIs_new("true");
    target.setDiff_total_qc_cnt(0);
    backupRepository.save(target);
  }

}
