package com.lxl.web;

import com.lxl.dao.BaseDao;
import com.lxl.pojo.User;
import com.lxl.service.Impl.UserServiceImpl;
import com.lxl.service.UserService;
import com.lxl.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    public void login(HttpServletRequest req,HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(new User(username, password, null));
        if(user==null){
            try {
                resp.sendRedirect("pages/user/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                req.getRequestDispatcher("pages/user/login_success.html").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void regist(HttpServletRequest req,HttpServletResponse resp){
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String  code= req.getParameter("code");

        User u =  WebUtils.paramsToBean(new User(),req.getParameterMap());
        //检测验证码  验证码写死为abcde
        if(code.equals("abcde")){
            //检查用户名是否可用
            if(userService.userIsExist(username)){
                System.out.println("用户名不可用！");
                try {
                    resp.sendRedirect("pages/user/regist.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                userService.regist(new User(username,password,email));
                try {
                    req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("验证码错误!");
            try {
                resp.sendRedirect("pages/user/regist.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
