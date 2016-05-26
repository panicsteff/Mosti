package persistenz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import kundenverwaltung.Kunde;
import verkaufsverwaltung.Verkauf;
import verkaufsverwaltung.Verkaufsposition;
import dienstleistungenverwaltung.Dienstleistung;

public class VerkäufeDB {

	Connection conn;

	public ArrayList<Verkaufsposition> einkaufLaden(Kunde kunde, Date date) {

		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = "
							+ kunde.getKundenID() + "AND verkaufsdatum = "
							+ date + "");

			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");

				Verkaufsposition einkaufsposition = new Verkaufsposition(name,
						preis, menge, literzahl);
				einkaufsliste.add(einkaufsposition);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}

	public ArrayList<Verkaufsposition> alleEinkäufeVonKundeLaden(Kunde kunde) {
		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = "
							+ kunde.getKundenID() + "");

			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");

				Verkaufsposition einkaufsposition = new Verkaufsposition(name,
						preis, menge, literzahl);
				einkaufsliste.add(einkaufsposition);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}


	public void kundeneinkaufHinzufügen(Verkauf v) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;
			System.out.println("bis zur DB vorgedrungen");
			int count = 0;
			for (int i = 0; i < v.getListengröße(); i++) {
			s = conn.prepareStatement("insert into verkäufe (verkaufsposition, verkaufsmenge, literzahl, verkaufsdatum, einzelpreis, kundenid) values "
					+ "( '"
					+ v.getVerkaufsposition(i).getName() + "', " + v.getVerkaufsposition(i).getVerkaufsMenge()
					+ ", " + v.getVerkaufsposition(i).getLiterzahl() + ", " + v.getVerkaufsDatum()
					+ ", " + v.getVerkaufsposition(i).getPreis()+ ", " + v.getKunde().getKundenID()+ ")");

			s.executeUpdate();
			System.out.println("Zähler " + i);
			}
			s.close();
			System.out.println("nach dem gedöns");

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public ArrayList<Verkaufsposition> tagesVerkäufeLaden(Date date) {
		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			System.out.println("1. Schritt");
			ResultSet rs = s
//					.executeQuery("SELECT * FROM [verkäufe] WHERE verkaufsdatum = "
//							+ date + "");
					.executeQuery("SELECT * FROM [verkäufe] WHERE verkaufsdatum = '26.05.2016'");
			System.out.println("YEEES");

			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");

				Verkaufsposition einkaufsposition = new Verkaufsposition(name,
						preis, menge, literzahl);
				einkaufsliste.add(einkaufsposition);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}
	
	

	public void dlUpdaten(ArrayList<Dienstleistung> dliste) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			for (int i = 0; i < dliste.size(); i++) {
				s = conn.prepareStatement("Update dienstleistungen set dlname = ?, preisProLiter = ? where id = ? ");
				s.setString(1, dliste.get(i).getName());
				s.setDouble(2, dliste.get(i).getPreis());
				s.setInt(3, i);

				s.executeUpdate();

			}

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void dienstleistungLöschen(Dienstleistung d) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			PreparedStatement s = null;

			s = conn.prepareStatement("delete from dienstleistungen where dlname = '"
					+ d.getName() + "' ");

			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

}
