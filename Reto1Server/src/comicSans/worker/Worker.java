/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.worker;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import comicSans.com.Reto1Server.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageReto;
import message.Type;
import user.User;

/**
 *
 * @author Jonathan Viñan
 */
public class Worker extends Thread {

    private Socket sc;

    public Worker(Socket sc) {
        this.sc = sc;
        this.start();
    }

    public void run() {

        //MessageReto mes = getMessageOut(sc);

        String mensaje = getOutMessage(sc);
        System.out.println(mensaje);

        try {
            sc.close();
            System.out.println("Cliente desconectado!");
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        /*switch (mes.getType()) {
            case SIGN_IN:

                break;
            case SIGN_UP:

                break;
            case LOGOUT:

                break;
        }*/

    }

    /*private MessageReto getMessageOut(Socket sc) {

        ObjectInputStream in;
        ObjectOutputStream out;
        MessageReto mes = null;
        
        try {

            in = new ObjectInputStream(sc.getInputStream());
            out = new ObjectOutputStream(sc.getOutputStream());
            mes = (MessageReto) in.readObject();
            System.out.println(mes.getUser().getFullName());//Para comrpbar que el mensaje llega

            sc.close();
            System.out.println("Cliente desconectado!");

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mes;
    }
     */

    private String getOutMessage(Socket sc) {

        DataInputStream in;
        DataOutputStream out;
        String mensaje = null;

        try {
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            mensaje = in.readUTF();

        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
}
