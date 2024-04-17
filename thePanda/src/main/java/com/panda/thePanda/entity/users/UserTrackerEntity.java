package com.panda.thePanda.entity.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users_tracker")
@Table(name = "users_tracker")
public class UserTrackerEntity {

  @Id
  private String id;
  private String userId;
  private String productId;
  private String searchKeyword;
  private String searchType;
  private String searchSource;
}
