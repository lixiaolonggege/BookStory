package com.lxl.dao;

import com.lxl.pojo.User;

public interface UserDao {
    User queryUserByName(String name);

    int saveUser(User user);

    User queryUserByNamePw(String name,String password);
}
