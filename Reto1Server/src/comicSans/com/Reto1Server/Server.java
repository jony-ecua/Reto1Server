/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server;

import comicSans.com.Reto1Server.Pool.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author jonyv
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            Connection c = ConnectionPool.getInstance().getConnection();
            if(c != null){
                System.out.println("Conectado!");
                ConnectionPool.getInstance().closeConnection(c);
            } else {
                System.out.println("No conectado!");
            }
                            
        } catch(SQLException ex){
            //Logger log = log.log(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
