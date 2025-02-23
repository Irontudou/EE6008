package com.onlineLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFormDTO {

    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社
    private LocalDate publishYear; // 出版时间
    private String isbn; // ISBN
    private String reason;
    private Integer status;

}
