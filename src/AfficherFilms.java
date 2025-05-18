import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;

public class AfficherFilms {

    public static void afficher(MongoCollection<Document> collection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Champ de recherche (ex : nom, annee) : ");
        String champ = scanner.nextLine();

        System.out.print("Valeur à chercher : ");
        String valeur = scanner.nextLine();

        Document filtre;

        if (champ.equalsIgnoreCase("annee")) {
            try {
                int annee = Integer.parseInt(valeur);
                filtre = new Document(champ, annee);
            } catch (NumberFormatException e) {
                System.out.println("Format d'année incorrect.");
                return;
            }
        } else {
            filtre = new Document(champ, valeur);
        }

        FindIterable<Document> resultats = collection.find(filtre);

        System.out.println("Documents trouvés :");
        for (Document doc : resultats) {
            System.out.println(doc.toJson());
        }
    }

    public static void main(String[] args) {
        // Juste pour tests indépendants, sinon tu peux appeler afficher depuis un menu ailleurs
        try (var mongoClient = com.mongodb.client.MongoClients.create("mongodb://localhost:27017")) {
            var database = mongoClient.getDatabase("cinema");
            var collection = database.getCollection("films");

            afficher(collection);
        }
    }
}
