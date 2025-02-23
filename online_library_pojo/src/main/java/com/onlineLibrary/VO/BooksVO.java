package com.onlineLibrary.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksVO {
    private Integer id; // 图书ID
    private String title; // 书名
    private String author; // 作者
    private String publisher; // 出版社
    private String isbn; // ISBN

    private LocalDateTime publishDate; // 出版日期

    private String category; // 分类
    private String cover; // 图书封面
    private Double rating; // 评分
    private String description; // 内容简介
    private String location; // 馆藏位置
    private String callNumber; // 索书号
    private Integer availableCopies; // 在馆数量
    private Integer totalCopies; // 总藏书量
    private Boolean available; // 是否可用

    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
