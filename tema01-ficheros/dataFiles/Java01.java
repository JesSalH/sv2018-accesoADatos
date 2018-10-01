// Primer ejemplo de fuente en Java para convertir a Python

import java.util.Scanner;

public class Java01 {

    public static void main(String [] args)  {

        Scanner entrada = new Scanner(System.in);
        System.out.print("Dime un número: ");
        int n1 = entrada.nextInt();
        System.out.print("Dime otro número: ");
        int n2 = entrada.nextInt();

        System.out.print("Su suma es ");
        System.out.println(  n1+n2 );
    }
}
