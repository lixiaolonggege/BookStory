package com.lxl.dao.impl;

import com.lxl.dao.BaseDao;
import com.lxl.dao.UserDao;
import com.lxl.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByName(String name) {
        String sql="select * from t_user where username=?";
        return queryOne(User.class,sql,name);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByNamePw(String name, String password) {
        String sql="select * from t_user where username=? and password=?";
        return queryOne(User.class,sql,name,password);
    }
}
