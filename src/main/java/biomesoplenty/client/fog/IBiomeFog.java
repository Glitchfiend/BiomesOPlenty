package biomesoplenty.client.fog;

import org.lwjgl.util.Color;

public interface IBiomeFog 
{
	public float getFogDensity(int x, int y, int z);
	public int getFogColour(int x, int y, int z);
}
