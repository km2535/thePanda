package com.panda.thePanda.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListKeywordAndCategoryDTO {
    private Integer category;
    private List<String> list;
}
