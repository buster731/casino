package setup;
public class profile{
	public static String name;
	public static long chips;
	public static int cashout;
	
	public static long getChips() {
		return chips;
	}
	
	public static String getName() {
		return name;
	}
	
	public static void setChips(long newBal) {
		chips = newBal;
	}
	
	public static void setName(String newName) {
		name = newName;
	}
}

