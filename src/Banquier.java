import java.util.Vector;

public class Banquier {
	private int numeroPro;
	public Vector<Compte> comptes = new Vector<>();

	public Banquier(int numeroPro) {
		this.numeroPro = numeroPro;
	}

	public void creerCompte(Client client, float soldeInitial, boolean estCourant, float param1, float param2) {
		Compte compte;
		if (estCourant) {
			compte = new CompteCourant(soldeInitial, param1, param2);
		} else {
			compte = new CompteEpargne(soldeInitial, param1, param2);
		}
		compte.proprietaire = client;
		comptes.add(compte);
		client.sesComptes.add(compte);
	}
}
