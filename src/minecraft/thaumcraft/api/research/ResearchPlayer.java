package thaumcraft.api.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchPlayer {
	public Map<String,List<String>> researchCompleted = new HashMap<String,List<String>>();
	
	public Map<String,List<Short[]>> objectsScanned = new HashMap<String,List<Short[]>>();
	public Map<String,List<String>> entitiesScanned = new HashMap<String,List<String>>();
	public Map<String,List<String>> phenomenaScanned = new HashMap<String,List<String>>();
	
}
