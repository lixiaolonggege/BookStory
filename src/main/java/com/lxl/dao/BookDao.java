package com.lxl.dao;

import com.lxl.pojo.Book;

import java.util.List;

public interface BookDao {
    int addBook(Book book);
    int deleteBookById(Integer id);
    int updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();
    int totalCount();

    /**
     * 分页查询
     * @return
     */
    List<Book> queryItems(int begin,int pageSize);


    int totalCountByPrice(int min,int max);
    List<Book> queryItemsByPrice(int begin,int pageSize,int min,int max);
}
