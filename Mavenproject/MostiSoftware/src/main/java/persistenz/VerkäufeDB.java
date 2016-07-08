package persistenz;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.Verkauf;
import logik.verkaufsverwaltung.Verkaufsposition;
import logik.verkaufsverwaltung.VerkaufspositionPlus;

public class Verk�ufeDB {

	Connection conn;

	public Verk�ufeDB() {
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Fehler beim Aufbau der Datenbank-Verbindung");
		}
	}

	public void kundeneinkaufHinzuf�gen(Verkauf v) {

		try {
			PreparedStatement s = null;
			for (int i = 0; i < v.getListengr��e(); i++) {
				s = conn.prepareStatement("insert into verk�ufe (verkaufsposition, verkaufsmenge, literzahl, verkaufsdatum, einzelpreis, kundenid) values "
						+ "( '"
						+ v.getVerkaufsposition(i).getName()
						+ "', "
						+ v.getVerkaufsposition(i).getVerkaufsMenge()
						+ ", "
						+ v.getVerkaufsposition(i).getLiterzahl()
						+ ", {ts '"
						+ v.getVerkaufsDatum()
						+ " 00:00:00'} ,"
						+ v.getVerkaufsposition(i).getPreis()
						+ ", "
						+ v.getKunde().getKundenID() + ")");

				s.executeUpdate();
			}
			s.close();
			System.out.println("Einkauf von Kunde "
					+ v.getKunde().getNachname() + " wurde abgespeichert.");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public ArrayList<Verkaufsposition> ladeVerkaufspositionenInListe(
			ResultSet rs) {
		ArrayList<Verkaufsposition> liste = new ArrayList<Verkaufsposition>();

		try {
			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");

				Verkaufsposition einkaufsposition = new Verkaufsposition(name,
						preis, menge, literzahl);
				liste.add(einkaufsposition);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public ArrayList<VerkaufspositionPlus> ladeVerkaufspositionenPlusInListe(
			ResultSet rs) {
		ArrayList<VerkaufspositionPlus> liste = new ArrayList<VerkaufspositionPlus>();

		try {

			while (rs.next()) {
				String name = rs.getString("verkaufsposition");
				Double preis = rs.getDouble("einzelpreis");
				int menge = rs.getInt("verkaufsmenge");
				int literzahl = rs.getInt("literzahl");
				int kundenid = rs.getInt("kundenid");
				Date datum = rs.getDate("verkaufsdatum");

				VerkaufspositionPlus vposition = new VerkaufspositionPlus(name,
						preis, menge, literzahl, kundenid, datum);
				liste.add(vposition);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	// bestimmter Kunde
	public Verkauf kundeneinkaufLaden(Kunde kunde, Date date) {

		ArrayList<Verkaufsposition> einkaufsliste = new ArrayList<Verkaufsposition>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE kundenid = "
							+ kunde.getKundenID()
							+ " AND verkaufsdatum BETWEEN {ts '" + date
							+ " 00:00:00'} AND {ts '" + date + " 23:59:59'} ");

			einkaufsliste = ladeVerkaufspositionenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		Verkauf verkauf = new Verkauf(kunde, date, einkaufsliste);
		return verkauf;
	}

	public ArrayList<VerkaufspositionPlus> kundeneinkaufLaden2(Kunde kunde,
			Date date) {

		ArrayList<VerkaufspositionPlus> einkaufsliste = new ArrayList<VerkaufspositionPlus>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE kundenid = "
							+ kunde.getKundenID()
							+ " AND verkaufsdatum BETWEEN {ts '" + date
							+ " 00:00:00'} AND {ts '" + date + " 23:59:59'} ");

			einkaufsliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}

	public ArrayList<VerkaufspositionPlus> alleEink�ufeVonKundeLaden2(
			Kunde kunde) {
		ArrayList<VerkaufspositionPlus> vliste = new ArrayList<VerkaufspositionPlus>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE kundenid = "
							+ kunde.getKundenID() + "");
			vliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return vliste;
	}

	public ArrayList<VerkaufspositionPlus> kundeneink�ufeZeitraumLaden2(
			Kunde kunde, Date date1, Date date2) {

		ArrayList<VerkaufspositionPlus> einkaufsliste = new ArrayList<VerkaufspositionPlus>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE kundenid = "
							+ kunde.getKundenID()
							+ " AND verkaufsdatum BETWEEN{ts '" + date1
							+ " 00:00:00'} AND {ts '" + date2 + " 23:59:59'} ");

			einkaufsliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}
	
	// alle Kunden

	public ArrayList<VerkaufspositionPlus> tagesVerk�ufeLaden2(Date date) {
		ArrayList<VerkaufspositionPlus> einkaufsliste = new ArrayList<VerkaufspositionPlus>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE verkaufsdatum BETWEEN{ts '"
							+ date
							+ " 00:00:00'} AND {ts '"
							+ date
							+ " 23:59:59'}");
			
			einkaufsliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}

	public ArrayList<VerkaufspositionPlus> Verk�ufeZeitraumLaden2(Date date1,
			Date date2) {
		ArrayList<VerkaufspositionPlus> einkaufsliste = new ArrayList<VerkaufspositionPlus>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [verk�ufe] WHERE verkaufsdatum BETWEEN{ts '"
							+ date1
							+ " 00:00:00'} AND {ts '"
							+ date2
							+ " 23:59:59'}");

			einkaufsliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}

	public ArrayList<VerkaufspositionPlus> alleVerk�ufeLaden2() {
		ArrayList<VerkaufspositionPlus> einkaufsliste = new ArrayList<VerkaufspositionPlus>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [verk�ufe]");

			einkaufsliste = ladeVerkaufspositionenPlusInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return einkaufsliste;
	}

}
