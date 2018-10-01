// Gonzalo Martinez
// Exercise 0110

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Exercise0110 {
    public static void main(String[] argvs) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Name of the file: ");
        String fileName = sc.nextLine();
        if(!new File(fileName).exists()) {
            System.out.println("File doesn't exist!");
        }else {
            try {
                BufferedReader reader = new BufferedReader(
                        new FileReader(new File(fileName)));
                
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(new File(fileName.substring(0, 
                                fileName.lastIndexOf('.')) +".py")));
                
                String line = reader.readLine();
                String result;
                while(line != null) {
                    line = line.trim();
                    if(line.startsWith("//")){
                        writer.write("#" + line.substring(2));
                        writer.newLine();
                    }
                    else if(line.startsWith("System.out.println")){
                        writer.write("print");
                        for(int i = line.indexOf('('); 
                                i < line.lastIndexOf(')'); i++) {
                            writer.write(line.charAt(i));
                        }
                        writer.write(")");
                        writer.newLine();
                    }
                    else if(line.startsWith("System.out.print")){
                        
                        writer.write("print");
                        for(int i = line.indexOf('('); 
                                i < line.lastIndexOf(')'); i++) {
                            writer.write(line.charAt(i));
                        }
                        writer.write(", end=\"\")");
                        writer.newLine();
                    }
                    else if(line.startsWith("int") && 
                            line.contains(".nextInt()")) {
                        String[] parts = line.split(" ");
                        writer.write( parts[1] + " = int(input())");
                        writer.newLine();
                    }
                    line = reader.readLine();
                }
                writer.close();
                reader.close();
            }catch(IOException e) {
                System.out.println("Error IO");
            }
        }
        System.out.println("Conversion finished!");
    }
}
