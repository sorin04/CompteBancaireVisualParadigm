public class CompteEpargne extends Compte {
	private float tauxInteret;
	private float soldeMin;

	public CompteEpargne(float soldeInitial, float tauxInteret, float soldeMin) {
		super(soldeInitial);
		this.tauxInteret = tauxInteret;
		this.soldeMin = soldeMin;
	}

	@Override
	public void retirer(float montant) {
		if (getSolde() - montant >= soldeMin) {
			super.retirer(montant);
		} else {
			System.out.println("Solde minimum requis.");
		}
	}

	@Override
	public String getType() {
		return "Epargne";
	}

	@Override
	public float getParam1() {
		return tauxInteret;
	}

	@Override
	public float getParam2() {
		return soldeMin;
	}
}
