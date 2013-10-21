package ttftcuts.atg.api;

import java.util.Random;

public interface IGenMod {
	public int modify( int height, Random random, double rawHeight );
	
	public double noiseFactor();
}
