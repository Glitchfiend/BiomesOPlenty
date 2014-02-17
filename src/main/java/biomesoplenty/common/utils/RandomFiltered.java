package biomesoplenty.common.utils;

import java.util.Random;
/**
 * @prevent crashes in non overworld biome generation
 * due to calls to decorator getting -1 height values
 * Random.nextInt(-1) = crash
 */
public class RandomFiltered extends Random{
	public RandomFiltered(long par2) {
		super(par2);
	}
	@Override
	public int nextInt() {
		return this.nextInt(1);
	}
	@Override
	public int nextInt (int n) {
		if (n > 0) {
			return super.nextInt(n);
		}
		return 0;
	}
}
