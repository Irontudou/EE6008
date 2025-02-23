package com.onlineLibrary.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private Integer id;
    private Integer bookId;
    private String content;
    private Double rating;

    private LocalDateTime createTime;
    private Integer createUser;
    private LocalDateTime updateTime;
    private Integer updateUser;

}

