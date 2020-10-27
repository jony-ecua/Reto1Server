/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicSans.com.Reto1Server;

/**
 *
 * @author jonyv
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int opc;

        do {
            opc = menu();

            switch (opc) {
                case 1:
                    
                    break;

                case 2:
                    
                    break;
            }
        } while (opc != 9);
    }

    private static int menu() {
        int opc;

        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");

        opc = Utilidades.leerInt();
        
        return opc;
    }
}
