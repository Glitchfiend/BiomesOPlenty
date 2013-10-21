package biomesoplenty.integration.ATG;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModCanyonRavine implements IGenMod {
	
	private final static double cutoff = 0.45;
	
	@Override
	public int modify(int height, Random random, double rawHeight) {
		return (int)Math.round(height * 0.6 + ((rawHeight - cutoff)*0.7+cutoff)*256*0.4);
	}

	@Override
	public double noiseFactor() {
		return 20.0;
	}

}
