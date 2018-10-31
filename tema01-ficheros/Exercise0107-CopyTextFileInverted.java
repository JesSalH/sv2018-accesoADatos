/*
 * Inverting a text file
 * 
 * Néstor Rosario Escolano
 * 19 / 09 / 2018
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise0107 {

    public static void main(String[] args) {
        
        String inputFile, outputFile, line;
        ArrayList<String> lines = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        System.out.print("Input file: ");
        inputFile = scn.nextLine();
        
        if(!(new File(inputFile)).exists()) {
            System.out.println("File " + inputFile + " not found");
            return;
        }
        
        System.out.print("Output file: ");
        outputFile = scn.nextLine();
        
        try {
            bufferedReader = new BufferedReader(new FileReader(
                    new File(inputFile)));
            
            line = bufferedReader.readLine();
            
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();               
            }
            
            bufferedReader.close();
        }
        catch(IOException ioe) {
            System.out.println("Error when reading file");
            return;
        }
        
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(
                    new File(outputFile)));
            
            for(int i=lines.size()-1; i>=0; i--) {
                bufferedWriter.write(lines.get(i));
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close(); 
        }
        catch(IOException ioe) {
            System.out.println("Error when writing in file");
        }
        

    }

}
