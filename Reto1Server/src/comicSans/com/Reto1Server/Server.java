/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server;

import comicSans.com.Reto1Server.Pool.ConnectionPool;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageReto;

/**
 *
 * @author jonyv
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServerSocket servidor;
        Socket sc;
        final int PUERTO = 1300;
        
        //ObjectInputStream in;
        //ObjectOutputStream out;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor conectado!");

            while (true) {
                sc = servidor.accept();

                System.out.println("Cliente conectado!");

                //in = new ObjectInputStream(sc.getInputStream());
                //out = new ObjectOutputStream(sc.getOutputStream());
                //MessageReto mes = (MessageReto) in.readObject();
                //System.out.println(mes.getUser().getFullName());//Para comrpbar que el mensaje llega

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                
                String mensaje = in.readUTF();
                System.out.println(mensaje);
                
                sc.close();
                System.out.println("Cliente desconectado!");

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (ClassNotFoundException ex) {
        //    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection c = ConnectionPool.getInstance().getConnection();
            if (c != null) {
                System.out.println("Conectado!");
                ConnectionPool.getInstance().closeConnection(c);
            } else {
                System.out.println("No conectado!");
            }

        } catch (SQLException ex) {
            //Logger log = log.log(ex.getMessage());
            System.out.println(ex.getMessage());
        }

    }

}
