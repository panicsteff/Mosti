package logik.trester;

import java.util.ArrayList;
import java.util.Date;

import logik.kundenverwaltung.Kunde;
import logik.verkaufsverwaltung.Verkaufsverwaltung;
import persistenz.TresterDB;

public class Tresterverwaltung {

	private double preisPro1000L;
	private TresterDB tresterdb;

	public Tresterverwaltung() {
		tresterdb = new TresterDB();
		preisPro1000L = tresterdb.tresterpreisLaden();
	}

	public double getPreisPro1000L() {
		return tresterdb.tresterpreisLaden();
	}

	public void setPreisPro1000L(double preis) {
		preisPro1000L = preis;
		tresterdb.updateTresterpreis(preis);
	}

	public void tresterAbrechnungHinzufügen(Tresterabrechnung ta) {
		tresterdb.trestereinkaufHinzufügen(ta);
	}

	public int getTagesLiterZahl() {
		Verkaufsverwaltung v = new Verkaufsverwaltung();
		java.util.Date datum = new Date();
		java.sql.Date d = new java.sql.Date(datum.getTime());
		int literzahl = v.getLitersummeGesamterTag(d);
		return literzahl;

	}

	public double berechneTresterGesamtpreis(int literzahl) {
		return (preisPro1000L / 1000.0) * literzahl;
	}

	// alle Kunden

	public ArrayList<Tresterabrechnung> ladeTagesTresterVerkäufe(
			java.sql.Date date) {
		return tresterdb.ladeTagesTresterVerkäufeAusDB(date);
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterverkäufeZeitraum(
			java.sql.Date date1, java.sql.Date date2) {
		return tresterdb.ladeAlleTresterverkäufeZeitraumAusDB(date1, date2);
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterverkäufe() {
		return tresterdb.ladeAlleTresterverkäufeAusDB();
	}

	// bestimmter Kunde

	public ArrayList<Tresterabrechnung> ladeKundentrestereinkaufTag(
			Kunde kunde, java.sql.Date date) {
		return tresterdb.ladeKundentrestereinkaufTagAusDB(kunde, date);
	}

	public ArrayList<Tresterabrechnung> ladeKundenTrestereinkaufZeitraum(
			Kunde kunde, java.sql.Date date1, java.sql.Date date2) {
		return tresterdb.ladeKundenTrestereinkaufZeitraumAusDB(kunde, date1,
				date2);
	}

	public ArrayList<Tresterabrechnung> ladeAlleTresterEinkäufeVonKunde(
			Kunde kunde) {
		return tresterdb.ladeAlleTresterEinkäufeVonKundeAusDB(kunde);
	}

}
