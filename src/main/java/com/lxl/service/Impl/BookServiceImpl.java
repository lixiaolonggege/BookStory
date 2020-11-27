package com.lxl.service.Impl;

import com.lxl.dao.BookDao;
import com.lxl.dao.impl.BookDaoImpl;
import com.lxl.pojo.Book;
import com.lxl.pojo.Page;
import com.lxl.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNum, int pageSize) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        int pageTotalCount=(Integer) bookDao.totalCount();
        page.setPageTotalCount(pageTotalCount);
        int pageTotal=pageTotalCount%pageSize==0?pageTotalCount/pageSize:pageTotalCount/pageSize+1;
        page.setPageTotal(pageTotal);
        page.setPageNum(pageNum);
        int begin=(pageNum-1)*pageSize;
        List<Book> items=bookDao.queryItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNum, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();

        page.setPageSize(pageSize);
        int pageTotalCount=(Integer) bookDao.totalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        int pageTotal=pageTotalCount%pageSize==0?pageTotalCount/pageSize:pageTotalCount/pageSize+1;
        page.setPageTotal(pageTotal);
        page.setPageNum(pageNum);
        int begin=(pageNum-1)*pageSize;
        List<Book> items=bookDao.queryItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
