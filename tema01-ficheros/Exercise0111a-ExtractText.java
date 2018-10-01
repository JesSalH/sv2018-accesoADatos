// Extraer texto de un fichero binario
// JORGE CALABUIG BARTUAL

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Exercise0111a {

    public static void main(String[] args) {
        try {
            FileInputStream ficheroEntrada = new FileInputStream(
                    new File("fichero.bin"));
            try {
                PrintWriter ficheroSalida = new PrintWriter("salida.txt");
                int dato = ficheroEntrada.read();
                
                while (dato != -1) {
                    if (dato >= 32 && dato <= 127) // Codigo ASCII de "a"
                        ficheroSalida.print((char) dato);
                    
                    dato = ficheroEntrada.read();
                }
                
                ficheroSalida.close();
            } catch (IOException e) {
                System.out.println("Error en el fichero de salida" 
                        + e.getMessage());
            }
            ficheroEntrada.close();
        }
        catch (Exception errorDeFichero) {
            System.out.println("Ha habido problemas: " 
            + errorDeFichero.getMessage() );
        }
    }
}
