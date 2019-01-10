package mongo01;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Mongo01 {
	public static void main(String[] args) {

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("baterias");
        MongoCollection<Document> coleccion = db.getCollection("baterias");
        System.out.println("Cantidad de baterías en la bbdd "
                + db.getName() + ": "+ coleccion.count() );

        for(Document doc : coleccion.find(new Document("marca", "PowerAdd"))){
            System.out.println(doc.toJson());
        }
	}
}
