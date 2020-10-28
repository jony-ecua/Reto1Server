/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.Pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author xabig
 */
public class ConnectionPool {
    private ResourceBundle rb = ResourceBundle.getBundle("comicSans.Pool.DataPool");
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;
    
    private ConnectionPool(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(rb.getString("DRIVER"));
        basicDataSource.setUsername(rb.getString("USER"));
        basicDataSource.setPassword(rb.getString("PASS"));
        basicDataSource.setUrl(rb.getString("URL"));
        
        
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
