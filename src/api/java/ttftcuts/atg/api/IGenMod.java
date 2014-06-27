package ttftcuts.atg.api;

import java.util.Random;

import net.minecraft.world.World;

public interface IGenMod {
	public int modify( World world, int height, Random random, double rawHeight, int x, int z );
	
	public double noiseFactor();
}
