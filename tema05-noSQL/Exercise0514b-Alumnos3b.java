import java.util.Scanner;
import java.util.logging.Level;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Program {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("alumnos");
        MongoCollection<Document> coleccion = db.getCollection("alumnos");

        System.out.print("Alumno a buscar: ");
        String nombre = scn.nextLine();
        if(coleccion.count(new Document("nombre", nombre)) == 0) {
            System.out.println("No encontrado");
        }
        else {
            for(Document doc : coleccion.find(new Document("nombre", nombre))){
                System.out.println("Nombre: " + doc.getString("nombre"));
                System.out.println("Año nacimiento: " + doc.getDouble("nacido"));
                System.out.println("Empresa actual: " + doc.getString("empresaActual"));
                System.out.println("Cargo: " + doc.getString("cargo"));
            }
        }
    }
}
