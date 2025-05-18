import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class ConnexionMongo {
    public static void main(String[] args) {
        // 1 - Connexion au serveur MongoDB local
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // 2 - Création / accès à la base "cinema"
        MongoDatabase database = mongoClient.getDatabase("cinema");

        // 3 - Création / accès à la collection "films"
        MongoCollection<Document> collection = database.getCollection("films");

        System.out.println("Connexion réussie à la base : " + database.getName());

        // 4 - Fermer la connexion
        mongoClient.close();
    }
}

