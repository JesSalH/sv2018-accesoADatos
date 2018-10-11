//Raul Gogna
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Persona implements Serializable{
    protected String nombre;
    protected String email;
    protected Date fechaNac;
    public SimpleDateFormat ft;
    
    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        ft = new SimpleDateFormat("dd-MM-yyyy");
    }
    
    public void setDate(String fecha) {
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

public class Exercise0117 {

    public static void main(String[] args) throws FileNotFoundException, 
    IOException{
        File fichero = new File("personas.dat");
        FileOutputStream ficheroSalida = new FileOutputStream(fichero);
        ObjectOutputStream ficheroObjetos = 
                new ObjectOutputStream(ficheroSalida);
        
        Persona p = new Persona("raul", "raul@gmail.com");
        p.setDate("5-04-2018");
        ficheroObjetos.writeObject(p);
        ficheroObjetos.close();

    }

}
