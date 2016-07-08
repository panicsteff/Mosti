package logik.produktverwaltung;

import java.text.DecimalFormat;

public interface FoFormat {

	final static DecimalFormat dcf = new DecimalFormat("#0,00");

	final static DecimalFormat pf = new DecimalFormat("##0,00");

}
