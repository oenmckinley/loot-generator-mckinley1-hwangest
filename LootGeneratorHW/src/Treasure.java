
public class Treasure {

	private String treasure;
	private String item1;
	private String item2;
	private String item3;
	
	public Treasure(String treasure, String item1, String item2, String item3) {
		this.treasure = treasure;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}
	
	public Treasure(String line) {
		String[] tre = line.split("	");
		this.treasure = tre[0];
		this.item1 = tre[1];
		this.item2 = tre[2];
		this.item3 = tre[3];
	}
	
	public String toString() {
		return treasure + item1 + item2 + item3;
	}
	
	public String getTreasure() {
		return treasure;
	}
	
	public String getItem1() {
		return item1;
	}
	
	public String getItem2() {
		return item2;
	}
	
	public String getItem3() {
		return item3;
	}
	
	public String[] getItems() {
		String[] ret = {item1, item2, item3};
		return ret;
		
	}
	
}
