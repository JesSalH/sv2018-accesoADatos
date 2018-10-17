import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Serializar y deserializar
// Adrián Pigem Recobeni

class Persona implements Serializable{
    protected String nombre;
    protected String email;
    protected Date fechaNac;
    public SimpleDateFormat ft;
    
    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    
    public void setDate(String fecha) {
        ft = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if(fecha.length() == 0)
            {
                fecha = "01-01-1970";
                fechaNac = ft.parse(fecha);
            }
            else
                fechaNac = ft.parse(fecha);
        }
        catch(ParseException e) {
            System.err.println("Unparseable using: " + ft);
        }
    }
    
    public Date getDate()
    {
        return fechaNac;
    }
    
}
 // ------------------------------------------------------------
 public class Exercise0118 {
     public static void main(String[] args) 
            throws FileNotFoundException, IOException {

        // ---- Serializar ----
        
        System.out.println("Creando fichero...");
        File fichero = new File("personas.dat");
        FileOutputStream ficheroSalida = new FileOutputStream(fichero);
        ObjectOutputStream ficheroObjetos = 
                new ObjectOutputStream(ficheroSalida);
        
        System.out.println("Serializando un dato...");
        Persona p = new Persona("raul", "raul@gmail.com");
        p.setDate("05-04-2018");
        ficheroObjetos.writeObject(p);
        
        System.out.println("Serializando otro dato...");
        p = new Persona("raul2", "raul2@gmail.com");
        p.setDate("30-12-2018");
        ficheroObjetos.writeObject(p);
        
        ficheroObjetos.close();
        
        // ---- Deserializar ----
        
        System.out.println("Deserializando dos datos...");
        FileInputStream ficheroSalida2= new FileInputStream(fichero);
        ObjectInputStream ficheroObjetos2 = 
            new ObjectInputStream(ficheroSalida2);
        for (int i = 0; i < 2; i++)
        {
            try {
                Persona p2 = (Persona) ficheroObjetos2.readObject();
                System.out.println(p2.nombre);
                System.out.println(p2.email);
                System.out.println(p2.fechaNac);        
            }
            catch(ClassNotFoundException e) {
                System.out.println("Clase desconocida");
            }
        }
        ficheroObjetos2.close();
        
        System.out.println("Deserializando todos los datos...");
        ficheroSalida2= new FileInputStream(fichero);
        ficheroObjetos2 = 
            new ObjectInputStream(ficheroSalida2);
        try {
            while(true) {
                Persona p3 = (Persona) ficheroObjetos2.readObject();
                System.out.println(p3.nombre);
                System.out.println(p3.email);
                System.out.println(p3.fechaNac);
            }
        }
        catch(ClassNotFoundException e) {
            System.out.println("Clase desconocida");
        }
        catch(EOFException e) {
            // Final del fichero, no es un error... si se lleva cuidado
            System.out.println("Final de la lectura");
        }
        ficheroObjetos2.close();
        System.out.println("Ejecución terminada");
     }
 }


