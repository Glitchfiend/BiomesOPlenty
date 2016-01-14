package thaumcraft.api.golems.seals;


public interface ISealConfigToggles {

	
	public SealToggle[] getToggles();
	public void setToggle(int indx, boolean value);
	
	
	public class SealToggle {
		public boolean value;
		public String key;		
		public String name;
		
		public SealToggle(boolean value, String key, String name) {
			this.value = value;
			this.key = key;
			this.name=name;
		}
		
		public boolean getValue() {
			return value;
		}

		public void setValue(boolean value) {
			this.value = value;
		}

		public String getKey() {
			return key;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
}
