/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class DaoImplementation implements Dao{
    
    private Connection con;
    private PreparedStatement stm;
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASS;
    public DaoImplementation() {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/comicsansdb";
        DB_USER = "root";
        DB_PASS = "abcd*1234";
    }
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                //Establecer la conexion con la BD
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            } catch (SQLException ex) {
                System.err.print("Error: ConectorBD.conectar()");
                System.err.print("Al intentar la conexion con la BD");
                System.err.print(ex.getMessage());
            }
        } catch (ClassNotFoundException cex) {
            System.err.print("Error: ConectorBD.conectar()");
            System.err.print("No se encontro el Driver de la Conexion con MySQL");
            System.err.print(cex.getMessage());
        }
    }
    public void desconectar() {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException sqle) {
            System.err.print("Error: ConectorBD.desconectar");
            System.err.print(sqle.getMessage());
        }
    }
    
    
    
    
   
    
    
    

    @Override
    public void signUpUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signInUser(User u) {
        try {
            ResultSet rs = null;
            
            this.conectar();
            
            boolean exists = false;
            
            exists=this.checkUserExists(u);
            
            if (exists){
                
            }
            
           timeStamp timeNow = new TimeStamp();

            String select = "UPDATE user SET lastAccess ='" +timeNow+ "' WHERE login='" +u.getLogin()+ "'";
            stm = con.prepareStatement(select);
            rs = stm.executeQuery(select);
            
            while (rs.next()) {
                c.setId(rs.getLong("id"));
                c.setCity(rs.getString("city"));
                c.setEmail(rs.getString("email"));
                c.setFirstName(rs.getString("firstName"));
                c.setLastName(rs.getString("lastName"));
                c.setMiddleInitial(rs.getString("middleInitial"));
                c.setPhone(rs.getLong("phone"));
                c.setState(rs.getString("state"));
                c.setStreet(rs.getString("street"));
                c.setZip(rs.getInt("zip"));
            }
            
            rs.close();
            
            this.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    // private final String checkUserExist = " select * from user where login='";
    
    @Override
    public boolean checkUserExists(User u) {
         boolean exists = false;
         try {
            ResultSet rs = null;
            
            this.conectar();
            
            String select = " select * from user where login='" +u.getLogin()+ "'";
            stm = con.prepareStatement(select);
            rs = stm.executeQuery(select);
            
            /*
            stm = con.prepareStatement(checkUserExist + user.getLogin() +"'");
            rs = stm.executeQuery(checkUserExist);*/
            
            if (rs.next()) {
                exists = true;
            } else {
                exists = false;
            }
            
            rs.close();
            
            this.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
         return exists;
    }
    
}
