package com.lxl.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();

    static {
        try {
            Properties properties=new Properties();
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn=threadLocal.get();
        if(conn==null){
            try {
                conn=dataSource.getConnection();
                threadLocal.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     *关闭连接，放回连接池
     * @param conn
     *//*
    public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
    public static void commitAndClose(){
        Connection conn = threadLocal.get();
        if(conn!=null){
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            threadLocal.remove(); //一定要移除 否则可能内存泄漏
        }
    }

    public static void rollbackAndClose(){
        Connection conn = threadLocal.get();
        if(conn!=null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            threadLocal.remove(); //一定要移除 否则可能内存泄漏
        }
    }
}
