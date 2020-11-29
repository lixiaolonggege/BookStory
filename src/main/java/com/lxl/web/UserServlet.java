package com.lxl.web;


import com.google.gson.Gson;
import com.lxl.pojo.User;
import com.lxl.service.Impl.UserServiceImpl;
import com.lxl.service.UserService;
import com.lxl.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
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

        //获取kaptcha生成的验证码
        HttpSession session = req.getSession();
        String kaptcha_session_key = (String)session.getAttribute("KAPTCHA_SESSION_KEY");

        User u =  WebUtils.paramsToBean(new User(),req.getParameterMap());
        //检测验证码
        if(code.equals(kaptcha_session_key)){
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
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
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

    public void logout(HttpServletRequest req,HttpServletResponse resp){
        req.getSession().invalidate();
        try {
            resp.sendRedirect(req.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajaxExistUsername(HttpServletRequest req,HttpServletResponse resp){
        //获取username
        String username = req.getParameter("username");
        boolean nameIsExist = userService.userIsExist(username);
        Map<String,Boolean> resultMap=new HashMap<>();
        resultMap.put("nameIsExist",nameIsExist);

        Gson gson=new Gson();
        String json = gson.toJson(resultMap);

        try {
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
