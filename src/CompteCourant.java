public class CompteCourant extends Compte {
	private float decouvertAutorise;
	private float fraisDecouvert;

	public CompteCourant(float soldeInitial, float decouvertAutorise, float fraisDecouvert) {
		super(soldeInitial);
		this.decouvertAutorise = decouvertAutorise;
		this.fraisDecouvert = fraisDecouvert;
	}

	@Override
	public void retirer(float montant) {
		if (montant > 0 && getSolde() + decouvertAutorise >= montant) {
			super.retirer(montant);
		} else {
			System.out.println("Découvert dépassé !");
		}
	}

	@Override
	public String getType() {
		return "Courant";
	}

	@Override
	public float getParam1() {
		return decouvertAutorise;
	}

	@Override
	public float getParam2() {
		return fraisDecouvert;
	}
}
