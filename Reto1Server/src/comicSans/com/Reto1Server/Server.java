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
        boolean existe = false;
        
        User u = new User();
        u.setLogin("knuspo");
        
       existe = d.checkUserExists(u);
        
        if(existe){
            System.out.println("El usuario existe");
        } else {
            System.out.println("NO existe");
        }
        
    }
    
    public static void prueba(){
         
    }
    
}
