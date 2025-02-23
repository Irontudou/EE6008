package com.onlineLibrary.controller;

import com.onlineLibrary.DTO.LoginDTO;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.VO.LoginVO;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @CrossOrigin()
    public Result<LoginVO> login01(@RequestBody LoginDTO loginDTO) {

        String password = loginDTO.getPassword();
        loginDTO.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        LoginVO loginVO =  loginService.login01(loginDTO);

        if (loginVO != null) {
           return Result.success(loginVO);
        }else{
           return Result.error("wrong username or password");
        }
    }

    @PostMapping("/logout")
    @CrossOrigin()
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 普通用户注册
     * @param userDTO
     * @return
     */
    @PostMapping("/register01")
    @CrossOrigin()
    public Result register01(@RequestBody UserDTO userDTO) {
        User user1 = loginService.selectByUsername(userDTO);
        String password = userDTO.getPassword();
        userDTO.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

        if (user1 != null) {
            System.out.println("username:"+ user1.getUsername()+"is existed");
            return Result.error("username is existed");
        }else{
            loginService.register01(userDTO);
            return Result.success(userDTO);
        }
    }
}

