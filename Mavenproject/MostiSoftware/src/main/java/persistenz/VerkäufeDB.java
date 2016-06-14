package persistenz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import logik.dienstleistungverwaltung.Dienstleistung;
import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.Verkauf;
import logik.verkaufsverwaltung.Verkaufsposition;

public class VerkäufeDB {

	Connection conn;

	public ArrayList<Verkaufsposition> kundeneinkaufLaden(Kunde kunde, Date date) {

		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = "
							+ kunde.getKundenID() + " AND verkaufsdatum BETWEEN {ts '"+date+" 00:00:00'} AND {ts '"+date+" 23:59:59'} ");

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
			for (int i = 0; i < v.getListengröße(); i++) {
			s = conn.prepareStatement("insert into verkäufe (verkaufsposition, verkaufsmenge, literzahl, verkaufsdatum, einzelpreis, kundenid) values "
					+ "( '"
					+ v.getVerkaufsposition(i).getName() + "', " + v.getVerkaufsposition(i).getVerkaufsMenge()
					+ ", " + v.getVerkaufsposition(i).getLiterzahl() + ", {ts '" +v.getVerkaufsDatum()+ " 00:00:00'} ,"
					 + v.getVerkaufsposition(i).getPreis()+ ", " + v.getKunde().getKundenID()+ ")");

			s.executeUpdate();
			}
			s.close();
			System.out.println("Einkauf von Kunde "+v.getKunde().getNachname() + " wurde abgespeichert.");

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
					.executeQuery("SELECT * FROM [verkäufe] WHERE verkaufsdatum BETWEEN{ts '"+date+" 00:00:00'} AND {ts '"+date+" 23:59:59'}");
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
	
	public ArrayList<Verkaufsposition> kundeneinkäufeZeitraumLaden(Kunde kunde, Date date1, Date date2) {

		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE kundenid = "
							+ kunde.getKundenID() + " AND verkaufsdatum BETWEEN{ts '"+date1+" 00:00:00'} AND {ts '"+date2+" 23:59:59'} ");

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
	
	public ArrayList<Verkaufsposition> VerkäufeZeitraumLaden(Date date1, Date date2) {
		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			System.out.println("1. Schritt");
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe] WHERE verkaufsdatum BETWEEN{ts '"+date1+" 00:00:00'} AND {ts '"+date2+" 23:59:59'}");
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
	
	public ArrayList<Verkaufsposition> alleVerkäufeLaden() {
		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenkank.mdb");
			Statement s = conn.createStatement();
			System.out.println("1. Schritt");
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verkäufe]");
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

	
	

}
