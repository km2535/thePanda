package com.panda.thePanda.entity.keyword_info_base;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "keyword_info_base_error")
@Table(name = "keyword_info_base_error")
public class KeywordInfoBaseNameErrorEntity {

  @Id
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "category")
  private String category;
  @Column(name = "update_date")
  private Date update_date;

}
