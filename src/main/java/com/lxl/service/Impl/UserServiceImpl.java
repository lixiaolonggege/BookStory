package com.lxl.service.Impl;

import com.lxl.dao.UserDao;
import com.lxl.dao.impl.UserDaoImpl;
import com.lxl.pojo.User;
import com.lxl.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void regist(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByNamePw(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean userIsExist(String name) {
        if(userDao.queryUserByName(name)==null)
            return false;
        return true;
    }
}
