/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server.dao;

/**
 * Interfaz that declares the methods to use in DaoImplementation.
 * @author Nerea Aranguren
 */
public interface Dao {
    public void signUpUser(User user);
    public void signInUser(User user);
    public boolean checkUserExists(User user);
}
