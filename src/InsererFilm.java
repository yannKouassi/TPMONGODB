import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsererFilm {

    public static void inserer(MongoCollection<Document> collection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nom du film : ");
        String nom = scanner.nextLine();

        System.out.print("Année du film : ");
        int annee = Integer.parseInt(scanner.nextLine());

        System.out.print("Nom du réalisateur : ");
        String nomRea = scanner.nextLine();

        System.out.print("Prénom du réalisateur : ");
        String prenomRea = scanner.nextLine();

        // Saisie acteurs
        List<Document> acteurs = new ArrayList<>();
        while(true) {
            System.out.print("Ajouter un acteur ? (oui/non) : ");
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("oui")) break;

            System.out.print("Nom de l'acteur : ");
            String nomAct = scanner.nextLine();

            System.out.print("Prénom de l'acteur : ");
            String prenomAct = scanner.nextLine();

            acteurs.add(new Document("nom", nomAct).append("prenom", prenomAct));
        }

        // Saisie catégories
        List<String> categories = new ArrayList<>();
        while(true) {
            System.out.print("Ajouter une catégorie ? (oui/non) : ");
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("oui")) break;

            System.out.print("Nom de la catégorie : ");
            String cat = scanner.nextLine();
            categories.add(cat);
        }

        // Création du document complet
        Document film = new Document("nom", nom)
                .append("annee", annee)
                .append("realisateur", new Document("nom", nomRea).append("prenom", prenomRea))
                .append("acteurs", acteurs)
                .append("categorie", categories);

        // Insertion dans la collection
        collection.insertOne(film);

        System.out.println("Film inséré avec succès !");
    }

    // Tu peux garder un main juste pour tester indépendamment si tu veux
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("cinema");
            MongoCollection<Document> collection = database.getCollection("films");

            inserer(collection);
        }
    }
}
