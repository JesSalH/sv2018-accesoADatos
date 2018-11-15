package Examen2016;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Javier Saorín Vidal

public class Exercise0132 {
    public static void main(String[] args) {

        int data;
        String inputFile = "vehiculos.dat", 
                outputFile = "vahiculos.txt";
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
                if((data >= 48 && data <= 57) || (data >= 65 && data <= 90) ||
                        (data >= 97 && data <= 122) || data == 32) {
                    outputStream.write( (byte) data);
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
