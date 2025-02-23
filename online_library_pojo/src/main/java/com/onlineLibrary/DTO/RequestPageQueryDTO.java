package com.onlineLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPageQueryDTO implements Serializable {

    private String title; // 书名
    private String author; // 作者

    private Integer status;
    private Integer createUser;
    //页码
    private int page;
    //每页显示记录数
    private int pageSize;

}
