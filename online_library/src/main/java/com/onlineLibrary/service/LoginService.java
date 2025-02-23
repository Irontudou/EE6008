package com.onlineLibrary.service;

import com.onlineLibrary.DTO.LoginDTO;
import com.onlineLibrary.DTO.UserDTO;
import com.onlineLibrary.VO.LoginVO;
import com.onlineLibrary.entity.User;

public interface LoginService {
     LoginVO login01(LoginDTO loginDTO);

     User selectByUsername(UserDTO userDTO);

     void register01(UserDTO userDTO);


}


