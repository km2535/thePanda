package com.panda.thePanda.entity.keyword_save;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "keyword_top")
@Table(name = "keyword_top")
public class KeywordSaveEntity {
  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "category_id")
  private Integer category_id;
  @Column(name = "rank")
  private Integer rank;
  @Column(name = "create_date")
  private String create_date;
}
