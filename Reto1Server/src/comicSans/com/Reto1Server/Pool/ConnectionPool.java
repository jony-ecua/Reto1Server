/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server.Pool;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author xabig
 */
public class ConnectionPool {
    
    private final String DB = "comicsansdb";
    private final String URL = "jdbc:mysql://localhost:3306/comicsansdb";
    private final String USER = "root";
    private final String PASS = "abcd*1234";
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;
    
    private ConnectionPool(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        
    }
    
    public static ConnectionPool getInstance(){
        if(dataSource==null){
            dataSource = new ConnectionPool();
        }
        return dataSource;
    }
    
    public Connection getConnection() throws SQLException{
        return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
}
