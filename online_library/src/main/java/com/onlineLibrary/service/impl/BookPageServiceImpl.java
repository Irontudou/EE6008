package com.onlineLibrary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.CommentsDTO;
import com.onlineLibrary.VO.BooksVO;
import com.onlineLibrary.entity.Comments;
import com.onlineLibrary.mapper.BookPageMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.service.BookPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookPageServiceImpl implements BookPageService {

    @Autowired
    private BookPageMapper bookPageMapper;
    /**
     * 插入评论  均值  更新
     * @param commentsDTO
     */
    @Transactional
    @Override
    public void insertComments(CommentsDTO commentsDTO) {

        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsDTO, comments);

        bookPageMapper.insertComment(comments);
        // 计算书籍的平均评分
        Double averageRating = bookPageMapper.getAverageRating(comments.getBookId());
        //更新到book表中
        bookPageMapper.updateBookRating(comments.getBookId(), averageRating);
    }


    /**
     * 计算均值
     * @param bookId
     * @return
     */
    @Override
    public Double getAverageRating(Integer bookId) {
        return bookPageMapper.getAverageRating(bookId);
    }

    /**
     * 更新rating
     * @param bookId
     * @param averageRating
     */
    @Override
    public void updateRating(Integer bookId, Double averageRating) {
        bookPageMapper.updateBookRating(bookId,averageRating);
    }

    @Override
    public PageResult pageQuery(BooksPageQueryDTO booksPageQueryDTO) {
        //设置分页参数
        PageHelper.startPage(booksPageQueryDTO.getPage(), booksPageQueryDTO.getPageSize());
        //调用Mapper查数据
        Page<BooksVO> page = bookPageMapper.select01(booksPageQueryDTO);

        //数据封装
        long total = page.getTotal();
        List<BooksVO> records = page.getResult();
        //返回
        return new PageResult(total, records);
    }

    @Override
    public PageResult slideQuery(BooksPageQueryDTO booksPageQueryDTO) {
        //设置分页参数
        PageHelper.startPage(booksPageQueryDTO.getPage(), booksPageQueryDTO.getPageSize());

        //调用Mapper查数据
        Page<BooksVO> page = bookPageMapper.select02(booksPageQueryDTO);
        //数据封装
        long total = page.getTotal();
        List<BooksVO> records = page.getResult();

        return new PageResult(total, records);
    }







}
