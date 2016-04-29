package kundenverwaltung;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class KundeFileManager {

	private String fileName;
	
	public KundeFileManager(String fileName){
		this.fileName = fileName;
	}
	
	public String loadString(String s){
		return s.equals("") ? null:s;
	}
	
	
	
	public List<Kunde> load() throws IOException, ParseException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StreamTokenizer st = new StreamTokenizer(br);
		st.whitespaceChars(',', ',');
		
		List<Kunde> kunden = new ArrayList<Kunde>();
		Kunde kunde;
		
		while(st.nextToken() != StreamTokenizer.TT_EOF){
			
			kunde = new Kunde();
			kunde.setNachname(loadString(st.sval));
			st.nextToken();
			kunde.setVorname(loadString(st.sval));
			st.nextToken();
			kunde.setStrasse(loadString(st.sval));
			st.nextToken();
			kunde.setPlz(loadString(st.sval));
			st.nextToken();
			kunde.setWohnort(loadString(st.sval));
			st.nextToken();
			kunde.setTel(loadString(st.sval));
			kunden.add(kunde);
		}
		
		br.close();
		return kunden;
	}
	
	
	public void save(List<Kunde> kunden) throws IOException{
		
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		for(Kunde kunde : kunden){
			pw.print(kunde.getNachname());
			pw.print(',');
			pw.print(kunde.getVorname());
			pw.print(',');
			pw.print(kunde.getStrasse());
			pw.print(',');
			pw.print(kunde.getPlz());
			pw.print(',');
			pw.print(kunde.getWohnort());
			pw.print(',');
			pw.print(kunde.getTel());
			
		}
		pw.close();
	}
}
