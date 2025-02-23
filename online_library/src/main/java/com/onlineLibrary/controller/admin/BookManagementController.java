package com.onlineLibrary.controller.admin;


import com.onlineLibrary.DTO.BooksDTO;
import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.entity.Books;
import com.onlineLibrary.entity.RequestForm;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.BookManagementService;
import com.onlineLibrary.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/book")
public class BookManagementController {

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private BookManagementService bookManagementService;

    /**
     * 条件查询书籍并分页展示
     * @param booksPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @CrossOrigin()
    public Result<PageResult> pageQuery(BooksPageQueryDTO booksPageQueryDTO) {
        PageResult pageResult = homePageService.pageQuery(booksPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增书籍
     * @param booksDTO
     * @return
     */
    @PostMapping
    @CrossOrigin()
    public Result addAdmin(@RequestBody BooksDTO booksDTO) {
        bookManagementService.addbook(booksDTO);
        return Result.success();
    }

    /**
     * 删除书籍 (单个 and 批量)
     * @param ids
     * @return
     */
    @DeleteMapping
    @CrossOrigin()
    public Result deleteBooks(@RequestParam List<Integer> ids) {
        bookManagementService.deleteBooks(ids);
        return Result.success();
    }

    /**
     * 根据id查询书籍信息 用于查询回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CrossOrigin()
    public Result<Books> getById(@PathVariable("id") Integer id) {
        Books books = bookManagementService.getById(id);
        return Result.success(books);
    }

    /**
     * 修改员工信息
     * @param booksDTO
     * @return
     */
    @PutMapping
    @CrossOrigin()
    public Result updateBooks(@RequestBody BooksDTO booksDTO) {
        bookManagementService.updateBooks(booksDTO);
        return Result.success();
    }

}
