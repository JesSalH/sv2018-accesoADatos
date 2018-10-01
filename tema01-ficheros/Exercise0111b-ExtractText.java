/*
 * Extraer texto de un fichero (binario)
 * V2: Salida binaria
 * 
 * Néstor Rosario Escolano
 * 27 / 09 / 2018
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise0111b {

    public static void main(String[] args) {

        int data;
        String inputFile = "file.txt", outputFile = "output.txt";
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        
        if(!new File(inputFile).exists()) {
            System.out.println("File " + inputFile + " not found");
            return;
        }
            
        
        try {
            inputStream = new FileInputStream(new File(inputFile));
            outputStream = new FileOutputStream(new File(outputFile));
            
            data = inputStream.read();
            
            while(data != -1) {
                if(data >= 32 && data <= 127) {
                    outputStream.write(data);
                }          
                data = inputStream.read();
            }
            
            inputStream.close();
            outputStream.close();
        }
        catch(IOException ioe) {
            System.out.println("Error in file");
        }
    }
}
