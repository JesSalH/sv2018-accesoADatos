//Pedro Luis Coloma

package alumnos;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        Scanner scn= new Scanner(System.in);
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("alumnos");
        MongoCollection<Document> coleccion = db.getCollection("alumnos");
        
        System.out.println("Indica el nombre del alumno a buscar:");
        String nombre = scn.nextLine();
        
        for(Document doc : coleccion.find(new Document("nombre", nombre))){
            System.out.println(doc.toJson());
        }
    }
}
