/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Server;

import comicSans.Pool.ConnectionPool;
import comicSans.Worker.Worker;
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
 * @author Xabier
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServerSocket servidor;
        Socket sc;
        final int PUERTO = 1300;

        ArrayList <Worker> work = new ArrayList<>();

       try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor conectado!");
            for (int i= 1; i <= 5; i++) {
                sc = servidor.accept();
                System.out.println("Cliente " + i + " conectado!");

                Worker aux = new Worker(sc);
                work.add(aux);

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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
