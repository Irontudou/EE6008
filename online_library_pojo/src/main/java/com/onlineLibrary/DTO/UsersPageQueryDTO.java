package com.onlineLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersPageQueryDTO implements Serializable {


    private String username;

    //页码
    private int page;
    //每页显示记录数
    private int pageSize;
    //
    private Integer role;

}
