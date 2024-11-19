import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("Doe", "John", "123 rue Exemple", new Date());
        Banquier banquier = new Banquier(1001);

        banquier.creerCompte(client, 500.0f, true, 200.0f, 10.0f);
        banquier.creerCompte(client, 1000.0f, false, 5000.0f, 2.0f);

        while (true) {
            System.out.println("\n--- Gestion des comptes ---");
            System.out.println("1. Voir les comptes");
            System.out.println("2. Effectuer une opération (dépôt/retrait)");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();

            if (choix == 1) {
                afficherComptes(client);
            } else if (choix == 2) {
                effectuerOperation(client, scanner);
            } else if (choix == 3) {
                System.out.println("Au revoir !");
                break;
            } else {
                System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }

    private static void afficherComptes(Client client) {
        System.out.println("\n--- Liste des comptes ---");
        int numero = 1;
        for (Compte compte : client.sesComptes) {
            String typeCompte;
            if (compte instanceof CompteCourant) {
                typeCompte = "Courant";
            } else {
                typeCompte = "Épargne";
            }
            System.out.println(numero + ". Compte " + typeCompte + " - Solde : " + compte.getSolde());
            numero++;
        }
    }


    private static void effectuerOperation(Client client, Scanner scanner) {
        afficherComptes(client);

        System.out.print("\nChoisissez un compte (numéro) : ");
        int numCompte = scanner.nextInt();

        if (numCompte < 1 || numCompte > client.sesComptes.size()) {
            System.out.println("Compte invalide.");
            return;
        }

        Compte compte = client.sesComptes.get(numCompte - 1);

        System.out.print("Voulez-vous déposer (1) ou retirer (2) ? ");
        int operation = scanner.nextInt();

        System.out.print("Entrez le montant (avec un point ou une virgule comme séparateur) : ");
        scanner.nextLine();
        String montantStr = scanner.nextLine();

        float montant = parseMontant(montantStr);
        if (montant <= 0) {
            System.out.println("Montant invalide.");
            return;
        }

        if (operation == 1) {
            compte.deposer(montant);
        } else if (operation == 2) {
            compte.retirer(montant);
        } else {
            System.out.println("Opération invalide.");
        }

        System.out.println("Opération terminée. \nNouveau solde : " + compte.getSolde());
    }

    private static float parseMontant(String montantStr) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setDecimalSeparator('.');
            symbols.setGroupingSeparator(',');

            DecimalFormat df = new DecimalFormat();
            df.setDecimalFormatSymbols(symbols);

            Number number = df.parse(montantStr.trim());
            return number.floatValue();
        } catch (Exception e) {
            System.out.println("Erreur : Impossible de lire le montant.");
            return -1.0f;
        }
    }
}
