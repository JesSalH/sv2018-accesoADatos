//Álvaro Monllor Quesada
package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    new File("alumnos2.xml")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    new File("alumnos3.xml")));
            
            String line = bufferedReader.readLine();
            
            while(line != null) {
            	bufferedWriter.write(line.trim()
            			.replace(" duplicado=\"si\"", "")
            			.replace(" conflicto=\"si\"", ""));
                bufferedWriter.newLine();
                line = bufferedReader.readLine();               
            }
            bufferedWriter.close();
            bufferedReader.close();
        }
        catch(IOException ioe) {
            System.out.println("Error when reading file");
            return;
        }
	}

}
