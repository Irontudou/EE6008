package com.onlineLibrary.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

        //默认值
        public static final  Double  initial_rating = 0.0;
        public static final  Integer numberOfTotalcopies =20;
        public static final  Integer initial_available_copies = 20;
        public static final  Integer initial_available = 1;
        public static final  String  initial_location = "Lee Wee Nam Library";

        private Integer id; // 图书ID
        private String title; // 书名
        private String author; // 作者
        private String publisher; // 出版社
        private String isbn; // ISBN

        private LocalDate publishDate; // 出版日期

        private String category; // 分类
        private String cover; // 图书封面
        private Double rating; // 评分
        private String description; // 内容简介
        private String location; // 馆藏位置
        private String callNumber; // 索书号
        private Integer availableCopies; // 在馆数量
        private Integer totalCopies; // 总藏书量
        private Integer available; // 是否可用

        private LocalDateTime createTime; // 创建时间
        private LocalDateTime updateTime; // 更新时间
}
