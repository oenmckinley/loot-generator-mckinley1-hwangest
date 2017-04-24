public class Monster {
	
	private String name;
	private String level;
	private String type;
	private String treasure;
	
	public Monster (String name, String level, String type, String treasure) {
		this.name = name;
		this.level = level;
		this.type = type;
		this.treasure = treasure;
	}
	
	public Monster (String line) {
		String[] mon = line.split("	");
		this.name = mon[0];
		this.level = mon[1];
		this.type = mon[2];
		this.treasure = mon[3];
	}
	
	public Monster print () {
		System.out.println(name);
		System.out.println(level);
		System.out.println(type);
		System.out.println(treasure);
		return this;
	}
	
	public String toString() {
		return name + level + type + treasure;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTreasure() {
		return treasure;
	}
	
}
