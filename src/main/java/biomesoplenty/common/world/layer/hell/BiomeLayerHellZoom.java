package biomesoplenty.common.world.layer.hell;

import net.minecraft.world.gen.layer.IntCache;

public class BiomeLayerHellZoom extends BiomeLayerHell
{
	public BiomeLayerHellZoom(long par1, BiomeLayerHell par3GenLayer)
	{
		super(par1);
		super.parent = par3GenLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int i1 = par1 >> 1;
		int j1 = par2 >> 1;
		int k1 = (par3 >> 1) + 3;
		int l1 = (par4 >> 1) + 3;
		int[] aint = parent.getInts(i1, j1, k1, l1);
		int[] aint1 = IntCache.getIntCache(k1 * 2 * l1 * 2);
		int i2 = k1 << 1;
		int j2;

		for (int k2 = 0; k2 < l1 - 1; ++k2)
		{
			j2 = k2 << 1;
			int l2 = j2 * i2;
			int i3 = aint[0 + (k2 + 0) * k1];
			int j3 = aint[0 + (k2 + 1) * k1];

			for (int k3 = 0; k3 < k1 - 1; ++k3)
			{
				this.initChunkSeed(k3 + i1 << 1, k2 + j1 << 1);
				int l3 = aint[k3 + 1 + (k2 + 0) * k1];
				int i4 = aint[k3 + 1 + (k2 + 1) * k1];
				aint1[l2] = i3;
				aint1[l2++ + i2] = this.choose(i3, j3);
				aint1[l2] = this.choose(i3, l3);
				aint1[l2++ + i2] = this.modeOrRandom(i3, l3, j3, i4);
				i3 = l3;
				j3 = i4;
			}
		}

		int[] aint2 = IntCache.getIntCache(par3 * par4);

		for (j2 = 0; j2 < par4; ++j2)
		{
			System.arraycopy(aint1, (j2 + (par2 & 1)) * (k1 << 1) + (par1 & 1), aint2, j2 * par3, par3);
		}

		return aint2;
	}

	/**
	 * Chooses one of the two inputs randomly.
	 */
	 protected int choose(int par1, int par2)
	{
		return this.nextInt(2) == 0 ? par1 : par2;
	}

	/**
	 * returns the mode (most frequently occuring number) or a random number from the 4 integers provided
	 */
	 protected int modeOrRandom(int par1, int par2, int par3, int par4)
	 {
		 if (par2 == par3 && par3 == par4)
			 return par2;
		 else if (par1 == par2 && par1 == par3)
			 return par1;
		 else if (par1 == par2 && par1 == par4)
			 return par1;
		 else if (par1 == par3 && par1 == par4)
			 return par1;
		 else if (par1 == par2 && par3 != par4)
			 return par1;
		 else if (par1 == par3 && par2 != par4)
			 return par1;
		 else if (par1 == par4 && par2 != par3)
			 return par1;
		 else if (par2 == par1 && par3 != par4)
			 return par2;
		 else if (par2 == par3 && par1 != par4)
			 return par2;
		 else if (par2 == par4 && par1 != par3)
			 return par2;
		 else if (par3 == par1 && par2 != par4)
			 return par3;
		 else if (par3 == par2 && par1 != par4)
			 return par3;
		 else if (par3 == par4 && par1 != par2)
			 return par3;
		 else if (par4 == par1 && par2 != par3)
			 return par3;
		 else if (par4 == par2 && par1 != par3)
			 return par3;
		 else if (par4 == par3 && par1 != par2)
			 return par3;
		 else
		 {
			 int i1 = this.nextInt(4);
			 return i1 == 0 ? par1 : (i1 == 1 ? par2 : (i1 == 2 ? par3 : par4));
		 }
	 }

	 public static BiomeLayerHell func_75915_a(long par0, BiomeLayerHell par2GenLayer, int par3)
	 {
		 Object object = par2GenLayer;

		 for (int k = 0; k < par3; ++k)
		 {
			 object = new BiomeLayerHellZoom(par0 + k, (BiomeLayerHell)object);
		 }

		 return (BiomeLayerHell)object;
	 }
}