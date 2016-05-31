package account; 

import javax.swing.JOptionPane;

import persistenz.AnmeldungDB;


public class Accountverwaltung{
	 private AnmeldungDB anmeldungDB;
	 
	 Accountverwaltung(){anmeldungDB = new AnmeldungDB();}
	 
	public boolean anmelden(String benutzername, String passwort){
		if(anmeldungDB.mitarbeiterSuchen(benutzername).equals("")){
			JOptionPane.showMessageDialog(null, "Benutzername nicht bekannt");
			return false;
		}
		else{
			if(passwort.equals(anmeldungDB.passwortLaden(benutzername)) == false){
				JOptionPane.showMessageDialog(null, "Ungültiges Passwort");
			return false;
			}
			
			return true;
		}
	}
	
	public boolean isAdmin(String benutzername){
		if(benutzername.equals("Admin")){
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}