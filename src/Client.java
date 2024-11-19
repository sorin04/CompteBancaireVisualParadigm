import java.util.Date;
import java.util.Vector;

public class Client {
	private String nom;
	private String prenom;
	private String adresse;
	private Date naissance;
	public Vector<Compte> sesComptes = new Vector<>();

	public Client(String nom, String prenom, String adresse, Date naissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.naissance = naissance;
	}

	public void gererCompte(Compte compte, float montant, boolean deposer) {
		if (deposer) {
			compte.deposer(montant);
		} else {
			compte.retirer(montant);
		}
	}
}
