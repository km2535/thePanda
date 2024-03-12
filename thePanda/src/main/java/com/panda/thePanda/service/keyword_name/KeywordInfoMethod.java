package com.panda.thePanda.service.keyword_name;

import org.springframework.stereotype.Service;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000000Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000001Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000002Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000003Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000004Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000005Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000006Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000007Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000008Repository;
import com.panda.thePanda.repository.keyword_info.KeywordInfo50000009Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeywordInfoMethod {
  private final KeywordInfo50000000Repository keywordInfo50000000Repository;
  private final KeywordInfo50000001Repository keywordInfo50000001Repository;
  private final KeywordInfo50000002Repository keywordInfo50000002Repository;
  private final KeywordInfo50000003Repository keywordInfo50000003Repository;
  private final KeywordInfo50000004Repository keywordInfo50000004Repository;
  private final KeywordInfo50000005Repository keywordInfo50000005Repository;
  private final KeywordInfo50000006Repository keywordInfo50000006Repository;
  private final KeywordInfo50000007Repository keywordInfo50000007Repository;
  private final KeywordInfo50000008Repository keywordInfo50000008Repository;
  private final KeywordInfo50000009Repository keywordInfo50000009Repository;

  public void saveCmpIdx(String id,
      String keyword,
      String monthly_pc_qc_cnt,
      String monthly_mobile_qc_cnt,
      String total_qc_cnt,
      String monthly_ave_pc_cnt,
      String monthly_ave_mobile_cnt,
      String comp_idx, String category) {

    switch (category) {
      case "50000000":
        keywordInfo50000000Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000001":
        keywordInfo50000001Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000002":
        keywordInfo50000002Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000003":
        keywordInfo50000003Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000004":
        keywordInfo50000004Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000005":
        keywordInfo50000005Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000006":
        keywordInfo50000006Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000007":
        keywordInfo50000007Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000008":
        keywordInfo50000008Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;
      case "50000009":
        keywordInfo50000009Repository.saveInfoCompetition(id, keyword, monthly_pc_qc_cnt, monthly_mobile_qc_cnt,
            total_qc_cnt, monthly_ave_pc_cnt, monthly_ave_mobile_cnt, comp_idx);
        break;

      default:
        break;
    }
  }

}
