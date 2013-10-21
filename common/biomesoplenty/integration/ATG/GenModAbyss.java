package biomesoplenty.integration.ATG;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModAbyss implements IGenMod {
	
	private static final double smooth = 0.4; // how much double height vs how much int height
	private static final int cutoff = 45; // top of the shelves
	private static final double slopefactor = 0.12; // 1/slopefactor blocks of real height shifts from "height" to "abyss" level.

	@Override
	public int modify(int height, Random random, double rawHeight) {
		double heightmix = height * (1-smooth) + ( rawHeight*256 ) * smooth;
		double factor = Math.min(1,Math.max(0, (cutoff-heightmix)*slopefactor));
		double abyss = rawHeight*rawHeight;
		return Math.max(5, (int)Math.round( (factor * abyss + (1-factor) * rawHeight) *256 ) );
	}

	@Override
	public double noiseFactor() {
		return 150.0;
	}

}
