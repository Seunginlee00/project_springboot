package com.java.project.api.dto;

public record SearchDto(
       //날짜
        String start,
        String end,

       //검색어
        String searchType,
        String searchValue

//        Boolean isUse,
//       Role role
) {
}
