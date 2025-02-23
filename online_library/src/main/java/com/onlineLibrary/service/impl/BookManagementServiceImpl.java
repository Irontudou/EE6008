package com.onlineLibrary.service.impl;

import com.onlineLibrary.DTO.BooksDTO;
import com.onlineLibrary.entity.Books;
import com.onlineLibrary.mapper.BookManagementMapper;
import com.onlineLibrary.service.BookManagementService;
import com.onlineLibrary.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookManagementServiceImpl implements BookManagementService {

    @Autowired
    private BookManagementMapper bookManagementMapper;

    /**
     * 新增书籍
     * @param booksDTO
     */
    @Override
    public void addbook(BooksDTO booksDTO) {
        //接收到的数据，属性拷贝到Books
        Books books = new Books();
        BeanUtils.copyProperties(booksDTO, books);
        //完善属性 1.一些默认值
        books.setAvailable(Books.initial_available);
        books.setAvailableCopies(Books.initial_available_copies);
        books.setRating(Books.initial_rating);
        books.setTotalCopies(Books.numberOfTotalcopies);
        books.setLocation(Books.initial_location);
        //完善属性 2.时间
        books.setCreateTime(LocalDateTime.now());
        bookManagementMapper.addbook(books);
    }

    /**
     * 删除书籍 (单个 and 批量)
     * @param ids
     * @return
     */
    @Override
    public void deleteBooks(List<Integer> ids) {
       bookManagementMapper.deletByIds(ids);
    }

    /**
     * 根据id查询书籍信息 用于查询回显
     * @param id
     * @return
     */
    @Override
    public Books getById(Integer id) {
        Books books = bookManagementMapper.getById(id);
        return books;
    }

    /**
     * 修改员工信息
     * @param booksDTO
     * @return
     */
    @Override
    public void updateBooks(BooksDTO booksDTO) {
        //属性拷贝
        Books books = new Books();
        BeanUtils.copyProperties(booksDTO, books);
        //更新数据
        bookManagementMapper.updateBooks(books);
    }
}
