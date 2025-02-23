package com.onlineLibrary.mapper;

import com.github.pagehelper.Page;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.DTO.UsersPageQueryDTO;
import com.onlineLibrary.VO.UserManagementVO;
import com.onlineLibrary.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 条件查询员工并分页
     * @param usersPageQueryDTO
     * @return
     */
    Page<UserManagementVO> select01(UsersPageQueryDTO usersPageQueryDTO);

    /**
     * 新增管理员
     * @param user
     */
    void addAdmin(User user);

    /**
     * 根据id查询员工信息 用于查询回显
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    /**
     * 修改员工信息 修改用户role管理员与普通用户转变 修改员工基本信息
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 员工/用户删除 (单个 and 批量)
     * @param ids
     * @return
     */
    void deletByIds(List<Integer> ids);
}
