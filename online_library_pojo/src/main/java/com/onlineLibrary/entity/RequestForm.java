package com.onlineLibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestForm {

    public static final Integer pending =0;
    public static final Integer complete =1;
    public static final Integer fail =2;

    private Integer id; // 图书ID
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社
    private LocalDate publishYear; // 出版时间
    private String isbn; // ISBN
    private String reason;
    private Integer status;


    private LocalDateTime createTime;
    private Integer createUser;
    private LocalDateTime updateTime;
    private Integer updateUser;
}
