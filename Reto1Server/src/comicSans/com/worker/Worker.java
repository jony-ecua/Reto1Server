/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.Worker;

import clientDaoInterface.Signable;
import comicSans.Dao.DaoFactory;
import comicSans.Server.Server;
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

        Signable dao = new DaoFactory().getDao();

        MessageReto mes = getMessageOut(sc);
        
        try {
            sc.close();
            //System.out.println("Cliente desconectado!");
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (mes.getType()) {
            
            case SIGN_UP:
                dao.signUp(mes.getUser());
                break;
            case SIGN_IN:
                dao.signIn(mes.getUser());
                break;
        }

    }

    private MessageReto getMessageOut(Socket sc) {
        ObjectInputStream in;
        ObjectOutputStream out;
        MessageReto mes = null;

        try {

            out = new ObjectOutputStream(sc.getOutputStream());
            in = new ObjectInputStream(sc.getInputStream());
            mes = (MessageReto) in.readObject();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mes;
    }
}
