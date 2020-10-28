/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server;

import comicSans.com.Reto1Server.dao.*;

/**
 *
 * @author jonyv
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        DaoImplementation d = new DaoImplementation();
        
        User us = new User();
        us.setLogin("carlitos");
        us.setPassword("carlos");
        
       //d.signInUser(us);
       boolean existe = d.checkUserExists(us);
       if(existe){
           System.out.println("existe");
       } else {
           System.out.println("no existe");
       }
    }
    
    
}
