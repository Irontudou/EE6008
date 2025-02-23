package com.onlineLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksPageQueryDTO implements Serializable {


    private String title;
    private String author;
    private String isbn;
    private String publisher; // 出版社
    private LocalDateTime publishDate; // 出版日期
    private String category; // 分类

    //页码
    private int page;
    //每页显示记录数
    private int pageSize;

}
