import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Scanner;

public class MongoModifier {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("cinema");
            MongoCollection<Document> collection = database.getCollection("films");

            modifierDocument(collection);
        }
    }

    public static void modifierDocument(MongoCollection<Document> collection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Champ critère pour rechercher le film à modifier (ex : nom, annee) : ");
        String critere = scanner.nextLine();

        System.out.print("Valeur du critère : ");
        String valeurCritere = scanner.nextLine();

        System.out.print("Champ à modifier (ex : nom, annee) : ");
        String champAModifier = scanner.nextLine();

        System.out.print("Nouvelle valeur : ");
        String nouvelleValeur = scanner.nextLine();

        Document filtre;
        if (critere.equalsIgnoreCase("annee")) {
            filtre = new Document(critere, Integer.parseInt(valeurCritere));
        } else {
            filtre = new Document(critere, valeurCritere);
        }

        Document miseAJour;
        if (champAModifier.equalsIgnoreCase("annee")) {
            miseAJour = new Document("$set", new Document(champAModifier, Integer.parseInt(nouvelleValeur)));
        } else {
            miseAJour = new Document("$set", new Document(champAModifier, nouvelleValeur));
        }

        long modifiedCount = collection.updateOne(filtre, miseAJour).getModifiedCount();

        System.out.println(modifiedCount + " document(s) modifié(s).");
    }
}

