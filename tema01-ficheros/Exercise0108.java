import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Jesús Salas
 * Contar vocales de un archivo de texto
 */
public class Exercise0108 
{
    public static void main( String[] args ) 
    { 
        
        //Data petition
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduce file name: ");

        String nombreFichero = scan.nextLine();
        scan.close();
        
        int numVocales = 0;
        
        // Primero vemos si el fichero existe 
        if (! (new File(nombreFichero)).exists() ) 
        { 
            System.out.println("No he encontrado el fichero "+ nombreFichero);
            return;
        } 
        
        // En caso de que exista, intentamos leer 
        System.out.println("Leyendo fichero..."); 
        try 
        { 
            //Es un BufferedReader que dentro lleva un FileReader
            //Intentamos leer un archivo
            BufferedReader ficheroEntrada = new BufferedReader( 
                new FileReader(new File(nombreFichero)));
            
            //Vamos leyendo líneas
            String linea=null; 
            while ((linea=ficheroEntrada.readLine()) != null) 
            { 
                 for (int i = 0; i < linea.length(); i++)
                {
                     linea = linea.toLowerCase();
                    //En este caso las sacamos por consola
                      if (linea.charAt(i) == 'a' || linea.charAt(i) == 'e' || 
                              linea.charAt(i) == 'i' || linea.charAt(i) == 'o' 
                                || linea.charAt(i) == 'u')
                    {
                        numVocales++;
                    }
                }
            }
            //Cerrar es sagrado
            ficheroEntrada.close(); 
            
            // Y mostramos resultado si todo ha ido bien
            System.out.println("Numero de vocales = "+ numVocales); 
        } 
        catch (IOException errorDeFichero) 
        { 
            System.out.println( "Ha habido problemas: " + 
                errorDeFichero.getMessage() );
        }
        
        
    } 
}
