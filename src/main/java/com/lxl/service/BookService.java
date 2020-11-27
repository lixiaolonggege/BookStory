package com.lxl.service;

import com.lxl.pojo.Book;
import com.lxl.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();
    Page<Book> page(int pageNum,int pageSize);
    Page<Book> pageByPrice(int pageNum,int pageSize,int min,int max);
}
