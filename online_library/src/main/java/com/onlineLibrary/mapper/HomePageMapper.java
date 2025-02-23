package com.onlineLibrary.mapper;

import com.github.pagehelper.Page;
import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.VO.BooksVO;
import com.onlineLibrary.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomePageMapper {
     Page<BooksVO> select01(BooksPageQueryDTO booksPageQueryDTO);

     @Select("SELECT * FROM book ORDER BY rating DESC LIMIT 5;")
     List<Books> seletTopBooks();

     @Select("SELECT * FROM book ORDER BY publishDate DESC LIMIT 5;")
     List<Books> seletNewBooks();
}
