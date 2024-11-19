import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("Doe", "John", "123 rue Exemple", new Date());
        Banquier banquier = new Banquier(1001);

        // Charger les comptes à partir du fichier
        banquier.chargerComptes();

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
                banquier.afficherComptes();
            } else if (choix == 2) {
                System.out.println("Entrez le numéro du compte : ");
                int numCompte = scanner.nextInt();
                System.out.println("Montant : ");
                float montant = scanner.nextFloat();
                System.out.println("1. Déposer\n2. Retirer");
                int action = scanner.nextInt();
                if (action == 1) {
                    client.gererCompte(banquier.comptes.get(numCompte - 1), montant, true);
                } else {
                    client.gererCompte(banquier.comptes.get(numCompte - 1), montant, false);
                }
            } else if (choix == 3) {
                System.out.print("Entrez le numéro du compte à supprimer : ");
                int numCompteASupprimer = scanner.nextInt();
                banquier.supprimerCompte(numCompteASupprimer);
            } else if (choix == 4) {
                // Enregistrer les changements dans le fichier
                banquier.sauvegarderComptes();
                System.out.println("Les changements ont été enregistrés.");
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
