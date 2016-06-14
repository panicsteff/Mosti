package account; 

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

import persistenz.AnmeldungDB;


public class Accountverwaltung{
	 private AnmeldungDB anmeldungDB;
	 
	 Accountverwaltung(){
		 anmeldungDB = new AnmeldungDB();
	}
	 
	public boolean anmelden(String benutzername, String passwort){
		if(anmeldungDB.mitarbeiterSuchen(benutzername).equals("")){
			JOptionPane.showMessageDialog(null, "Benutzername nicht bekannt");
			return false;
		}
		else{
			String passwort_aus_DB = anmeldungDB.passwortLaden(benutzername);
			passwort_aus_DB = passwortEntschlüsseln(passwort_aus_DB);
			
			if(passwort.equals(passwort_aus_DB) == false){
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
	
	private String passwortVerschlüsseln(String passwort){ 
		byte[] encodedBytes = Base64.encodeBase64(passwort.getBytes());		
		return new String(encodedBytes);
	}
	
	private String passwortEntschlüsseln(String passwort){
		byte[] decodedBytes = Base64.decodeBase64(passwort.getBytes());
		return new String(decodedBytes);
	}
	
	public static void main(String[] avgs){
		byte[] encodedBytes = Base64.encodeBase64("ultrageheim".getBytes());	
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		System.out.println("encoded: " + new String(encodedBytes));
		System.out.println("decoded: " + new String(decodedBytes));
		
	}
	
	
	
	
	
	
	
	
	
	
}