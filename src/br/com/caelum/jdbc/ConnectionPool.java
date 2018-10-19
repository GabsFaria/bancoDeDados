package br.com.caelum.jdbc;

import org.hsqldb.jdbc.JDBCPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private DataSource dataSource;

    ConnectionPool(){
        JDBCPool pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:hsql://localhost/loja-virtual");
        pool.setUser("SA");
        pool.setPassword("");
        this.dataSource = pool;
    }

     Connection getConnection() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
         Connection connection = dataSource.getConnection();
         return  connection;
    }
}
