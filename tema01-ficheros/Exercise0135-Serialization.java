/*
 * Néstor Rosario Escolano
 * 14 / 11 / 2018
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Compactador implements Serializable {
    
    public static void Compactar() {
        
        String lectura;
        String fileInput = "alumnos2.xml";
        String fileOutput = "alumnos3.xml";
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        
        try {
            bufferedReader = new BufferedReader(new FileReader(
                    new File(fileInput)));
            
            bufferedWriter = new BufferedWriter(new FileWriter(
                    new File(fileOutput)));
            
            while((lectura = bufferedReader.readLine()) != null) {
                
                lectura = lectura.trim()
                        .replace(" duplicado=\"si\"", "")
                        .replace(" conflicto=\"si\"", "");
                
                bufferedWriter.write(lectura);
                bufferedWriter.newLine();
                
            }
            
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}

public class Exercise0135 {
    public static void main(String[] args) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(
                    new FileOutputStream(new File("compactador.dat")));
            outputStream.writeObject(new Compactador());
            outputStream.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
