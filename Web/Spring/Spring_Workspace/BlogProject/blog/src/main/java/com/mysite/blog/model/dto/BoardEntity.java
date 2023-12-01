package com.mysite.blog.model.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreateDt = LocalDateTime.now();
    private LocalDateTime boardModifyDt = LocalDateTime.now();
}
