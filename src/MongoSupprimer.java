import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.Scanner;

public class MongoSupprimer {

    public static void supprimerDocument(MongoCollection<Document> collection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Champ critère pour supprimer le film (ex : nom, annee) : ");
        String critere = scanner.nextLine();

        System.out.print("Valeur du critère : ");
        String valeurCritere = scanner.nextLine();

        Document filtre;
        if (critere.equals("annee")) {
            filtre = new Document(critere, Integer.parseInt(valeurCritere));
        } else {
            filtre = new Document(critere, valeurCritere);
        }

        DeleteResult result = collection.deleteOne(filtre);

        System.out.println(result.getDeletedCount() + " document(s) supprimé(s).");
    }

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("cinema");
            MongoCollection<Document> collection = database.getCollection("films");

            supprimerDocument(collection);
        }
    }
}

