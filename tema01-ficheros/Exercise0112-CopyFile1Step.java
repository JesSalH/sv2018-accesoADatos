//Moisés Encinas Picazo

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Exercise0112 {

    public static void main(String[] args) {
        
        InputStream ficheroEntrada = null;
        OutputStream ficheroSalida = null;
        
        String fichero1 = "fichero.txt";
        String fichero2 = "ficheroSalida.txt";
        
        try {
            File fichero = new File(fichero1);
            int size = (int) fichero.length();
            
            ficheroEntrada = new FileInputStream(fichero);
            ficheroSalida = new FileOutputStream(new File(fichero2));
            
            byte[] buf = new byte[size ];
            int cantidadLeida = ficheroEntrada.read(buf, 0, size);
            ficheroSalida.write(buf, 0, cantidadLeida);
            
            if (cantidadLeida < size)
              System.out.println("Not completely read");
            
            ficheroEntrada.close();
            ficheroSalida.close();
        } catch (IOException e) {
            System.out.println("Problems! " 
                + e.getMessage() );
        }
    }
}
