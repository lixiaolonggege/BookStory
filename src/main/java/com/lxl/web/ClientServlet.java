package com.lxl.web;

import com.lxl.pojo.Book;
import com.lxl.pojo.Page;
import com.lxl.service.BookService;
import com.lxl.service.Impl.BookServiceImpl;
import com.lxl.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    public void page(HttpServletRequest request,HttpServletResponse response) {
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNum, pageSize);
        page.setUrl("clientServlet?action=pageByPrice");
        request.setAttribute("page", page);
        try {
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pageByPrice(HttpServletRequest request,HttpServletResponse response){

        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);


        Page<Book> page = bookService.pageByPrice(pageNum, pageSize,min,max);
        StringBuilder sb = new StringBuilder("clientServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());

        request.setAttribute("page", page);
        try {
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
