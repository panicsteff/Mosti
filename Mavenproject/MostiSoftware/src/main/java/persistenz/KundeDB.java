package persistenz;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import logik.kundenverwaltung.Kunde;

public class KundeDB {

	Connection conn;
	ArrayList<Kunde> kundenliste;

	public ArrayList<Kunde> kundenLaden() throws FileNotFoundException{

		kundenliste = new ArrayList<Kunde>();

		try {

			conn = DriverManager
			.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from [kunden]");

			while (rs.next()) {
				Kunde k = new Kunde();
				k.setNachname(rs.getString("Nachname"));
				k.setVorname(rs.getString("Vorname"));
				k.setStrasse(rs.getString("Strasse"));
				k.setPlz(rs.getString("PLZ"));
				k.setWohnort(rs.getString("Wohnort"));
				k.setTel(rs.getString("Telefonnummer"));
				k.setKundenID(rs.getInt("ID"));
				kundenliste.add(k);
			}
			s.close();

			return kundenliste;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void kundenSpeichern(ArrayList<Kunde> kundenliste) {

		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
			PreparedStatement s = null;
			
			for (Kunde k: kundenliste){
				
				s = conn
						.prepareStatement("update kunden set nachname = ?, vorname = ?,"
								+ " straße = ?, PLZ = ?, wohnort = ?, telefonnummer = ?"
								+ " where id = ?");
				s.setString(1, k.getNachname());
				s.setString(2, k.getVorname());
				s.setString(3, k.getStrasse());
				s.setString(4, k.getPlz());
				s.setString(5, k.getWohnort());
				s.setString(6, k.getTel());
				s.setInt(7, k.getKundenID());
				
				s.executeUpdate();
			}
			
			
			s.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void kundeEinfügen(Kunde k){
		
		try{
			conn = DriverManager.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
			PreparedStatement s = conn.prepareStatement("Insert into kunden (Nachname, Vorname, Strasse, Plz, Wohnort, Telefonnummer) VALUES (?, ?, ?, ?, ?, ?)");
			
			s.setString(1, k.getNachname());
			s.setString(2, k.getVorname());
			s.setString(3, k.getStrasse());
			s.setString(4, k.getPlz());
			s.setString(5, k.getWohnort());
			s.setString(6, k.getTel());
			
			s.executeUpdate();
			s.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void kundeLöschen(int id){
		
		try{
			conn = DriverManager.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
			PreparedStatement s = conn.prepareStatement("Delete from kunden where id =?");
			s.setInt(1, id);
			
			s.executeUpdate();
			s.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Kunde einzelnenKundeLaden(int id){
		
		Kunde kunde = new Kunde();
		try{
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
					Statement s = conn.createStatement();
					ResultSet rs = s.executeQuery("Select * from [kunden] where id = " + id );

					while (rs.next()) {
						kunde.setNachname(rs.getString("Nachname"));
						kunde.setVorname(rs.getString("Vorname"));
						kunde.setStrasse(rs.getString("Strasse"));
						kunde.setPlz(rs.getString("PLZ"));
						kunde.setWohnort(rs.getString("Wohnort"));
						kunde.setTel(rs.getString("Telefonnummer"));
						kunde.setKundenID(rs.getInt("ID"));
					}
					s.close();
		}catch(Exception e){
			
		}
		return kunde;
	}
	
	public void termineUpdaten(int kundenid, long d){
		
		Date datum = new Date(d);
		
		try {

			conn = DriverManager
			.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
			PreparedStatement s = conn.prepareStatement("Delete from termine where Datum  "
							+ "BETWEEN{ts '" + datum + " 00:00:00'} AND {ts '"
							+ datum + " 23:59:59'}  and kundenId = ?");
			s.setInt(1, kundenid);
			
			s.executeUpdate();
			s.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
