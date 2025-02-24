package com.onlineLibrary.service;

import com.onlineLibrary.DTO.BooksDTO;
import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.CommentsDTO;
import com.onlineLibrary.VO.BooksVO;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;

import java.util.List;

public interface BookPageService {

    /**
     * 插入评论及分数 更新
     * @param commentsDTO
     */
    void insertComments(CommentsDTO commentsDTO);


    /**
     * 计算均分
     * @param bookId
     * @return
     */
    Double getAverageRating(Integer bookId);

    /**
     * 更新均分到book表
     * @param bookId
     * @param averageRating
     */
    void updateRating(Integer bookId, Double averageRating);

    /**
     * 查询书籍
     * @param booksPageQueryDTO
     * @return
     */
    PageResult pageQuery(BooksPageQueryDTO booksPageQueryDTO);


    /**
     * 条件查询书籍并分页展示。用滑条作条件，分别rating, publishDate, category三个参数
     * @param booksPageQueryDTO
     * @return
     */
    PageResult slideQuery(BooksPageQueryDTO booksPageQueryDTO);




}
