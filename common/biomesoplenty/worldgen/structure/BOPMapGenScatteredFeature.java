package biomesoplenty.worldgen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureSwampHut;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureScatteredFeatureStart;
import net.minecraft.world.gen.structure.StructureStart;
import biomesoplenty.api.Biomes;

public class BOPMapGenScatteredFeature extends MapGenStructure
{
	private static List biomelist = Arrays.asList(new BiomeGenBase[] {
			BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland,
			Biomes.dunes.get(), Biomes.oasis.get(), Biomes.desertNew.get(),
			Biomes.bayou.get(), Biomes.bog.get(), Biomes.deadSwamp.get(), Biomes.fen.get(), Biomes.sludgepit.get(), Biomes.swamplandNew.get(),
			Biomes.rainforest.get(), Biomes.temperateRainforest.get(), Biomes.tropicalRainforest.get(), Biomes.jungleNew.get()
	});

	/** contains possible spawns for scattered features */
	private List scatteredFeatureSpawnList;

	/** the maximum distance between scattered features */
	private int maxDistanceBetweenScatteredFeatures;

	/** the minimum distance between scattered features */
	private int minDistanceBetweenScatteredFeatures;

	public BOPMapGenScatteredFeature()
	{
		scatteredFeatureSpawnList = new ArrayList();
		maxDistanceBetweenScatteredFeatures = 32;
		minDistanceBetweenScatteredFeatures = 8;
		scatteredFeatureSpawnList.add(new SpawnListEntry(EntityWitch.class, 1, 1, 1));
	}

	public BOPMapGenScatteredFeature(Map par1Map)
	{
		this();
		Iterator iterator = par1Map.entrySet().iterator();

		while (iterator.hasNext())
		{
			Entry entry = (Entry)iterator.next();

			if (((String)entry.getKey()).equals("distance"))
			{
				maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)entry.getValue(), maxDistanceBetweenScatteredFeatures, minDistanceBetweenScatteredFeatures + 1);
			}
		}
	}
	
	@Override
	public String func_143025_a() 
	{
        return "Temple";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int par1, int par2)
	{
		int k = par1;
		int l = par2;

		if (par1 < 0)
		{
			par1 -= maxDistanceBetweenScatteredFeatures - 1;
		}

		if (par2 < 0)
		{
			par2 -= maxDistanceBetweenScatteredFeatures - 1;
		}

		int i1 = par1 / maxDistanceBetweenScatteredFeatures;
		int j1 = par2 / maxDistanceBetweenScatteredFeatures;
		Random random = worldObj.setRandomSeed(i1, j1, 14357617);
		i1 *= maxDistanceBetweenScatteredFeatures;
		j1 *= maxDistanceBetweenScatteredFeatures;
		i1 += random.nextInt(maxDistanceBetweenScatteredFeatures - minDistanceBetweenScatteredFeatures);
		j1 += random.nextInt(maxDistanceBetweenScatteredFeatures - minDistanceBetweenScatteredFeatures);

		if (k == i1 && l == j1)
		{
			BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt(k * 16 + 8, l * 16 + 8);
			Iterator iterator = biomelist.iterator();

			while (iterator.hasNext())
			{
				BiomeGenBase biomegenbase1 = (BiomeGenBase)iterator.next();

				if (biomegenbase == biomegenbase1)
					return true;
			}
		}

		return false;
	}

	@Override
	protected StructureStart getStructureStart(int par1, int par2)
	{
		return new BOPStructureScatteredFeatureStart(worldObj, rand, par1, par2);
	}
	
    public boolean func_143030_a(int par1, int par2, int par3)
    {
        StructureStart structurestart = this.func_143028_c(par1, par2, par3);

        if (structurestart != null && structurestart instanceof StructureScatteredFeatureStart && !structurestart.components.isEmpty())
        {
            StructureComponent structurecomponent = (StructureComponent)structurestart.components.getFirst();
            return structurecomponent instanceof ComponentScatteredFeatureSwampHut;
        }
        else
        {
            return false;
        }
    }

	/**
	 * returns possible spawns for scattered features
	 */
	public List getScatteredFeatureSpawnList()
	{
		return scatteredFeatureSpawnList;
	}
}
