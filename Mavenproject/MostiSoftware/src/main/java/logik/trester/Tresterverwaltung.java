package logik.trester;

import java.sql.SQLException;

import persistenz.TresterDB;

public class Tresterverwaltung {
	
	private double preisPro1000L;
	private TresterDB tresterdb;
	
	public Tresterverwaltung(){
		tresterdb = new TresterDB();
		try {
			preisPro1000L = tresterdb.tresterpreisLaden();
		} catch (SQLException e) {
			preisPro1000L = 15.0;
			//System.out.println("Es wurde kein abgelegter Preis in der Datenbank gefunden. Deswegen wurde der Tresterpreis pro 1000 L nun auf 15.00 € gesetzt");
			e.printStackTrace();
		} 
	}

	public double getPreisPro1000L() {
		try {
			return tresterdb.tresterpreisLaden();
		} catch (SQLException e) {
			System.out.println("Der Tresterpreis konnte nicht geladen werden.");
			e.printStackTrace();
			return 15.0;
		}
	}

	public void setPreisPro1000L(double preis) throws SQLException {
		preisPro1000L = preis;
		tresterdb.updateTresterpreis(preis);
	}


}
