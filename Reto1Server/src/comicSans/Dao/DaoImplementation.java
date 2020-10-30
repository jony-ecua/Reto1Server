/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.Dao;

import clientDaoInterface.Signable;
import comicSans.Pool.ConnectionPool;
import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.User;

/**
 *
 * @author 2dam
 */
public class DaoImplementation implements Signable {

    private Connection con;
    private PreparedStatement stm;

    private final String createUser = "insert into user (login, email, fullName, status, privilege, password, lastAccess, lastPasswordChange) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String updateLogin = "UPDATE user SET lastAccess = ? WHERE login= ? and password= ?";
    private final String selectUser = "SELECT * FROM user WHERE login= ?";
    private final String selectPassw = "SELECT * FROM user WHERE login= ? and password= ?";

    public void signUp(User user) {
        try {
            //try {
            con = ConnectionPool.getInstance().getConnection();

            stm = con.prepareStatement("insert into user (login, email, fullName, status, privilege, password, lastAccess, lastPasswordChange) values (?, ?, ?, ?, ?, ?, ?, ?)");

            stm.setString(1, user.getLogin());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getFullName());
            //stm.setUserStatus(4, u.getStatus());
            //stm.setUserPrivilege(5, u.getPrivilege());
            stm.setString(6, user.getPassword());
            //stm.setTimestamp(7, u.getLastAccess());
            //stm.setTimestamp(8, u.getLastPasswordChange());

            //--------------------------CAMBIAR------------------------------------
            if (stm.executeUpdate() == 0) {
                System.out.println("ERROR");
            } else {
                System.out.println("Usuario creado con exito");
            }

            /*
            } catch (SQLException sqle) {
            //System.out.println("Ha ocurrido un error en SQL");
            } catch (Exception e) {
            System.out.println("Ha ocurrido un error");
            } finally {
            this.desconectar();
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void signIn(User user) throws UserNotFoundException, PasswordIncorrectException {
        try {
            con = ConnectionPool.getInstance().getConnection();

            boolean existsUser = this.checkUserExists(user, con);
            if (existsUser) {
                boolean contCorr = this.checkPassCorrect(user, con);
                if (contCorr) {
                    Timestamp timeNow = new Timestamp(System.currentTimeMillis());

                    stm = con.prepareStatement(updateLogin);
                    stm.setTimestamp(1, timeNow);
                    stm.setString(2, user.getLogin());
                    stm.setString(3, user.getPassword());
                    stm.executeUpdate();

                    System.out.println("Usuario existe!");
                } else {
                    System.out.println("Contraseña incorrecta!");
                }
            } else {
                System.out.println("No existe usuario!");
            }
            ConnectionPool.getInstance().closeConnection(con);

            //} catch (SQLException ex) {
            //  Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
            //} //catch (UserNotFoundException ex) {
            //Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
            //}
        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserExists(User user, Connection con) throws UserNotFoundException {
        boolean exists = false;

        try {
            ResultSet rs = null;

            stm = con.prepareStatement(selectUser);
            stm.setString(1, user.getLogin());
            rs = stm.executeQuery();

            if (rs.next()) {
                exists = true;
            } else {
                throw new UserNotFoundException();
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    private boolean checkPassCorrect(User user, Connection con) throws PasswordIncorrectException{
        boolean passExist = false;

        try {
            ResultSet rs = null;

            stm = con.prepareStatement(selectPassw);
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getPassword());
            rs = stm.executeQuery();

            if (rs.next()) {
                passExist = true;
            } else {
                throw new PasswordIncorrectException();
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return passExist;
    }
}
