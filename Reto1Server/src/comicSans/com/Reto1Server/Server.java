/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server;

import comicSans.com.Reto1Server.dao.DaoImplementation;

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
        User u = new User();
        u.setLogin("aaaa");
        u.setEmail("aaa@gmail.com");
        u.setFullName("Aaaa aaa");
        u.setPassword("aaaaaaaaaaaaa");

        DaoImplementation dao = new DaoImplementation();
        dao.signUp(u);
    }
}
