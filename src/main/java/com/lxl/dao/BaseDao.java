package com.lxl.dao;

import com.lxl.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner qr=new QueryRunner();
    public int update(String sql,Object... params){
        Connection conn= JdbcUtils.getConnection();
        try {
            return qr.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public<T> T queryOne(Class<T> type, String sql, Object... params){
        Connection conn= JdbcUtils.getConnection();
        try {
            return qr.query(conn,sql,new BeanHandler<T>(type),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public<T> List<T> query(Class<T> type, String sql, Object... params){
        Connection conn= JdbcUtils.getConnection();
        try {
            return qr.query(conn,sql,new BeanListHandler<T>(type),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Object querySingleVal(String sql,Object... params){
        Connection conn=JdbcUtils.getConnection();
        try {
            return qr.query(conn,sql,new ScalarHandler(),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
