package com.onlineLibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final Integer super_admin = 2;
    public static final Integer admin = 1;
    public static final Integer user = 0;

    private Integer id;
    private String username;
    private String name;
    private String password;
    private Integer role;
}
