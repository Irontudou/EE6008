package com.onlineLibrary.service.impl;

import com.onlineLibrary.DTO.LoginDTO;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.entity.User;
import com.onlineLibrary.mapper.LoginMapper;
import com.onlineLibrary.VO.LoginVO;
import com.onlineLibrary.service.LoginService;
import com.onlineLibrary.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginVO login01(LoginDTO loginDTO){

         User user1 = loginMapper.select(loginDTO);

         if(user1 != null){
             Map<String,Object> claims = new HashMap<>();
             claims.put("id", user1.getId());
             String jwt = JwtUtils.generateJwt(claims);
             return new LoginVO(user1.getId(), user1.getUsername(), user1.getName(),jwt);
         }else {
             return null;
         }
    }
    //user.getUsername(), user.getPassword()

    @Override
    public User selectByUsername(UserDTO userDTO) {
        return loginMapper.selectByCondition(userDTO.getUsername());
    }

    @Override
    public void register01(UserDTO userDTO) {
        loginMapper.add(userDTO);
    }


}
