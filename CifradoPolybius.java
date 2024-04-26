package cifradopolybius;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class CifradoPolybius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opc;
        String opcion;
        System.out.println("Introduce el mensaje:");
        String msg = scanner.nextLine();
        String mensaje = msg.toUpperCase();
        System.out.println("Mensaje: "+mensaje);
        System.out.println("Introduce la clave 1:");
        int clave1 = scanner.nextInt();
        System.out.println("Introduce la clave 2:");
        int clave2 = scanner.nextInt();
        
        System.out.println("¿Que desea realizar?\n1. Cifrar\n2. Descifrar");
        opc = scanner.nextInt();
        CifradoPolybius polybius = new CifradoPolybius(clave1, clave2);
        switch (opc) {
            case 1:
                System.out.println("\tMatriz Polybius:");
                polybius.Tabla();
                scanner.nextLine();
                String msgCifrado = polybius.cifrar(mensaje);
                System.out.println("Cifrado: " + msgCifrado);
                break;
            case 2:
                System.out.println("\tMatriz Polybius:");
                polybius.Tabla();
                scanner.nextLine();
                String msgDescifrado = polybius.descifrar(mensaje,clave1,clave2);
                System.out.println("Descifrado: " + msgDescifrado);
                break;
            default:
                break;
            }
    }
    
    private char[][] tabla;
    private int dimensionX;
    private int dimensionY;

    public CifradoPolybius(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        Matriz();
    }

    private void Matriz() {
        tabla = new char[dimensionX][dimensionY];
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789- ";

        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                if (i * dimensionY + j < alfabeto.length()) {
                    tabla[i][j] = alfabeto.charAt(i * dimensionY + j);
                } else {
                    tabla[i][j] = '*';
                }
            }
        }
    }

    public void Tabla() {
        System.out.print("   ");
        for (int i = 0; i < dimensionY; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < dimensionX; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < dimensionY; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String cifrar(String frase) {
        StringBuilder texto = new StringBuilder();
        for (char c : frase.toCharArray()/*toUpperCase().toCharArray()*/) {
            if (c == 'J' || c == 'j') {
                //c = 'I';
            }
            for (int i = 0; i < dimensionX; i++) {
                for (int j = 0; j < dimensionY; j++) {
                    if (tabla[i][j] == c) {
                        texto.append(i + 1).append(j + 1);
                    }
                }
            }
        }
        return texto.toString();
    }

    public String descifrar(String frase,int dimensionX , int dimensionY) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < frase.length(); i += 2) {
            int x = Character.getNumericValue(frase.charAt(i)) - 1;
            int y = Character.getNumericValue(frase.charAt(i + 1)) - 1;
            if((x>=0 && x<dimensionX)&&(y>=0 && y<dimensionY)){
                texto.append(tabla[x][y]);
            } else{
                texto.append("*");
            }
        }
        return texto.toString();
    }
}
