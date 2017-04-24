
public class Armor {
	private String name;
	private String minac;
	private String maxac;
	
	public Armor (String name, String minac, String maxac) {
		this.name = name;
		this.minac = minac;
		this.maxac = maxac;
	}
	
	public Armor(String line) {
		String[] mon = line.split("	");
		this.name = mon[0];
		this.minac = mon[1];
		this.maxac = mon[2];
	}
	
	public String getName() {
		return name;
	}
	
	public int getMinac() {
		return Integer.parseInt(minac);
	}
	
	public int getMaxac() {
		return Integer.parseInt(maxac);
	}
	
}
