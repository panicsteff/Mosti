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
		if(benutzername.equals("Admin") || benutzername.equals("admin")){
			return true;
		}
		return false;
	}
	
	public boolean prüfePasswort(String alt, String neu, String wiederholt, int id){
		if(alt.equals(anmeldungDB.passwortLaden(id)) == false){
			JOptionPane.showMessageDialog(null, "Altes Passwort ist falsch. Bitte versuchen Sie es erneut");
			return false;
		}else{
			if(neu.equals("") == true){
				JOptionPane.showMessageDialog(null, "Bitte gebe Sie ein neues Passwort ein");
				return false;
			}
			if(neu.equals(wiederholt) == false){
				JOptionPane.showMessageDialog(null, "Wiederholtes Passwort stimmt nicht mit neuem Passwort überein. Bitte wiederholen Sie die Eingabe");
				return false;
			}else{
				anmeldungDB.updatePasswort(neu, id);
				return true;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}