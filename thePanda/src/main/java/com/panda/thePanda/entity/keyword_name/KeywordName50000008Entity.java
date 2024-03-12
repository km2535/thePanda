package com.panda.thePanda.entity.keyword_name;

import java.sql.Date;

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
@Entity(name = "keyword_name_50000008")
@Table(name = "keyword_name_50000008")
public class KeywordName50000008Entity {
  @Id
  @Column(name = "keyword")
  private String keyword;
  @Column(name = "updatedate")
  private Date updatedate;
  @Column(name = "createdate")
  private Date createdate;
}
