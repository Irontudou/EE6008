package com.onlineLibrary.controller;


import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.entity.Books;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    /**
     * 条件查询书籍并分页展示
     * @param booksPageQueryDTO
     * @return
     */
    //条件查询 非homepage多功能查询
    @GetMapping("/page")
    @CrossOrigin()
    public Result<PageResult> pageQuery(BooksPageQueryDTO booksPageQueryDTO){
        PageResult pageResult = homePageService.pageQuery(booksPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 评分最高书籍/新书籍展示
     * @return
     */
    @GetMapping()
    @CrossOrigin()
    public Result<Map<String,List<Books>>> topBooksQuery(){

        List<Books> topBooks = homePageService.topbooksQuery();
        List<Books> newBooks = homePageService.newbooksQuery();

        Map<String, List<Books>> result = new HashMap<>();
        result.put("topBooks", topBooks);
        result.put("newBooks", newBooks);

        return Result.success(result);
    }

}
