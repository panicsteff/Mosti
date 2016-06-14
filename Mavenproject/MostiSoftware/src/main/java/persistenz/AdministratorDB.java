package persistenz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdministratorDB {

	private Connection conn;
	
	public AdministratorDB(){
		try {
			conn = DriverManager
					.getConnection("jdbc:ucanaccess://./Mosti-Datenbank.mdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> terminWerteLaden() {

		ArrayList<Integer> adminwerte = new ArrayList<Integer>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Integer i = rs.getInt("Zeitslotl�nge");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsbeginn");
				adminwerte.add(i);
				i = rs.getInt("Arbeitsende");
				adminwerte.add(i);
				i = rs.getInt("AnzeigeAufteilung");
				adminwerte.add(i);
			}
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adminwerte;
	}
	
	public void terminWerteSpeichern(int aufteilung, int zeitslot, int beginn, int ende) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("update adminwerte set AnzeigeAufteilung = ?, Zeitslotl�nge = ?, Arbeitsbeginn = ?, Arbeitsende = ?  where id = 1");
			s.setInt(1, aufteilung);
			s.setInt(2, zeitslot);
			s.setInt(3, beginn);
			s.setInt(4, ende);
			
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> schichtWerteLaden() {

		ArrayList<Integer> adminwerte = new ArrayList<Integer>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Integer i = rs.getInt("MitarbeiterProSchicht");
				adminwerte.add(i);
				i = rs.getInt("SchichtenProTag");
				adminwerte.add(i);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return adminwerte;
	}
	
	public void schichtWerteSpeichern(int mitProSchicht, int schichtenProTag) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("update adminwerte set MitarbeiterProSchicht = ?, SchichtenProTag = ?  where id = 1");
			s.setInt(1, mitProSchicht);
			s.setInt(2, schichtenProTag);
			
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Double> pressWerteLaden() {

		ArrayList<Double> adminwerte = new ArrayList<Double>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM [Adminwerte] where id = 1");

			while (rs.next()) {
				Double d = rs.getDouble("Pressdauer");
				adminwerte.add(d);
				d = rs.getDouble("abf�lldauer");
				adminwerte.add(d);
			}
			s.close();

		} catch (Exception e) {
			System.out.println(e);

		}

		return adminwerte;
	}
	
	public void pressWerteSpeichern(double pressdauer, double abf�lldauer) {

		try {
			PreparedStatement s = null;
			s = conn.prepareStatement("update adminwerte set Pressdauer = ?, abf�lldauer = ?  where id = 1");
			s.setDouble(1, pressdauer);
			s.setDouble(2, abf�lldauer);
			
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
