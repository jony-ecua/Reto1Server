/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.worker;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import comicSans.com.Reto1Library.MessageReto;
import comicSans.com.Reto1Library.Type;
import comicSans.com.Reto1Library.User;

/**
 *
 * @author Jonathan Viñan
 */
public class Worker  extends Thread{
    MessageReto mes = new MessageReto();

    public Worker(MessageReto aux) {
        this.start();
        this.mes=aux;
    }
    
    
    
    public void run(){
        
     switch (mes.type) {
    case SIGN_IN:
        
         break;
    case SIGN_UP: 
        
        break;
    case LOGOUT: 
        
        
        break;
}
     
    }
    
}
