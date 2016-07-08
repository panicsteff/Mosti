package logik.verkaufsverwaltung;

import java.sql.Date;

public class VerkaufspositionPlus extends Verkaufsposition { // diese Klasse, um
																// Daten für die
																// Übersichtserstellung
																// erfassen zu
																// können
	private int kundenID;
	private Date date;

	public VerkaufspositionPlus(String name, double preis, int verkaufsmenge,
			int literzahl, int kundenid, Date datum) {
		super(name, preis, verkaufsmenge, literzahl);
		setKundenID(kundenid);
		date = datum;

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getKundenID() {
		return kundenID;
	}

	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}

}
