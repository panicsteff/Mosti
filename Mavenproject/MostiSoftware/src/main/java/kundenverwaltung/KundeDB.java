package kundenverwaltung;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KundeDB {

	Connection conn;
	ArrayList<Kunde> kundenliste;

	public ArrayList<Kunde> kundenLaden() throws FileNotFoundException{

		kundenliste = new ArrayList<Kunde>();

		try {

			conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
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
					.getConnection("jdbc:ucanaccess://C:/Users/Irmi/Desktop/Mosti/Mavenproject/MostiSoftware/Mosti-Datenkank.mdb");
			PreparedStatement s = null;
			
			for (Kunde k: kundenliste){
				
				s = conn
						.prepareStatement("update kunden set nachname = ?, vorname = ?,"
								+ " straﬂe = ?, PLZ = ?, wohnort = ?, telefonnummer = ?"
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
}
