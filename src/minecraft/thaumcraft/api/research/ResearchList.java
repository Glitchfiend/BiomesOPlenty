package thaumcraft.api.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thaumcraft.api.EnumTag;

public class ResearchList {
	
	/** Is the smallest column used on the GUI. */
    public static int minDisplayColumn;

    /** Is the smallest row used on the GUI. */
    public static int minDisplayRow;

    /** Is the biggest column used on the GUI. */
    public static int maxDisplayColumn;

    /** Is the biggest row used on the GUI. */
    public static int maxDisplayRow;
	
	//Research
	public static Map<String, ResearchItem> research = new HashMap<String,ResearchItem>();
	
	
	public static Map<String, List> craftingRecipesForResearch = new HashMap<String, List>();
	
	//
	
	
	/**
	 * @param key
	 * @return the research item linked to this key
	 */
	public static ResearchItem getResearch(String key) {
		return research.get(key);
	}
		
	
	
	/**
	 * @param key
	 * @return the name of the research linked to this key
	 */
	public static String getResearchName(String key) {
		ResearchItem rr = research.get(key);
		if (rr==null) return "";
		return rr.name;
	}
	
	/**
	 * @param key
	 * @return a list of the enumtags used in this research. returns the numeric values and not the actual enum
	 */
	public static byte[] getResearchTags(String key) {
		ResearchItem rr = research.get(key);
		if (rr==null) return null;
		byte[] output = new byte[rr.tags.size()];
		EnumTag[] et = rr.tags.getAspects();
		for (int a=0;a<rr.tags.size();a++) {
			output[a] = (byte) et[a].id;
		}
		return output;
	}
	
	/**
	 * @param key
	 * @return the aspect enumtag ordinal with the highest value. Used to determine scroll color and similar things
	 */
	public static int getResearchPrimaryTag(String key) {
		int t=0;
		int amt=0;
		ResearchItem rr = research.get(key);
		if (rr==null) return 0;
		for (EnumTag tag:rr.tags.getAspects()) {
			if (rr.tags.getAmount(tag)>amt) {
				t=tag.id;
				amt=rr.tags.getAmount(tag);
			};
		}
		return t;
	}
	
	public static int getResearchAmount(String key, EnumTag tag) {
		ResearchItem rr = research.get(key);
		if (rr==null) return 0;
		return rr.tags.getAmount(tag);
	}
	
}
