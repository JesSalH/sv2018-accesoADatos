//Almudena López Sánchez


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise0106 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        BufferedWriter outputFile = null;
        try
        {
            outputFile = new BufferedWriter(
                    new BufferedWriter(
                    new FileWriter("trapezium.txt")));
            System.out.println("Width: ");
            int width = sc.nextInt();
            System.out.println("Height: ");
            int height = sc.nextInt();
            int spaces = height-1;
            int ast = width -2*spaces;
            
            for(int row = 0; row < height; row++)
            {
                for(int sp = 0; sp < spaces; sp++)
                {
                    outputFile.write(" ");
                }
                for(int col = 0; col < ast; col++)
                {
                    outputFile.write("*");
                }
                outputFile.newLine();
                
                spaces--;
                ast +=2;
            }
            outputFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error files.");
        }
    }
}
