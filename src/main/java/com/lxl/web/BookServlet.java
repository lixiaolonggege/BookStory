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
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService  bookService=new BookServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response){
        Book book = WebUtils.paramsToBean(new Book(), request.getParameterMap());
        bookService.addBook(book);
        try {
            response.sendRedirect("http://localhost:8080/Book_war_exploded/manager/bookServlet?action=page");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(HttpServletRequest request, HttpServletResponse response){
        Integer id = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBookById(id);
        try {
            response.sendRedirect("http://localhost:8080/Book_war_exploded/manager/bookServlet?action=page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response){
        Book book = WebUtils.paramsToBean(new Book(), request.getParameterMap());
        bookService.updateBook(book);
        try {
            response.sendRedirect("http://localhost:8080/Book_war_exploded/manager/bookServlet?action=page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response){
        List<Book> books = bookService.queryBooks();
        request.setAttribute("books",books);
        try {
            request.getRequestDispatcher("/pages/manager/books.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBook(HttpServletRequest request,HttpServletResponse response){
        Integer id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        try {
            request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理分页
     * @param request
     */
    public void page(HttpServletRequest request,HttpServletResponse response){
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNum, pageSize);
        request.setAttribute("page",page);
        try {
            request.getRequestDispatcher("/pages/manager/books.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
