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

/**
 * 
 * @author Jorge Calabuig Bartual <IES. SAN VICENTE - 2º DAM>
 *
 */
public class Alumnos {

    private static String pedir(String mensaje) {
        Scanner scn = new Scanner(System.in);
        System.out.print(mensaje + " ");
        return scn.nextLine();
    }

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient mongoClient = null;

        try {
            mongoClient = new MongoClient("localhost", 27017);

            MongoDatabase database = mongoClient.getDatabase("alumnos");

            MongoCollection<Document> collection =
                    database.getCollection("alumnos");


            String nombre = pedir("Nombre del alumno:");

            BasicDBObject filtros =
                    (BasicDBObject) JSON.parse("{nombre:\"" + nombre + "\"}");

            MongoCursor<Document> cursor = collection.find(filtros).iterator();
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            } finally {
                cursor.close();
            }

            mongoClient.close();
        } catch (MongoCommandException e) {
            System.err.println(
                    "Se produjo un error ejecutando un comando de mongo");
        }
    }

}
