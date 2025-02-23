package com.onlineLibrary.mapper;

import com.onlineLibrary.entity.Books;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookManagementMapper {

    /**
     * 新增书籍
     * @param books
     */
    void addbook(Books books);

    /**
     * 删除书籍 (单个 and 批量)
     * @param ids
     * @return
     */
    void deletByIds(List<Integer> ids);

    /**
     * 根据id查询书籍信息 用于查询回显
     * @param id
     * @return
     */
    Books getById(Integer id);

    /**
     * 修改员工信息
     * @param books
     * @return
     */
    void updateBooks(Books books);
}
