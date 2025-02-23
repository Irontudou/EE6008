package com.onlineLibrary.controller;


import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.CommentsDTO;
import com.onlineLibrary.VO.BooksVO;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.BookPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookPageController {

    @Autowired
    private BookPageService bookPageService;
    /**
     * 插入评论及打分 并更新
     * @param commentsDTO
     * @return
     */
    @PostMapping
    @CrossOrigin()
    public Result insertComments(@RequestBody CommentsDTO commentsDTO){
        //插入评论
        bookPageService.insertComments(commentsDTO);
        // 获取最新的书籍评分平均值 返回给前端
        Double averageRating = bookPageService.getAverageRating(commentsDTO.getBookId());
        //更新rating到book表
        bookPageService.updateRating(commentsDTO.getBookId(),averageRating);
        return Result.success(averageRating);
    }

    /**
     * 条件查询书籍并分页展示
     * @param booksPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @CrossOrigin()
    public Result<PageResult> pageQuery(BooksPageQueryDTO booksPageQueryDTO){
        PageResult pageResult = bookPageService.pageQuery(booksPageQueryDTO);
        return Result.success(pageResult);
    }
    //查询


    /**
     * 根据滑条rating搜索书籍
     * @param rating
     * @return
     */
    @GetMapping("/rating")   // 这里用/{id}还是/id？？查询回显为啥用{id}来着？？
    public Result<List<BooksVO>> getBooksByRating(Integer rating){

        List<BooksVO> listBooks = bookPageService.getBooksByRating(rating);
        return Result.success(listBooks);
    }

    /**
     * 根据滑条publishYear搜索书籍
     * @param publishDate
     * @return
     */
    @GetMapping("/publishDate")   // 这里用/{id}还是/id？？查询回显为啥用{id}来着？？
    public Result<List<BooksVO>> getBooksByPublishDate(Integer publishDate){

        List<BooksVO> listBooks = bookPageService.getBooksByPublishDate(publishDate);
        return Result.success(listBooks);
    }

    /**
     * 根据标签category搜索书籍
     * @param category
     * @return
     */
    @GetMapping("/category")   // 这里用/{id}还是/id？？查询回显为啥用{id}来着？？
    public Result<List<BooksVO>> getBooksByPublishDate(String category){

        List<BooksVO> listBooks = bookPageService.getBooksByCategory(category);
        return Result.success(listBooks);
    }

}
