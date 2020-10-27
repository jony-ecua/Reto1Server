package Utilidades;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilidades {

    public static String introducirCadena() {
        String cadena = "";
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);

        try {
            cadena = teclado.readLine();
        } catch (IOException er) {
            System.out.println("Error al introducir los datos");
            System.exit(0);
        }

        return cadena;
    }

    public static String introducirCadena(String mensaje) {
        String cadena = "";
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        System.out.println(mensaje);
        try {
            cadena = teclado.readLine();
        } catch (IOException er) {
            System.out.println("error al introducir datos");
            System.exit(0);
        }
        return cadena;
    }

    public static String leerString(int x) {
        String cadena = null;
        boolean ok;

        do {
            ok = true;
            cadena = introducirCadena();

            if (cadena.length() > x) {
                System.out.println("Error al introducir los datos");
                ok = false;
            }
        } while (!ok);

        return cadena;
    }

    public static double leerDouble(int x, int y) {
        double num = 0;
        boolean ok;

        do {
            try {
                ok = true;
                num = Double.parseDouble(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros");
                ok = false;
                num = x;

            }

            if (num < x || num > y) {
                System.out.println("Dato fuera de rango, introduce entre " + x + " y " + y);
                ok = false;
            }
        } while (!ok);

        return num;
    }

    public static double leerDouble() {
        double fNumero = 0;
        boolean ok;

        do {
            try {
                ok = true;
                fNumero = Double.parseDouble(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error al introducir el numero");
                ok = false;
            }
        } while (!ok);

        return fNumero;
    }

    public static long leerLong() {
        long num = 0;
        boolean ok;
        do {
            try {
                ok = true;
                num = Long.parseLong(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error al introducir el numero");
                ok = false;
            }
        } while (!ok);
        return num;
    }

    public static boolean esBoolean() {
        String respu;

        do {
            respu = introducirCadena().toLowerCase();
        } while (!respu.equals("0") && !respu.equals("1") && !respu.equals("si") && !respu.equals("no") && !respu.equals("s") && !respu.equals("n") && !respu.equals("true") && !respu.equals("false"));

        if (respu.equals("1") || respu.equals("si") || respu.equals("s") || respu.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static char leerCharArray(char caracteres[]) {
        int i;
        boolean error = false;
        String letra;
        char aux = 0;

        do {
            error = false;
            letra = introducirCadena();
            if (letra.length() != 1) {
                System.out.println("Error, introduce un caracter");
                error = true;
            } else {
                aux = letra.charAt(0);
                for (i = 0; i < caracteres.length; i++) {
                    if (Character.toUpperCase(caracteres[i]) == Character.toUpperCase(aux)) {
                        break;
                    }
                }
                if (i == caracteres.length) {
                    error = true;
                    System.out.println("Error, el caracter introducido no es valido");
                }
            }
        } while (error);
        return aux;
    }

    public static char leerChar() {
        boolean error = false;
        String letra;

        do {
            error = false;
            letra = introducirCadena();
            if (letra.length() != 1) {
                System.out.println("Error, introduce un caracter");
                error = true;
            }

        } while (error);
        return letra.charAt(0);
    }

    public static float leerFloat() {
        float fNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                fNumero = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error al introducir el numero");
                ok = false;
            }
        } while (!ok);
        return fNumero;
    }

    public static float leerFloat(String mensaje) {
        float num = 0;
        boolean ok = true;
        System.out.println(mensaje);
        do {
            ok = true;

            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                ok = false;
                System.out.println("Error al introducir un número");
            }
        } while (!ok);
        return num;
    }

    public static float leerFloat(float x, float y) {
        float fNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                fNumero = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros");
                ok = false;
                fNumero = x;
            }
            if (fNumero < x || fNumero > y) {
                System.out.println("Dato fuera de rango. Introduce entre " + x + " y " + y);
                ok = false;
            }
        } while (!ok);
        return fNumero;
    }

    public static int leerInt() {
        int iNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                iNumero = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros");
                ok = false;
            }
        } while (!ok);
        return iNumero;
    }

    public static int leerInt(int x, int y) {
        int num = 0;
        boolean ok;
        do {
            try {
                ok = true;
                num = Integer.parseInt(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros");
                ok = false;
                num = x;

            }
            if (num < x || num > y) {
                System.out.println("Dato fuera de rango, introduce entre " + x + " y " + y);
                ok = false;
            }
        } while (!ok);
        return num;
    }

    public static LocalDate introduceFecha(String message) {
        String fechaS;
        boolean hay;
        LocalDate fecha = null;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        do {
            hay = true;
            System.out.print(message);
            System.out.println(" en formato dd-MM-yyyy: ");
            fechaS = Utilidades.introducirCadena();
            try {
                fecha = LocalDate.parse(fechaS, formateador);
            } catch (DateTimeParseException p) {
                System.out.println("Error. Formato de fecha incorrecto.");
                hay = false;
            }
        } while (!hay);
        return fecha;
    }

    public static int calculoFichero(File fich) {
        int cont = 0;
        if (fich.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(fich);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }

            } catch (EOFException e1) {
                System.out.println("Has acabado de leer, tienes " + cont + " objetos");

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos");

            }
        }
        return cont;
    }
}
