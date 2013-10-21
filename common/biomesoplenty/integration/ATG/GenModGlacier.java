package biomesoplenty.integration.ATG;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModGlacier implements IGenMod {
	
	@Override
	public int modify(int height, Random random, double rawHeight) {
		return height + 3;
	}

	@Override
	public double noiseFactor() {
		return 1.0;
	}

}
