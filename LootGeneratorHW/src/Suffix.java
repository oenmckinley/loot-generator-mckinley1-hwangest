
public class Suffix {
	private String name;
	private String mod1code;
	private String mod1min;
	private String mod1max;
	
	public Suffix(String name, String mod1code, String mod1min, String mod1max){
		this.name = name;
		this.mod1code = mod1code;
		this.mod1min = mod1min;
		this.mod1max = mod1max;
	}
	
	public Suffix(String line) {
		String[] mon = line.split("	");
		this.name = mon[0];
		this.mod1code = mon[1];
		this.mod1min = mon[2];
		this.mod1max = mon[3];
	}
	
	public String getName() {
		return name;
	}
	
	public int getMin() {
		return Integer.parseInt(mod1min);
	}
	
	public int getMax() {
		return Integer.parseInt(mod1max);
	}
}
