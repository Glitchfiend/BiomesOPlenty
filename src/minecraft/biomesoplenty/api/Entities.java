package biomesoplenty.api;

public class Entities {

	public static Class Mudball = getClass("biomesoplenty.items.projectiles.EntityMudball");
	public static Class Dart = getClass("biomesoplenty.items.projectiles.EntityDart");
	public static Class JungleSpider = getClass("biomesoplenty.mobs.EntityJungleSpider");
	public static Class Rosester = getClass("biomesoplenty.mobs.EntityRosester");

	public static Class getClass(String inputstring)
	{
		Class foundclass = null;
		try
		{
			foundclass = Class.forName(inputstring);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return foundclass;
	}
}
