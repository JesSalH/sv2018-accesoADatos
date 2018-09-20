// Log
// Moisex Encinas Picazo

import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exercise0105 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Sentence? ");
        String sentence = sc.nextLine();
        
        PrintWriter printWriter = null;
        
        try {
            printWriter = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("log.txt",true)));
            Date date = new Date();
            DateFormat hourdateFormat = 
                    new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            printWriter.println(hourdateFormat.format(date)+" "+sentence);
        } catch (IOException e){ 
            e.printStackTrace();
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
