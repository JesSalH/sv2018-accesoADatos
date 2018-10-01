// Crea un programa que lea de línea de comandos el nombre de dos ficheros 
// de entrada y uno de salida. El fichero de salida tendrá el contenido de 
// todos los ficheros de entrada, ordenado alfabéticamente. 

//Javier Saorín Vidal.

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exercise1019 {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Input error: Enter the name of 3 files.");
        }
        else {
            ArrayList<String> lines = new ArrayList<>();
            
            try {
                BufferedReader input1 = new BufferedReader( 
                        new FileReader(new File(args[0])));
                
                String line = "";
                do {
                    line = input1.readLine();
                    if (line != null) {
                        lines.add(line);
                    }
                } while (line != null);
                
                input1.close();
                
                BufferedReader input2 = new BufferedReader( 
                        new FileReader(new File(args[1])));
                
                line = "";
                do {
                    line = input2.readLine();
                    if (line != null) {
                        lines.add(line);
                    }
                } while (line != null);
                
                input2.close(); 
            
            } catch (IOException e) {
                System.err.println("Input/Output error: " + e.getMessage());
            }    
                
            Collections.sort(lines);
                
            try {
                PrintWriter output = new PrintWriter(args[2]);
                
                for (String l : lines) {
                    output.println(l);
                }
                output.close();
                
            } catch (IOException e) {
                System.err.println("Input/Output error: " + e.getMessage());
            }
        }
    }
}
