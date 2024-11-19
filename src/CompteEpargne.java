public class CompteEpargne extends Compte {
	private float plafond;
	private float taux;

	public CompteEpargne(float soldeInitial, float plafond, float taux) {
		super(soldeInitial);
		this.plafond = plafond;
		this.taux = taux;
	}

	public float calculerInterets() {
		return getSolde() * (taux / 100);
	}

	@Override
	public void deposer(float montant) {
		if (getSolde() + montant <= plafond) {
			super.deposer(montant);
		} else {
			System.out.println("Dépassement du plafond !");
		}
	}
}
