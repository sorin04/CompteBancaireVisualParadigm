import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Créer un client
        Client client = new Client("Doe", "John", "123 rue Exemple", new Date());

        // Créer un banquier
        Banquier banquier = new Banquier(1001);

        // Charger les comptes depuis le fichier (s'il existe)
        banquier.chargerComptes();

        // Création de comptes pour le client
        // Compte courant (solde initial, découvert autorisé, frais de découvert)
        //banquier.creerCompte(client, 5000.0f, true, 200.0f, 10.0f);

        // Compte épargne (solde initial, taux d'intérêt, frais de gestion)
        //banquier.creerCompte(client, 10000.0f, false, 2.5f, 5.0f);

        // Affichage des comptes existants
        //banquier.afficherComptes();

        // Menu pour gérer les comptes
        while (true) {
            System.out.println("\n--- Gestion des comptes ---");
            System.out.println("1. Voir les comptes");
            System.out.println("2. Effectuer une opération (dépôt/retrait)");
            System.out.println("3. Supprimer un compte");
            System.out.println("4. Enregistrer les changements");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();

            if (choix == 1) {
                // Afficher les comptes
                banquier.afficherComptes();
            } else if (choix == 2) {
                // Effectuer une opération sur un compte
                System.out.print("Entrez le numéro du compte : ");
                int numCompte = scanner.nextInt();
                System.out.print("Montant : ");
                float montant = scanner.nextFloat();
                System.out.println("1. Déposer\n2. Retirer");
                int action = scanner.nextInt();
                if (action == 1) {
                    client.gererCompte(banquier.comptes.get(numCompte - 1), montant, true);
                } else {
                    client.gererCompte(banquier.comptes.get(numCompte - 1), montant, false);
                }
            } else if (choix == 3) {
                // Supprimer un compte
                System.out.print("Entrez le numéro du compte à supprimer : ");
                int numCompteASupprimer = scanner.nextInt();
                banquier.supprimerCompte(numCompteASupprimer);
            } else if (choix == 4) {
                // Enregistrer les comptes dans un fichier
                banquier.sauvegarderComptes();
                System.out.println("Changements enregistrés.");
            } else if (choix == 5) {
                System.out.println("Au revoir !");
                break;
            } else {
                System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
