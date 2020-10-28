/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.Reto1Server;

import comicSans.Reto1Server.dao.DaoImplementation;
import otros.User;

/**
 *
 * @author jonyv
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        signUp();
    }

    private static void signUp() {
        boolean exists=false;
        
        User u = new User();
        DaoImplementation dao = new DaoImplementation();

        exists = dao.checkUserExists(u);

        if (exists) {
            System.out.println("El usuario ya existe");
        } else {
            u.setLogin("aaaa");
            u.setEmail("aaa@gmail.com");
            u.setFullName("Aaaa aaa");
            u.setPassword("aaaaaaaaaaaaa");

            dao.signUp(u);
        }
    }
}
