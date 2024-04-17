package com.panda.thePanda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserFilterDTO {
  private String userId;
  private String username;
  private String email;
  private String image;
  private String role;
}
