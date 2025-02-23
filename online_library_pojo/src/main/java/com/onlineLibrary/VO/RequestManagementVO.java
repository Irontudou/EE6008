package com.onlineLibrary.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestManagementVO {

    private String title; // 书名
    private String author; // 作者
    private String isbn; // ISBN
    private Integer status;
    private LocalDateTime createTime;

}
