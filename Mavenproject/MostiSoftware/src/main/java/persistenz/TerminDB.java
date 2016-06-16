package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import logik.terminplanung.Termin;

public class TerminDB {

	private static Connection conn;
	
	public TerminDB(){
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Termin> termineLaden(Date datum) {

		ArrayList<Termin> terminliste = new ArrayList<Termin>();

		try {
			
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [termine] Where Datum  "
							+ "BETWEEN{ts '"
							+ datum
							+ " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");

			while (rs.next()) {
				Termin t = new Termin();
				t.setTerminId(rs.getInt("ID"));
				t.setKundenId(rs.getInt("kundenId"));
				t.setAnzahlZeitslots(rs.getInt("AnzahlZeitslot"));
				t.setUhrzeit(rs.getInt("Beginn"));
				t.setDatum(rs.getDate("Datum"));
				terminliste.add(t);
			}
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return terminliste;
	}

	public void termineSpeichern(int kundenId, int anzahlZeitslot, Date datum,
			int beginn) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("Insert into termine (kundenId, AnzahlZeitslot, Datum, Beginn) VALUES (?, ?, ?,?)");
			s.setInt(1, kundenId);
			s.setInt(2, anzahlZeitslot);
			s.setDate(3, datum);
			s.setInt(4, beginn);

			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String kundenNamenLaden(int kundenId) {

		String name = new String();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT vorname, nachname FROM [kunden] Where id = "
							+ kundenId);

			while (rs.next()) {
				name = rs.getString("Nachname") + ", "
						+ rs.getString("Vorname");
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return name;

	}


	public ArrayList<Integer> kundenIdLaden(String name) {

		ArrayList<Integer> kundenId = new ArrayList<Integer>();

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [kunden] where nachname like '"
							+ name + "%'");

			while (rs.next()) {
				Integer i = rs.getInt("ID");
				kundenId.add(i);
			}

			s.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return kundenId;
	}
	
	public static void terminLöschen(int terminId){
		
		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("Delete from termine where ID = " + terminId);
			
			s.executeUpdate();
			s.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int tresterKundeLaden(Date datum){
		
		int kundenId = 0;
		
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [trestertermine] Where Datum  "
							+ "BETWEEN{ts '" + datum + " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");
			while(rs.next()){
				kundenId = rs.getInt("kundenId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kundenId;
	}
	
	public void tresterKundeNeuSpeichern(Date d, int kundenId) {

		try {
			PreparedStatement s = null;

			s = conn.prepareStatement("Insert into trestertermine (Datum, kundenId) VALUES (?,?)");
			s.setDate(1, d);
			s.setInt(2, kundenId);
			
			s.executeUpdate();

			s.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void tresterKundeUpdaten(Date datum, int kundenId){
		
		try{
			PreparedStatement s = null;
			
			s = conn.prepareStatement("Update trestertermine set kundenid = " + kundenId + " where Datum BETWEEN{ts '"
							+ datum	+ " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");
			
			s.executeUpdate();
			s.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tresterKundeLöschen(Date datum){
		
		try{
			PreparedStatement s = null;
			s = conn.prepareStatement("Delete from trestertermine where datum BETWEEN{ts '"
							+ datum	+ " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'} ");
			
			s.executeUpdate();
			s.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}