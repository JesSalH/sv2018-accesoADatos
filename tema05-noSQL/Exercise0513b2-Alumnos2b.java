package Alumnos;

import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

// Javier Saorin Vidal

public class AlumnosTest {
    public static void main(String[] args) throws UnknownHostException {
        
        Scanner sc = new Scanner(System.in);
        
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        MongoClient mongoClient = null;
        
        try {
            mongoClient = new MongoClient("localhost",27017);
            MongoDatabase database = mongoClient.getDatabase("alumnos");
           
            MongoCollection<Document> collection = database.getCollection("alumnos");
            
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                
                System.out.print("Introduce un nombre a buscar: ");
                String nombre = sc.nextLine();
                
                BasicDBObject filtros = (BasicDBObject) JSON.parse(
                        "{nombre: '" + nombre + "'}");

                System.out.println("\nMostrar los datos obtenidos (BSON).");
                for (Document cur : collection.find(filtros)) {
                    System.out.println(cur.toJson());
                }
                
            } finally {
                cursor.close();
            }
            
        }catch(MongoCommandException e){
            System.err.println(e.getErrorMessage());
        }
        
        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();
    }
}
