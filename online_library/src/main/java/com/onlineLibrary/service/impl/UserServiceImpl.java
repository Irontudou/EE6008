package com.onlineLibrary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.DTO.UsersPageQueryDTO;
import com.onlineLibrary.VO.UserManagementVO;
import com.onlineLibrary.constant.PasswordConstant;
import com.onlineLibrary.context.BaseContext;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.mapper.UserMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 条件查询员工并分页
     * @param usersPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(UsersPageQueryDTO usersPageQueryDTO) {
        //设置分页参数
        PageHelper.startPage(usersPageQueryDTO.getPage(), usersPageQueryDTO.getPageSize());
        //调用Mapper查数据
        Page<UserManagementVO> page = userMapper.select01(usersPageQueryDTO);
        //数据封装
        long total = page.getTotal();
        List<UserManagementVO> records = page.getResult();
        //返回
        return new PageResult(total, records);
    }

    /**
     * 新增管理员
     * @param userDTO
     */
    @Override
    public void addAdmin(UserDTO userDTO) {
        //接收到的数据，属性拷贝到User
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        //完善属性 1.设置状态
        user.setRole(User.admin);
        //完善属性 2.设置密码
        user.setPassword(DigestUtils.md5DigestAsHex
                (PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        userMapper.addAdmin(user);
    }

    /**
     * 根据id查询员工信息 用于查询回显
     * @param id
     * @return
     */
    @Override
    public User getById(Integer id) {
        User user = userMapper.getById(id);
        user.setPassword("****");
        return user;
    }

    /**
     * 修改员工信息 修改用户role管理员与普通用户转变 修改员工基本信息
     * @param userDTO
     * @return
     */
    @Override
    public void updateUser(UserDTO userDTO) {
        //拷贝修改信息 username/name/password/role
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userMapper.updateUser(user);
    }

    /**
     * 员工/用户删除 (单个 and 批量)
     * @param ids
     * @return
     */
    @Override
    public void deleteAdminOrUser(List<Integer> ids) {
        userMapper.deletByIds(ids);
    }
}
