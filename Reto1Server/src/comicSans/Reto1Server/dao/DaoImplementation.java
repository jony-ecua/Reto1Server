/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.Reto1Server.dao;

import comicSans.Reto1Server.*;
import otros.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristina Milea
 */
public class DaoImplementation {

    private Connection con;
    private PreparedStatement stm;

    private final String createUser = "insert into user (login, email, fullName, status, privilege, password, lastAccess, lastPasswordChange) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String selectUser = "select * from user where login= ?";
    
    //--------------------------BORRAR------------------------------------
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASS;

    //--------------------------BORRAR------------------------------------
    public DaoImplementation() {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/comicsansdb";
        DB_USER = "root";
        DB_PASS = "abcd*1234";
    }

    //--------------------------BORRAR------------------------------------
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

    //--------------------------BORRAR------------------------------------
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

    public void signUp(User u) {
        try {          
            //---------------------DATE----------------------------
            this.conectar();

            stm = con.prepareStatement(createUser);

            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getFullName());
            stm.setObject(4, u.getStatus());
            stm.setObject(5, u.getPrivilege());
            stm.setString(6, u.getPassword());
            //stm.setTimestamp(7, u.getLastAccess());
            //stm.setTimestamp(8, u.getLastPasswordChange());

            if (stm.executeUpdate() == 0) {
                System.out.println("No se ha podido crear el usuario");
            } else {
                System.out.println("Usuario creado con exito");
            }
            
            
        } catch (SQLException sqle) {
            System.out.println("Ha ocurrido un error en SQL");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
        } finally {
            this.desconectar();
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
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, e);
        }
         return exists;
    }
}
