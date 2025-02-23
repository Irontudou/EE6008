package com.onlineLibrary.service;

import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.DTO.UsersPageQueryDTO;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.result.PageResult;

import java.util.List;

public interface UserService {

    /**
     * 条件查询员工并分页
     * @param usersPageQueryDTO
     * @return
     */
    PageResult pageQuery(UsersPageQueryDTO usersPageQueryDTO);

    /**
     * 新增管理员
     * @param userDTO
     */
    void addAdmin(UserDTO userDTO);

    /**
     * 根据id查询员工信息 用于查询回显
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 修改员工信息
     * @param userDTO
     * @return
     */
    void updateUser(UserDTO userDTO);

    /**
     * 员工/用户删除 (单个 and 批量)
     * @param ids
     * @return
     */
    void deleteAdminOrUser(List<Integer> ids);
}
