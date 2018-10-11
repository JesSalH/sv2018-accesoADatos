//Álvaro Monllor Quesada

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Exercise0116 {
    public static void main(String args[]) {
        System.out.println("Leyendo cabecera");
        int bufferSize = 0;
        
        try {
            File file = new File("mp3.mp3");
            InputStream ficheroEntrada = new FileInputStream(file);
            
            bufferSize = (int)file.length();
            byte[] buf = new byte[bufferSize];
            ficheroEntrada.read(buf, 0, bufferSize);
            ficheroEntrada.close();
            
            int inicioCabecera = bufferSize - 128;
            
            String tag = "" + (char) buf[inicioCabecera]
                + (char) buf[inicioCabecera+1]
                + (char) buf[inicioCabecera+2];
            
            if (! tag.equals("TAG")) {
                System.out.println("It is not a MP3 file");
            }
            else {
                System.out.print("Títle: ");
                for(int index = inicioCabecera+3 ; index<inicioCabecera + 33 ;
                        index++) {
                    if((char)buf[index] != '\0')
                        System.out.print((char)buf[index]);
                }
                System.out.println();
                
                System.out.print("Artist: ");
                for(int index = inicioCabecera + 33 ; index<inicioCabecera + 63 ; 
                        index++) {
                    if((char)buf[index] != '\0')
                        System.out.print((char)buf[index]);
                }
                System.out.println();
                
                System.out.print("Album: ");
                for(int index = inicioCabecera + 63 ; index<inicioCabecera + 93 ; 
                        index++) {
                    if((char)buf[index] != '\0')
                        System.out.print((char)buf[index]);
                }
                System.out.println();
                
                System.out.print("Year: ");
                for(int index = inicioCabecera + 93; index<inicioCabecera + 97 ; 
                        index++) {
                    if((char)buf[index] != '\0')
                        System.out.print((char)buf[index]);
                }
                System.out.println();
                
                System.out.print("Comment: ");
                for(int index = inicioCabecera + 97 ; index<inicioCabecera + 127 ; 
                        index++) {
                    if((char)buf[index] != '\0')
                        System.out.print((char)buf[index]);
                }
                System.out.println();
                
                System.out.print("Genre: ");
                System.out.println(buf[inicioCabecera + 127]);
            }
            
        } catch (Exception errorDeFichero) {
            System.out.println("Ha habido problemas: " 
                    + errorDeFichero.getMessage());
        }
    }
}
