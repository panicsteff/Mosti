package produkte;

public class Einkaufsposition {
	
	private String name;
	private int verkaufsMenge;
	
	public Einkaufsposition(String name, int menge){
		this.setName(name);
		setVerkaufsMenge(menge);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVerkaufsMenge() {
		return verkaufsMenge;
	}

	public void setVerkaufsMenge(int verkaufsMenge) {
		this.verkaufsMenge = verkaufsMenge;
	}
	
	
	
	

}
