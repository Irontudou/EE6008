package com.onlineLibrary.controller.admin;


import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.DTO.UsersPageQueryDTO;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.mapper.UserMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.HomePageService;
import com.onlineLibrary.service.LoginService;
import com.onlineLibrary.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserManagementController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;


    /**
     * 条件查询员工并分页
     * @param usersPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @CrossOrigin()
    public Result<PageResult> pageQuery(UsersPageQueryDTO usersPageQueryDTO) {
        PageResult pageResult = userService.pageQuery(usersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增管理员
     */
    @PostMapping
    @CrossOrigin()
    public  Result addAdmin(@RequestBody UserDTO userDTO) {
        User user = loginService.selectByUsername(userDTO);
        if (user != null) {
            System.out.println("username:"+ user.getUsername()+"is existed");
            return Result.error("username is existed");
        }else{
            userService.addAdmin(userDTO);
            return Result.success();
        }
    }

    /**
     * 根据id查询员工信息 用于查询回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CrossOrigin()
    public Result<User> getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 修改员工信息
     * @param userDTO
     * @return
     */
    @PutMapping
    @CrossOrigin()
    public Result updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return Result.success();
    }

    /**
     * 员工/用户删除 (单个 and 批量)
     * @param ids
     * @return
     */
    @DeleteMapping
    @CrossOrigin()
    public Result deleteDishAndFlavor(@RequestParam List<Integer> ids) {
        userService.deleteAdminOrUser(ids);
        return Result.success();
    }

}
