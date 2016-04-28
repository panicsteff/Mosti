package mitarbeiterverwaltung;

public class Mitarbeiterliste {

	private Mitarbeiter[] liste;

	public Mitarbeiterliste(){
		liste = new Mitarbeiter[10];
		Mitarbeiter m = new Mitarbeiter("Maier", "Sepp", null, 0, 0, null, null, "daMaia");
		liste[0] = m;
		m = new Mitarbeiter("Schmidt", "Helmut", null, 0, 0, null, null, "Schmiddi");
		liste[1] = m;
		m = new Mitarbeiter("Huber", "Franziska", null, 0, 0, null, null, "DIEFrau");
		liste[2] = m;
		m = new Mitarbeiter("Schofreiter", "Franz", null, 0, 0, null, null, "Schofreiter");
		liste[3] = m;
		m = new Mitarbeiter("Gamsberger", "Schos", null, 0, 0, null, null, "BlauesAuge");
		liste[4] = m;
	}

	public Mitarbeiter[] getListe(){
		return liste;
	}
}
