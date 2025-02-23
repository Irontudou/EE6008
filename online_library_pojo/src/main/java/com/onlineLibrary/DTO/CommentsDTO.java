package com.onlineLibrary.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDTO {
    private Integer bookId; //评论书籍
    private String content; //评论内容
    private Double rating;  //打分
}
