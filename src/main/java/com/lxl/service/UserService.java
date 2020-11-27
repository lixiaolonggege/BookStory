package com.lxl.service;

import com.lxl.pojo.User;

public interface UserService {
    void regist(User user);
    User login(User user);
    boolean userIsExist(String name);
}
