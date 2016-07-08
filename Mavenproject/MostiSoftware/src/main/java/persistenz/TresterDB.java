package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logik.kundenverwaltung.Kunde;
import logik.trester.Tresterabrechnung;

public class TresterDB {

	private Connection conn;

	public TresterDB() {
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Fehler beim Aufbau der Datenbank-Verbindung");
		}
	}

	public double tresterpreisLaden() {

		double preis = 0.0;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [tresterpreis]");
			while (rs.next()) {
				preis = rs.getDouble("PreisPro1000L");
			}
			s.close();
		}

		catch (Exception e) {
			System.out.println("Der Tresterpreis konnte nicht geladen werden.");
			System.out.println(e);
		}
		return preis;
	}

	public void updateTresterpreis(double neupreis) {
		try {
			PreparedStatement s = null;
			int i = 1;
			s = conn.prepareStatement("update tresterpreis set preispro1000L = ? where id = ? ");
			s.setDouble(1, neupreis);
			s.setInt(2, i++);
			s.executeUpdate();
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void trestereinkaufHinzufügen(Tresterabrechnung ta) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("insert into tresterverkäufe (kundenid, tresterliter, tresterpreis, tresterverkaufsdatum) values "
					+ "( '"
					+ ta.getKundenID()
					+ "', "
					+ ta.getLiterzahl()
					+ ", "
					+ ta.getPreis()
					+ ", {ts '"
					+ ta.getDate()
					+ " 00:00:00'} )");

			s.executeUpdate();
			s.close();
			System.out.println("Trestereinkauf von Kunde " + ta.getKundenID()
					+ " wurde abgespeichert.");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public ArrayList<Tresterabrechnung> ladeTresterabrechnungenInListe(
			ResultSet rs) {
		ArrayList<Tresterabrechnung> liste = new ArrayList<Tresterabrechnung>();

		try {
			while (rs.next()) {
				int kundenid = rs.getInt("kundenid");
				int literzahl = rs.getInt("tresterliter");
				Double preis = rs.getDouble("tresterpreis");
				java.sql.Date verkaufsdatum = rs
						.getDate("tresterverkaufsdatum");

				Tresterabrechnung ta = new Tresterabrechnung(kundenid,
						literzahl, preis, verkaufsdatum);
				liste.add(ta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterverkäufeZeitraumAusDB(
			java.sql.Date date1, java.sql.Date date2) {
		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();
		try {
			Statement s = conn.createStatement();
			System.out.println("1. Schritt");
			ResultSet rs = s
					.executeQuery("SELECT * FROM [tresterverkäufe] WHERE tresterverkaufsdatum BETWEEN{ts '"
							+ date1
							+ " 00:00:00'} AND {ts '"
							+ date2
							+ " 23:59:59'}");
			System.out.println("YEEES");

			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterverkäufeAusDB() {
		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [tresterverkäufe]");
			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;
	}

	public ArrayList<Tresterabrechnung> ladeTagesTresterVerkäufeAusDB(
			java.sql.Date date) {
		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();
		try {
			Statement s = conn.createStatement();
			System.out.println("1. Schritt");
			ResultSet rs = s
					.executeQuery("SELECT * FROM [tresterverkäufe] WHERE tresterverkaufsdatum BETWEEN{ts '"
							+ date
							+ " 00:00:00'} AND {ts '"
							+ date
							+ " 23:59:59'}");
			System.out.println("YEEES");
			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;

	}

	public ArrayList<Tresterabrechnung> ladeKundentrestereinkaufTagAusDB(
			Kunde kunde, java.sql.Date date) {
		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [tresterverkäufe] WHERE kundenid = "
							+ kunde.getKundenID()
							+ " AND tresterverkaufsdatum BETWEEN {ts '"
							+ date
							+ " 00:00:00'} AND {ts '" + date + " 23:59:59'} ");

			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;
	}

	public ArrayList<Tresterabrechnung> ladeKundenTrestereinkaufZeitraumAusDB(
			Kunde kunde, java.sql.Date date1, java.sql.Date date2) {

		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [tresterverkäufe] WHERE kundenid = "
							+ kunde.getKundenID()
							+ " AND tresterverkaufsdatum BETWEEN{ts '"
							+ date1
							+ " 00:00:00'} AND {ts '" + date2 + " 23:59:59'} ");

			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterEinkäufeVonKundeAusDB(
			Kunde kunde) {

		ArrayList<Tresterabrechnung> tliste = new ArrayList<Tresterabrechnung>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [tresterverkäufe] WHERE kundenid = "
							+ kunde.getKundenID() + "");
			tliste = ladeTresterabrechnungenInListe(rs);
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return tliste;
	}

}
