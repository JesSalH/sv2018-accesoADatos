import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Negative of a PBM image
// Javier Saorín Vidal.

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Exercise0115 {
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);    
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();        
       
        try {
            InputStream input = new FileInputStream(new File(fileName));
            OutputStream output = new FileOutputStream(fileName + ".neg.pgm");
            
            int length = input.available();
            
            byte[] buf = new byte[length];
            
            int cantidadLeida = 0;
            if ((cantidadLeida = input.read(buf, 0, length)) == length) {
                
                int jumps = 0;
                int headerCount = 0;
                
                while(jumps < 3) {
                    if(buf[headerCount] == 10)
                        jumps++;
                    headerCount++;
                }
                
                for (int b = 0; b < headerCount; b++) {
                    output.write(buf[b]);
                }
                
                for (int i = headerCount; i < buf.length; i++) {
                    output.write(255 - buf[i]);
                }
            }
            
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println("Input/Output ERROR: " + e.getMessage());
        }
    }
}
