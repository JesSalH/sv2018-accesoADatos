import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//Adri�n Fern�ndez Arnal
public class Main {
    
    public static void main(String[] args) {
        // Pedimos el a�o
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el a�o: ");
        String anyo = sc.nextLine();
        // Abrimos (y descargamos) la p�gina que nos da la informaci�n,
        // pas�ndole los par�metros por GET
        DownloadWebPage("http://localhost/servicioweb/"
                + "servicioweb2.php?anyo="+anyo);
    }
    
    public static void DownloadWebPage(String webpage) 
    { 
        try { 
            URL url = new URL(webpage); 
            BufferedReader lector =  
              new BufferedReader(new InputStreamReader(url.openStream())); 
              
            String line = lector.readLine(); 
            String[] data;
            while (line != null) {
                // Cuidado "split" en Java usa una expresi�n regular
                data = line.split("\\|");
                // En cuanto ya hemos recibido y partido los datos
                // podemos mostrarlos o procesarlos:
                System.out.println("A�o: " + data[1]);
                System.out.println("Nombre: " + data[0]);
                line = lector.readLine();
            }  
            lector.close(); 
        } 
        catch (MalformedURLException mue) { 
            System.out.println("URL mal formada"); 
        } 
        catch (IOException ie) { 
            System.out.println("Problema de Entrada/salida"); 
        } 
    }
    
}
