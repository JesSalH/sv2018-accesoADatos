//Adrián Fernández Arnal
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


public class Exercise0113 {

    public static void main(String[] args) {
        String fileEntrada;     
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero a copiar: ");
        fileEntrada = sc.nextLine();
        System.out.println("Introduce el tamaño (bytes) en el que partir "
                + "los ficheros: ");
        final int BUFFER_SIZE = sc.nextInt();
        
        try {
            InputStream ficheroEntrada = new FileInputStream(
                    new File(fileEntrada));
            OutputStream ficheroSalida;
            
            
            byte[] buf = new byte[BUFFER_SIZE];
            int cantidadLeida,cont = 0;
            while((cantidadLeida = ficheroEntrada.read(buf,0,BUFFER_SIZE))>0){
                ficheroSalida = new FileOutputStream(
                        new File("salida-"+cont+".out"));
                ficheroSalida.write(buf, 0, cantidadLeida);
                ficheroSalida.close();  
                cont++;
            }
            sc.close();
            ficheroEntrada.close();
        } catch (IOException e1) {
            System.out.println("Error with the file"+ e1.getMessage() );
            
        }
    }
}
