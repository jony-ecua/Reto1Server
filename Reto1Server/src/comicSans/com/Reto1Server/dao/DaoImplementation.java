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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that defines the methods of the Signable Interface.
 * Is used to execute query commands that will acces de database.
 * @author Nerea Aranguren and Cristina Milea
 */
public class DaoImplementation implements Dao{
    
    
    private final String updateLogin = "UPDATE user SET lastAccess = ? WHERE login= ? and password= ?";
    private final String selectUser = "SELECT * FROM user WHERE login= ?";
    
    
    
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
    public void signUpUser(User user) { throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void signInUser(User u) {
        try {            
            this.conectar();
            
            boolean exists = this.checkUserExists(u);
            if (exists){
                Timestamp timeNow = new Timestamp(System.currentTimeMillis());

                stm = con.prepareStatement(updateLogin);
                stm.setTimestamp(1, timeNow);
                stm.setString(2, u.getLogin());
                stm.setString(3, u.getPassword());
                stm.executeUpdate();
            } else {
                System.out.println("NO existe");
            }
            this.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public boolean checkUserExists(User u) {
         boolean exists = false;
         
         try {
            ResultSet rs = null;
            
            this.conectar();
            
            stm = con.prepareStatement(selectUser);
            stm.setString(1, u.getLogin());
            rs = stm.executeQuery();
            
            if (rs.next()) {
                exists = true;
            } else {
                exists = false;
            }
            
            rs.close();
            
            this.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            
        }
         return exists;
    }
    
}
