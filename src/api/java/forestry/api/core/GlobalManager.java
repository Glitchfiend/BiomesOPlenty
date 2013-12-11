package forestry.api.core;

import java.util.ArrayList;

/**
 * Used mostly by hives to determine whether they can spawn at a certain position. Rather limited and hackish. 
 */
public class GlobalManager {

	public static ArrayList<Integer> dirtBlockIds = new ArrayList<Integer>();
	public static ArrayList<Integer> sandBlockIds = new ArrayList<Integer>();
	public static ArrayList<Integer> leafBlockIds = new ArrayList<Integer>();
	public static ArrayList<Integer> snowBlockIds = new ArrayList<Integer>();

}
