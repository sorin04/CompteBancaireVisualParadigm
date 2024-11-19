import java.util.Vector;
import java.io.*;
import java.util.Scanner;

public class Banquier {
	private int numeroPro;
	public Vector<Compte> comptes = new Vector<>();

	public Banquier(int numeroPro) {
		this.numeroPro = numeroPro;
	}
	public void creerCompte(Client client, float soldeInitial, boolean estCourant, float param1, float param2) {
		Compte compte;
		if (estCourant) {
			// Compte Courant
			compte = new CompteCourant(soldeInitial, param1, param2);
		} else {
			// Compte Epargne
			compte = new CompteEpargne(soldeInitial, param1, param2);
		}

		compte.proprietaire = client;
		comptes.add(compte);
		client.sesComptes.add(compte);
		System.out.println("Compte créé avec succès.");
		sauvegarderComptes();
	}

	public void afficherComptes() {
		System.out.println("--- Liste des comptes ---");
		int numero = 1;
		for (Compte compte : comptes) {
			if (compte.proprietaire != null) {
				System.out.println(numero + ". " + compte.getType() + " - Solde : " + compte.getSolde() + " - Propriétaire : " + compte.proprietaire.getNom());
			} else {
				System.out.println(numero + ". " + compte.getType() + " - Solde : " + compte.getSolde() + " - Propriétaire inconnu");
			}
			numero++;
		}
	}

	// Supprimer un compte en fonction de son numéro
	public void supprimerCompte(int numeroCompte) {
		if (numeroCompte < 1 || numeroCompte > comptes.size()) {
			System.out.println("Numéro de compte invalide.");
			return;
		}

		Compte compteASupprimer = comptes.get(numeroCompte - 1);
		comptes.remove(compteASupprimer);
		if (compteASupprimer.proprietaire != null) {
			compteASupprimer.proprietaire.sesComptes.remove(compteASupprimer);
		}

		sauvegarderComptes();
		System.out.println("Compte numéro " + numeroCompte + " supprimé avec succès.");
	}


	void sauvegarderComptes() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("comptes.txt"))) {
			for (Compte compte : comptes) {

				writer.write(compte.getType() + ";" + compte.getSolde() + ";" + compte.getParam1() + ";" + compte.getParam2() + "\n");
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde des comptes.");
		}
	}



	// Charger les comptes depuis un fichier
	public void chargerComptes() {
		try (Scanner scanner = new Scanner(new File("comptes.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(";");
				if (data[0].equals("Courant")) {
					// Créer un compte courant
					CompteCourant compteCourant = new CompteCourant(
							Float.parseFloat(data[1]),
							Float.parseFloat(data[2]),
							Float.parseFloat(data[3])
					);
					comptes.add(compteCourant);
				} else if (data[0].equals("Epargne")) {
					// Créer un compte épargne
					CompteEpargne compteEpargne = new CompteEpargne(
							Float.parseFloat(data[1]),
							Float.parseFloat(data[2]),
							Float.parseFloat(data[3])
					);
					comptes.add(compteEpargne);
				}
			}
		} catch (IOException e) {
			System.out.println("Aucun fichier de comptes trouvé. Création de nouveaux comptes.");
		}
	}
}
