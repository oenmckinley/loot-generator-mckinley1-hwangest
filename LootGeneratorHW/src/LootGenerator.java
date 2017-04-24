import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

	private static final String MON_FILE = "./data/large/monstats.txt";
	private static final String TC_FILE = "./data/large/TreasureClassEx.txt";
	private static final String ARM_FILE = "./data/large/armor.txt";
	private static final String PRE_FILE = "./data/large/MagicPrefix.txt";
	private static final String SUF_FILE = "./data/large/MagicSuffix.txt";

	/**
	 * @param file
	 * @param r
	 * @return a randomly selected Monster from the file
	 * @throws IOException
	 */
	public static Monster pickMonster(String file, Random r) throws IOException {
		Object[] monsters = Files.lines(Paths.get(file)).map(str -> new Monster(str)).toArray();
		return (Monster) monsters[r.nextInt(monsters.length)];
	}

	/**
	 * @param file
	 * @param str
	 * @return true if the string matches the name of a Treasure Class, false if not.
	 * @throws IOException
	 */
	public static boolean isTC(String file, String str) throws IOException {
		return Files.lines(Paths.get(file)).map(s -> new Treasure(s)).anyMatch(tr -> tr.getTreasure().equals(str));
	}

	/**
	 * @param file
	 * @param t, the name of a Treasure Class
	 * @return curTre, a Treasure that is specified by the given string t
	 * @throws IOException
	 */
	public static Treasure fetchTreasureClass(String file, String t) throws IOException {
		Object[] treasure = Files.lines(Paths.get(file)).map(s -> new Treasure(s))
				.filter(tre -> tre.getTreasure().equals(t)).toArray();
		Treasure curTre = (Treasure) treasure[0];
		return curTre;
	}

	/**
	 * @param file
	 * @param t, the name of a treasure class
	 * @return an Armor specified by t
	 * @throws IOException
	 */
	public static Armor generateBaseItem(String file, String t) throws IOException {
		Object[] armor = Files.lines(Paths.get(file)).map(str -> new Armor(str)).filter(tre -> tre.getName().equals(t))
				.toArray();
		return (Armor) armor[0];
	}

	/**
	 * @param file
	 * @param arm
	 * @param r
	 * @return an int randomly generated between the minac and maxac of arm
	 * @throws IOException
	 */
	public static int generateBaseStats(String file, Armor arm, Random r) throws IOException {
		return r.nextInt((arm.getMaxac() - arm.getMinac())) + arm.getMinac();
	}

	/**
	 * @param preFile
	 * @param r
	 * @return a randomly selected prefix
	 * @throws IOException
	 */
	public static Prefix generatePrefix(String preFile, Random r) throws IOException {
		Object[] prefix = Files.lines(Paths.get(preFile)).map(str -> new Prefix(str)).toArray();
		return (Prefix) prefix[r.nextInt(prefix.length - 1)];
	}

	/**
	 * 
	 * @param sufFile
	 * @param r
	 * @return a randomly selected suffix
	 * @throws IOException
	 */
	public static Suffix generateSuffix(String sufFile, Random r) throws IOException {
		Object[] suffix = Files.lines(Paths.get(sufFile)).map(str -> new Suffix(str)).toArray();
		return (Suffix) suffix[r.nextInt(suffix.length - 1)];
	}

	public static void main(String[] args) throws IOException {
		String answer = "y";
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		while (answer != "n") {

			// Step 1
			Monster curMon = pickMonster(MON_FILE, r);
			String mon = curMon.getName();
			String treas = curMon.getTreasure();

			// Step 2
			while (isTC(TC_FILE, treas)) {
				String[] items = fetchTreasureClass(TC_FILE, treas).getItems();
				treas = items[r.nextInt(2)];
			}

			// Step 3
			Armor baseItem = generateBaseItem(ARM_FILE, treas);
			int baseStat = generateBaseStats(ARM_FILE, baseItem, r);

			// Step 4
			String item = baseItem.getName();
			int affixStat = 0;
			Prefix curPre = null;
			Suffix curSuf = null;
			if (r.nextInt(1) == 0) {
				curPre = generatePrefix(PRE_FILE, r);
				item = curPre.getName() + " " + item;
				if (curPre.getMax() > curPre.getMin()) {
					affixStat += r.nextInt(curPre.getMax() - curPre.getMin()) + curPre.getMin();
				} else {
					affixStat += curPre.getMin();
				}
			}
			if (r.nextInt(1) == 0) {
				curSuf = generateSuffix(SUF_FILE, r);
				item = item + " " + curSuf.getName();
				if (curSuf.getMax() > curSuf.getMin()) {
					affixStat += r.nextInt(curSuf.getMax() - curSuf.getMin()) + curSuf.getMin();
				} else {
					affixStat += curSuf.getMin();
				}
			}
			
			System.out.println("Fighting " + mon + "...");
			System.out.println("You have slain " + mon + "!");
			System.out.println(mon + " dropped:\n");

			System.out.println(item);
			System.out.println(baseStat);
			System.out.println(affixStat);

			System.out.print("Fight again (y/n)? \n");
			answer = sc.nextLine().toLowerCase();
			
		}
		
		sc.close();

	}

}
