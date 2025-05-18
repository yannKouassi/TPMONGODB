import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;

public class CinemaApp {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("cinema");
            MongoCollection<Document> collection = database.getCollection("films");

            Scanner scanner = new Scanner(System.in);
            boolean continuer = true;

            while (continuer) {
                System.out.println("_______________AKWABA___________");
                System.out.println("\nChoisissez une action :");
                System.out.println("1 - Insérer un film");
                System.out.println("2 - Afficher des films");
                System.out.println("3 - Modifier un film");
                System.out.println("4 - Supprimer un film");
                System.out.println("0 - Quitter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        InsererFilm.inserer(collection);
                        break;
                    case "2":
                        AfficherFilms.afficher(collection);
                        break;
                    case "3":
                        MongoModifier.modifierDocument(collection);
                        break;
                    case "4":
                        MongoSupprimer.supprimerDocument(collection);
                        break;
                    case "0":
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            }
        }
    }
}
