package com.lxl.dao.impl;

import com.lxl.dao.BaseDao;
import com.lxl.dao.BookDao;
import com.lxl.pojo.Book;


import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getSales(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select * from t_book where id=?";
        return queryOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from t_book";
        return query(Book.class,sql);
    }

    @Override
    public int totalCount() {
        String sql="select count(*) from t_book";
        return  ((Number) querySingleVal(sql)).intValue();
    }

    @Override
    public List<Book> queryItems(int begin, int pageSize) {
        String sql="select * from t_book limit ?,?";
        return query(Book.class,sql,begin,pageSize);
    }

    @Override
    public int totalCountByPrice(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        return  ((Number) querySingleVal(sql,min,max)).intValue();
    }

    @Override
    public List<Book> queryItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select * from t_book where price between ? and ? order by price asc limit ?,?";
        return query(Book.class,sql,min,max ,begin,pageSize);
    }
}
