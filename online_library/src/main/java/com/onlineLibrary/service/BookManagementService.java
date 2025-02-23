package com.onlineLibrary.service;

import com.onlineLibrary.DTO.BooksDTO;
import com.onlineLibrary.entity.Books;

import java.util.List;

public interface BookManagementService {

    /**
     * 新增书籍
     * @param booksDTO
     */
    void addbook(BooksDTO booksDTO);

    /**
     * 删除书籍 (单个 and 批量)
     * @param ids
     * @return
     */
    void deleteBooks(List<Integer> ids);

    /**
     * 根据id查询书籍信息 用于查询回显
     * @param id
     * @return
     */
    Books getById(Integer id);

    /**
     * 修改员工信息
     * @param booksDTO
     * @return
     */
    void updateBooks(BooksDTO booksDTO);
}
