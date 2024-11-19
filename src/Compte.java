public class Compte {
	private int numero;
	private float solde;
	public Client proprietaire;
	private static int compteur = 1;

	public Compte(float soldeInitial) {
		this.numero = compteur++;
		this.solde = soldeInitial;
	}

	public void deposer(float montant) {
		if (montant > 0) {
			solde += montant;
			System.out.println("Déposé : " + montant);
		}
	}

	public void retirer(float montant) {
		if (montant > 0 && solde >= montant) {
			solde -= montant;
			System.out.println("Retiré : " + montant);
		} else {
			System.out.println("Fonds insuffisants.");
		}
	}

	public float getSolde() {
		return solde;
	}

	public String getType() {
		return "Compte"; // Définir un type de base
	}

	public float getParam1() {
		return 0;
	}

	public float getParam2() {
		return 0;
	}
}
