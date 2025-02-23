package com.onlineLibrary.service;

import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.entity.Books;
import com.onlineLibrary.result.PageResult;

import java.util.List;

public interface HomePageService {
    /**
     * 主页面书籍展示
     * @param booksPageQueryDTO
     * @return
     */
    PageResult pageQuery(BooksPageQueryDTO booksPageQueryDTO);

    /**
     * 查询评分最高书籍
     * @return
     */
    List<Books> topbooksQuery();

    /**
     * 查询新书籍
     * @return
     */
    List<Books> newbooksQuery();
}
