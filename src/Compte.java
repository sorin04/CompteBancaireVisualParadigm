import java.util.Vector;

public class Compte {
	private int numero;
	private float solde;
	public Client proprietaire;
	public Vector<Banquier> gerants = new Vector<>();
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

}
