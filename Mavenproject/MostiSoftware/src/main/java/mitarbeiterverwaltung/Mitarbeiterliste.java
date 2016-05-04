package mitarbeiterverwaltung;

public class Mitarbeiterliste {

	private Mitarbeiter[] liste;

	public Mitarbeiterliste(){
		liste = new Mitarbeiter[10];
		Mitarbeiter m = new Mitarbeiter("Maier", "Sepp", null, null, null, null, null, 1,"daMaia");
		liste[0] = m;
		m = new Mitarbeiter("Schmidt", "Helmut", null, null, null, null, null, 2, "Schmiddi");
		liste[1] = m;
		m = new Mitarbeiter("Huber", "Franziska", null, null, null, null, null, 3, "DIEFrau");
		liste[2] = m;
		m = new Mitarbeiter("Schofreiter", "Franz", null, null, null, null, null, 4, "Schofreiter");
		liste[3] = m;
		m = new Mitarbeiter("Gamsberger", "Schos", null, null, null, null, null, 5, "BlauesAuge");
		liste[4] = m;
	}

	public Mitarbeiter[] getListe(){
		return liste;
	}
}
