package lagerverwaltung;

import java.text.DecimalFormat;

public interface FoFormat {

		//final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("dd.MM.yyyy");
		final static DecimalFormat dcf = new DecimalFormat ("#0,00");
		
		final static DecimalFormat pf = new DecimalFormat("##0,00");
		//final static DecimalFormat bpf = new DecimalFormat("###0.00");
		
		final static DecimalFormat inf = new DecimalFormat("#####");
		
		
}
